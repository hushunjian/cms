package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsRankSet {
    private int listType;
    private int mode;
    private List<ExtRankSet> data = new ArrayList<ExtRankSet>();
}
