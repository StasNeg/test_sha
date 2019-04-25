Configuration.

For launch:
    1. Create schema Mysql DB.
        set up it into:  spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_SCHEMA_NAME?useSSL=false
        set credential to your MySqlServer:
        spring.datasource.username=YOUR_USERNAME
        spring.datasource.password=YOUR_PASSWORD
    2. For auto create records:
        You can fill records in to schema if your properties contains:
            spring.jpa.hibernate.ddl-auto=create
        set up it into:
            mysql.load.path=C:\\ProgramData\\MySQL\\MySQL Server 5.6\\Data\\hash_1\\    ---- path to your Folder with Schema data (for import)
            telephone.count=5000000                                                     ---- count of created records
            telephone.batch.size=1300000                                                ---- batch size records (recommended not greater than 1 500 000)
            hash.type=2                                                                 ---- type of hash (only 1, 2, 3)
            hash.salt=salt                                                              ---- salt: your text with salt
        that data will be used only when you create your records.
    3. Run mvn clean install
    4. If you want change properties add your properties.files and change paths in test.bat ant run it.

    After successful run you could see : "end add to sql"

    Working with Application:
    Application answers for two requests:
    1. YOUR_HOST/test/telephone/number/{number}
        curl -X GET \
          http://localhost:8080/test/telephone/number/380001110033 \
          -H 'Authorization: Basic c3RhbmlzbGF2QGVtYWlsLmNvbTpwYXNzd29yZA==' \
    2. YOUR_HOST/test/telephone/hash/{hash}
        curl -X GET \
          http://localhost:8080/test/telephone/hash/aefd6c1689f9a808473e9f1c48bc5d062bf3aa36 \
          -H 'Authorization: Basic c3RhbmlzbGF2QGVtYWlsLmNvbTpwYXNzd29yZA==' \
    For basic Authorisation used:
    UserName: stanislav@email.com
    Password: password

    What conditions didn't realise:
    1. Time of response - is very long - in Table didn't use indexes (for rapid time to init DataBase)
    2. I can't tackle with collisions
