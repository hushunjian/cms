package com.m2m.response;

import com.m2m.entity.ExtUserProfile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsAppUser4GetAllAppUserResponse {

    private Long total;

    private List<ExtUserProfile> data = new ArrayList<ExtUserProfile>();
}
