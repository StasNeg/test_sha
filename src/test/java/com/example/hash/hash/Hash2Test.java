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
@TestPropertySource(properties = "hash.type=2")
public class Hash2Test {

    @Autowired
    HashingUtils hash;

    @Test
    public void testHash(){
//        a84b1c4ac161a5eb022738360df1b922413da3c4b45e66110cd2b9a014a4c9d3
        Assert.assertEquals("a84b1c4ac161a5eb022738360df1b922413da3c4b45e66110cd2b9a014a4c9d3",hash.getHashString("380686304344",""));
    }


}
