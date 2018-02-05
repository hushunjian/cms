package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsTag4GetTagSearchRequest extends CmsCredentialRequest implements Serializable {
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
