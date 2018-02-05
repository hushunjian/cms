package com.m2m.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CmsUserFeatureResponse implements Serializable {
    private Integer id;
    private Integer userId;
    private List<Integer> featureIds;
}