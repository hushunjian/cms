package com.m2m.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ExtTagSearchQuery implements Serializable {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;

    private String tagName;

    private Date startTime;

    private Date endTime;

    private Integer isSystem;

    private Integer isRecommend;

    private Integer topicCountStart;

    private Integer topicCountEnd;

    private Integer parentTagId;

    private Integer noParentTag;
}
