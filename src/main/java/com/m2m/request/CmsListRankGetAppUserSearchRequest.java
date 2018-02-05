package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsListRankGetAppUserSearchRequest extends CmsCredentialRequest implements Serializable {
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
    private Integer followcountEnd;
    private Integer fansCountStart;
    private Integer fansCountEnd;
}
