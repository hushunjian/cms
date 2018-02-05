package com.m2m.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CmsRoleFeatureResponse implements Serializable {
    private Integer id;
    private Integer roleId;
    private List<Integer> featureIds;
}