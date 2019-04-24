package com.example.hash.hash;

import com.example.hash.utils.HashingUtils.HashingUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HashingUtils.class)
@TestPropertySource(properties = "hash.type=1")
public class Hash1Test {
    @Autowired
    HashingUtils hash;

    @Test
    public void testHash(){
//        a6866607e5f9674d80308ea24e6028fd5a1f5e63
        Assert.assertEquals("a6866607e5f9674d80308ea24e6028fd5a1f5e63",hash.getHashString("380686304344",""));
    }


}
