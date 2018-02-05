package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsBehaviour4RegisteredUserSearchRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;

    private Date createdBegin;

    private Date createdEnd;
    
    private String registerChannelCode;
}
