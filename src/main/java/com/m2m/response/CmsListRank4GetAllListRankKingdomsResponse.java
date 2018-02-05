package com.m2m.response;

import com.m2m.entity.ExtRankKingdom;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAllListRankKingdomsResponse {
    private Integer listType;

    private Integer mode;

    private List<ExtRankKingdom> data = new ArrayList<>();
}
