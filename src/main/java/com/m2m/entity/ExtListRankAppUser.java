package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtListRankAppUser {
    private long uid;
    private String nickName;
    private Date createdAt;
    private int isV;
    private int topicCount;
    private int followCount;
    private int fansCount;
}
