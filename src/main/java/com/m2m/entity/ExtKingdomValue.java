package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtKingdomValue {
    private Long topicId;

    private String topicName;

    private Long uid;

    private String userName;

    private Date createdAt;

    private Date updatedAt;

    private Integer type;

    private Integer value;

    private Integer lastAddValue;

    private Integer stealValue;

    private Double careDegree;

    private Double approvalDegree;
}
