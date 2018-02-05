package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtUserProfile {
    private Long uid;

    private Integer meNumber;

    private String mobile;

    private String nickName;

    private Integer isInvited;

    private Integer isV;

    private Integer level;

    private Integer status;

    private Integer silentStatus;

    private String registerChannel;

    private String registerVersion;

    private Date registerTime;
    
    private Date lastOptAt;

}
