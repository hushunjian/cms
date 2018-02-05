package com.m2m.response;

import com.m2m.entity.ExtListRank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAllListRankResponse {
    private Long total;

    private List<ExtListRank> data = new ArrayList<>();
}
