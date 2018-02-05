package com.m2m.mapper;

import com.m2m.entity.ExtCmsCredential;
import com.m2m.entity.ExtCmsUser;

import java.util.Date;
import java.util.List;

public interface ExtCmsUserMapper {
    ExtCmsUser findById(Long id);

    List<ExtCmsCredential> getCredential(String token);
}