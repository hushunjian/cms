package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsFeatureGroup {
    private Integer groupId;
    private String groupName;
    private List<ExtCmsFeature> features = new ArrayList<ExtCmsFeature>();
}