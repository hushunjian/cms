package com.m2m.response;

import com.m2m.entity.ExtRegisteredUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsBehaviour4GetRegisteredUserResponse {
    private long total;
    private List<ExtRegisteredUser> data = new ArrayList<ExtRegisteredUser>();
}
