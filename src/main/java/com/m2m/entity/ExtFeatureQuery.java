package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExtFeatureQuery implements Serializable {
    private Integer userId;
    private Integer featureId;
}
