package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtRegisteredUser {
    private Long uid;
    private String nickName;
    private String registerChannel;
    private String logonDevice;
    private Date registerTime;
}
