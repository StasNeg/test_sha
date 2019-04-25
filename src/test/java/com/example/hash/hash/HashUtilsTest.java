package com.example.hash.hash;

import com.example.hash.model.hash.HashType;
import com.example.hash.repository.HashTypeRepository;
import com.example.hash.service.HashTypeService;
import com.example.hash.utils.HashingUtils.HashingUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=update","telephone.count=0"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HashUtilsTest {

    @Autowired
    HashingUtils hash;
    @Autowired
    HashTypeService service;


    @Test
    public void testHash3(){
        service.saveOrEdit(new HashType("3",""));
//        bf73e5063f440a327cefdd241fdc4c406a24c005e484f36786c3670ed7cb4026c920907fe9e89bfee84492d11e6b4a4b
        Assert.assertEquals("bf73e5063f440a327cefdd241fdc4c406a24c005e484f36786c3670ed7cb4026c920907fe9e89bfee84492d11e6b4a4b",hash.getHashString("380686304344"));
    }

    @Test
    public void testHash2(){
        service.saveOrEdit(new HashType("2",""));
//      //        a84b1c4ac161a5eb022738360df1b922413da3c4b45e66110cd2b9a014a4c9d3
        Assert.assertEquals("a84b1c4ac161a5eb022738360df1b922413da3c4b45e66110cd2b9a014a4c9d3",hash.getHashString("380686304344"));
    }

    @Test
    public void testHash1(){
        service.saveOrEdit(new HashType("1",""));
//        a6866607e5f9674d80308ea24e6028fd5a1f5e63
        Assert.assertEquals("a6866607e5f9674d80308ea24e6028fd5a1f5e63",hash.getHashString("380686304344"));
    }
}
