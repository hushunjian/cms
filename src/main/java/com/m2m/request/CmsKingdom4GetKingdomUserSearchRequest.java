package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4GetKingdomUserSearchRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;
    @NotNull
    private Long topicId;

    private String fragment;

    private Date joinBegin;

    private Date joinEnd;

    private Integer isFirstReview;
}
