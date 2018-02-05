package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtListedKingdom {
    private Long id;
    private Long topicId;
    private String topicName;
    private Integer topicValue;
    private String topicOwnerName;
    private Integer topicOwnerId;
    private Date listedAt;
    private Date purchaseAt;
    private Long buyerId;
    private String buyerName;
    private Integer meNumber;
    private Integer status;
}
