package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExtTagTemp implements Serializable {
    private Integer tagId;

    private String tagName;

    private Integer parentTagId;

    private String parentTagName;

    private Date createdAt;

    private Integer isSystem;

    private Integer isRecommend;

    private Integer status;

    private Integer topicCount;

    private Integer sortNumber;

    private String coverImage;

    private String userHobbyIds;
}
