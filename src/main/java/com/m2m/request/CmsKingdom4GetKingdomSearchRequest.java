package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4GetKingdomSearchRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;

    private String topicName;

    private String userName;

    private Integer isV;

    private Integer type;

    private Date createdBegin;

    private Date createdEnd;

    private Date updatedBegin;

    private Date updatedEnd;

    private Integer reviewCountStart;

    private Integer reviewCountEnd;

    private Integer readCountStart;

    private Integer readCountEnd;

    private Integer likeCountStart;

    private Integer likeCountEnd;

    private Integer memberCountStart;

    private Integer memberCountEnd;

    private Integer updateCountStart;

    private Integer updateCountEnd;

    private Integer imageCountStart;

    private Integer imageCountEnd;

    private Integer audioCountStart;

    private Integer audioCountEnd;

    private Integer videoCountStart;

    private Integer videoCountEnd;

    private Integer wordCountStart;

    private Integer wordCountEnd;

}
