package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsRankUser {
    private int listType;
    private int mode;
    private List<ExtRankUser> data = new ArrayList<ExtRankUser>();
}
