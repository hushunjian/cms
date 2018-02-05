package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtCmsUserBehaviourTotalQuery {
    private Date createdBegin;
    private Date createdEnd;
    private Integer[] timeQuantum;
    private Long interviewId;
}
