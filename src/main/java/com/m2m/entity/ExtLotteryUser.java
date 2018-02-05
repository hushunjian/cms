package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtLotteryUser {
    private Long lotteryId;

    private String lotteryName;

    private String userNick;

    private Long userId;

    private Date joinTime;

    private Integer status;

    private Integer isAppoint;

    private Integer invitationNum;
}
