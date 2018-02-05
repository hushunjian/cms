package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtDailyOperation {
    private Long uid;
    private String url;
    private Date createdAt;
    private Long visitInterval;
}
