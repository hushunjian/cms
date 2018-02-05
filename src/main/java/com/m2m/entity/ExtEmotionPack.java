package com.m2m.entity;

import lombok.Data;

@Data
public class ExtEmotionPack {
    private Integer id;

    private String name;

    private String cover;

    private Integer type;

    private Integer isValid;

    private Integer packVersion;

    private Integer analyVersion;

    private Integer order;

    private String extra;
}
