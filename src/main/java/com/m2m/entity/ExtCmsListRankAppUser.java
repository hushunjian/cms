package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsListRankAppUser {
    private Long total;
    private List<ExtListRankAppUser> data = new ArrayList<ExtListRankAppUser>();
}
