package com.m2m.response;

import com.m2m.entity.ExtOnlineListRank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAllOnlineListRankResponse {
    private List<ExtOnlineListRank> data = new ArrayList<>();
}
