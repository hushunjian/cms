package com.m2m.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ExtListRankAppUserQuery {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;
    private String nickName;
    private Date createdBegin;
    private Date createdEnd;
    private Integer isV;
    private Integer topicCountStart;
    private Integer topicCountEnd;
    private Integer followCountStart;
    private Integer followCountEnd;
    private Integer fansCountStart;
    private Integer fansCountEnd;
}
