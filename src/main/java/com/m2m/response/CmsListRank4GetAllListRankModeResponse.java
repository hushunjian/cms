package com.m2m.response;

import com.m2m.entity.ExtBillBoardMode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsListRank4GetAllListRankModeResponse {
    private Long total;

    private List<ExtBillBoardMode> data = new ArrayList<>();
}
