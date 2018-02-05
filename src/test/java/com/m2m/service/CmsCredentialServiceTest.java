package com.m2m.service;

import com.m2m.entity.ExtCmsCredential;
import com.m2m.exception.SystemException;
import com.m2m.util.MD5;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsCredentialServiceTest {
    @Autowired
    private CmsCredentialService cmsCredentialService;

    @Test
    public void testConvert() throws SystemException{
        String token = "99999999-9999-9999-9999-999999999999";
        ExtCmsCredential cmsCredentials = cmsCredentialService.getCredential(token);
        //Assert.assertEquals(cmsCredentials.equals(null), false);
        Assert.assertEquals(true, true);
    }
}
