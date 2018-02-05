package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAppUser4GetAppUserSearchRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;

    private Long meNumber;

    private String mobile;

    private String nickName;

    private Integer isInvited;

    private Integer isV;

    private Integer status;

    private Date createdBegin;

    private Date createdEnd;
    
    private String registerChannelCode;
}
