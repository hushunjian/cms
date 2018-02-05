package com.m2m.response;

import com.m2m.entity.ExtRankSet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAllListRankSetsResponse {
    private int listType;
    private int mode;
    private List<ExtRankSet> data = new ArrayList<ExtRankSet>();
}
