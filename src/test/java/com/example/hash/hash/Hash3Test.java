package com.example.hash.hash;

import com.example.hash.utils.HashingUtils.HashingUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HashingUtils.class)
@TestPropertySource(properties = "hash.type=3")
public class Hash3Test {

    @Autowired
    HashingUtils hash;

    @Test
    public void testHash(){
//        bf73e5063f440a327cefdd241fdc4c406a24c005e484f36786c3670ed7cb4026c920907fe9e89bfee84492d11e6b4a4b
        Assert.assertEquals("bf73e5063f440a327cefdd241fdc4c406a24c005e484f36786c3670ed7cb4026c920907fe9e89bfee84492d11e6b4a4b",hash.getHashString("380686304344",""));
    }


}
