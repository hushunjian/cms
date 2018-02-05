package com.m2m.entity;

import lombok.Data;

@Data
public class ExtUserDailyCount {
    private Long uid;
    private Integer visitCount;
    private Long visitTimeCount;
}
