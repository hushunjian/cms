package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtKingdomUserQuery {
    private Long pageIndex;
    private Long pageSize;
    private String fragment;
    private Date joinBegin;
    private Date joinEnd;
    private Integer isFirstReview;
    private Long topicId;
}
