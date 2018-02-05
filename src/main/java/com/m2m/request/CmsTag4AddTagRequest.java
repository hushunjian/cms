package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsTag4AddTagRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private String tagName;
    private Integer parentTagId;
    private Integer sortNumber;
    @NotNull
    private Integer isSystem;
    @NotNull
    private Integer isRecommend;
    @NotNull
    private Integer status;
    @NotNull
    private String coverImage;
    @NotNull
    private Integer[] userHobbyIds;
}
