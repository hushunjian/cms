package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsBehaviour4GetUserBehavioursSearchRequest extends CmsCredentialRequest implements Serializable {
    private Long pageIndex;
    private Long pageSize;
    private Date createdBegin;
    private Date createdEnd;
    private Integer[] timeQuantum;
    private Long interviewId;
    private Long duration;
}
