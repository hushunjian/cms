package com.m2m.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExtUserFeature implements Serializable {
    private Integer userId;
    private List<ExtCmsFeature> features;
}