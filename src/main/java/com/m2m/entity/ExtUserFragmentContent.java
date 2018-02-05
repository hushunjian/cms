package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtUserFragmentContent {
    private Integer uid;

    private Integer topicId;

    private Integer contentType;

    private String extra;

    private String fragment;

    private String fragmentImage;

    private Integer id;

    private Integer status;

    private Integer type;

    private Date createdAt;
}
