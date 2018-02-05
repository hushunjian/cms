package com.m2m.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ExtAppUserQuery {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;

    private Integer meNumber;

    private String mobile;

    private String nickName;

    private Integer isInvited;

    private Integer isV;

    private Integer status;

    private Date createdBegin;

    private Date createdEnd;
    
    private String registerChannelCode;
}
