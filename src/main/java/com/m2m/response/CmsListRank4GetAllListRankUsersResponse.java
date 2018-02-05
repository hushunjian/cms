package com.m2m.response;

import com.m2m.entity.ExtRankUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAllListRankUsersResponse {
    private int listType;
    private int mode;
    private List<ExtRankUser> data = new ArrayList<ExtRankUser>();
}
