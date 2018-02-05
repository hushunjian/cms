package com.m2m.response;

import com.m2m.entity.ExtListRankAppUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAppUserResponse {
    private Long total;
    private List<ExtListRankAppUser> data = new ArrayList<ExtListRankAppUser>();
}
