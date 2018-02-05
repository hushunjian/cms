package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsBehaviour4GetUserBehavioursTotalSearchRequest extends CmsCredentialRequest implements Serializable {
    private Date createdBegin;
    private Date createdEnd;
    private Integer[] timeQuantum;
    private Long interviewId;
}
