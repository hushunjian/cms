package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsRankKingdom {
    private Integer listType;

    private Integer mode;

    private List<ExtRankKingdom> data = new ArrayList<>();
}
