package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtTagKing {
    private Integer topicId;

    private String topicName;

    private Integer tagId;

    private String tagName;

    private Date createdAt;

    private Date updatedAt;

    private String link;

}
