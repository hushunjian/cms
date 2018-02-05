package com.m2m.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ExtUserBehaviour {
    private Long uid;
    private String nickName;
    private Date createdAt;
    private String interview;
    private Long duration;
    private Long visitTimes;
    private Integer visitTimeQuantum;
}
