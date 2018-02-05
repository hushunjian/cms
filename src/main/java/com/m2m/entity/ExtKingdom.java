package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtKingdom {

    private Long topicId;

    private String topicName;

    private Date createdAt;

    private Date updatedAt;

    private Long userId;

    private String userName;

    private Integer isV;

    private Integer type;

    private Integer reviewCount;

    private Integer readCount;

    private Integer likeCount;

    private Integer memberCount;

    private Integer updateCount;

    private Integer imageCount;

    private Integer audioCount;

    private Integer videoCount;

    private Integer wordCount;

    private String link;
}
