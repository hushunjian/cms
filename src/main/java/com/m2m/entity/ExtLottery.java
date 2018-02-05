package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtLottery {
    private Long topicId;

    private String topicName;

    private Long lotteryId;

    private String lotteryName;

    private Date createdAt;

    private Date validAt;

    private Integer status;
}
