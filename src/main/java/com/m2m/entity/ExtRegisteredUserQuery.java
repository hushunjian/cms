package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtRegisteredUserQuery {
    private Long pageIndex;
    private Long pageSize;
    private Date createdBegin;
    private Date createdEnd;
    private String registerChannelCode;
}
