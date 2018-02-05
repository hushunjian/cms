package com.m2m.service;

import com.m2m.domain.CmsCredential;
import com.m2m.domain.CmsCredentialExample;
import com.m2m.entity.ExtCmsCredential;
import com.m2m.exception.AuthenticationException;
import com.m2m.exception.SystemException;
import com.m2m.mapper.CmsCredentialMapper;
import com.m2m.mapper.ExtCmsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CmsCredentialService {
    @Autowired
    private CmsCredentialMapper cmsCredentialMapper;

    @Autowired
    private ExtCmsUserMapper extCmsUserMapper;

    public ExtCmsCredential getCredential(String token) throws SystemException {
        List<ExtCmsCredential> extCmsCredentials = extCmsUserMapper.getCredential(token);
        if (extCmsCredentials.size() == 1) {
            ExtCmsCredential extCmsCredential = extCmsCredentials.get(0);
            return extCmsCredential;
        } else {
            throw new AuthenticationException();
        }
    }

    public Integer remove(String token) {
        CmsCredentialExample example = new CmsCredentialExample();
        CmsCredentialExample.Criteria criteria = example.createCriteria();
        criteria.andTokenEqualTo(token);
        return cmsCredentialMapper.deleteByExample(example);
    }

    public Integer reset(Integer userId) {
        CmsCredentialExample example = new CmsCredentialExample();
        CmsCredentialExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return cmsCredentialMapper.deleteByExample(example);
    }

    public CmsCredential allocate(Integer userId, String name) {
        CmsCredential cmsCredential = new CmsCredential();
        String token = UUID.randomUUID().toString();
        if (name.equals("root")) {
            token = "99999999-9999-9999-9999-999999999999";
        } else if (name.equals("caochanghao")) {
            token = "77777777-7777-7777-7777-777777777777";
        } else if (name.equals("pengcheng")) {
            token = "88888888-8888-8888-8888-888888888888";
        }
        cmsCredential.setUserId(userId);
        cmsCredential.setToken(token);
        reset(userId);
        cmsCredentialMapper.insert(cmsCredential);
        return cmsCredential;
    }
}
