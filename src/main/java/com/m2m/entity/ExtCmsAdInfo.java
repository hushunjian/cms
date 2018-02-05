package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsAdInfo {
    private Long total;
    private List<ExtAdInfo> data = new ArrayList<ExtAdInfo>();
}
