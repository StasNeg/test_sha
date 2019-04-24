package com.example.hash.init;

import com.example.hash.model.tele.Telephone;
import com.example.hash.repository.TelephoneRepository;
import com.example.hash.repository.UserRepository;
import com.example.hash.utils.HashingUtils.HashingUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Order(0)
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create-drop")
public class InitDataBase implements ApplicationRunner {

    private UserRepository userRepository;
    private TelephoneRepository telephoneRepository;
    private HashingUtils hashingUtils;
    private DataSource dataSource;
    private Logger logger = LoggerFactory.getLogger(InitDataBase.class);

    @Value("${telephone.count}")
    private int count;

    @Value("${telephone.batch.size}")
    private int batchSize;

    @Value("${hash.salt}")
    private String salt;


    @Autowired
    public InitDataBase(UserRepository userRepository, TelephoneRepository telephoneRepository, HashingUtils hashingUtils, DataSource dataSource) {
        this.userRepository = userRepository;
        this.telephoneRepository = telephoneRepository;
        this.hashingUtils = hashingUtils;
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        int batchCount = count / batchSize;
        int restBatch = count % batchSize;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < batchCount; i++) {
            String filename = getFileWithBatch(batchSize, i * batchSize);
            startThread(filename, threads);
        }
        if (restBatch > 0) {
            String filename = getFileWithBatch(restBatch, batchCount * batchSize);
            startThread(filename, threads);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        logger.warn("end add to sql");
//        telephoneRepository.addIndexToHash();
        logger.warn("end with index");
    }

    private void startThread(String filename, List<Thread> threads) {
        Thread thread = new Thread(() -> {
            logger.warn(Thread.currentThread().getName() + " is received: " + filename);
            telephoneRepository.bulkLoadData(filename);
            Path fileToDeletePath = Paths.get(filename);
            try {
                Files.delete(fileToDeletePath);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        });
        threads.add(thread);
        thread.start();
    }

    private String getFileWithBatch(int batchSize, int startId) throws IOException {
        List<Telephone> list = new ArrayList<>(batchSize);
        for (int j = 0; j < batchSize; j++) {
            String tel = createTelephone(startId + j);
            list.add(new Telephone((long) (startId + j), tel, hashingUtils.getHashString(tel, "")));

        }
        return createCSVFile(list);
    }

    private String createTelephone(int lastNumber) {
        StringBuilder telNumber = new StringBuilder("380");
        String last = Integer.toString(lastNumber);
        for (int j = 0; j < 9 - last.length(); j++) {
            telNumber.append("0");
        }
        telNumber.append(last);
        return telNumber.toString();
    }

    public String createCSVFile(Iterable<Telephone> telephones) throws IOException {
        String filename = "C:\\ProgramData\\MySQL\\MySQL Server 5.6\\Data\\hash\\load_" + new Date().getTime() + ".csv";
        String[] HEADERS = {"id", "hash", "number"};
        FileWriter out = new FileWriter(filename);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.MYSQL))
//                .withHeader(HEADERS)))
        {
            telephones.forEach(telephone -> {
                try {
                    printer.printRecord(telephone.getId(), telephone.getHash(), telephone.getNumber());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return filename;
    }
}