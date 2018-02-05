package com.m2m.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExtRoleFeature implements Serializable {
    private Integer roleId;
    private List<Integer> featureIds;
}