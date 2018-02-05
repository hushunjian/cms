package com.m2m.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsFeatureGroupResponse {
    private Integer groupId;
    private String groupName;
    private List<CmsFeatureResponse> features = new ArrayList<CmsFeatureResponse>();
}