package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsListRank4AddListRankRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private Integer mode;
    @NotNull
    private Integer type;
    private String summary;
    private String bgColor;
    private String img;
    private Integer imgWidth;
    private Integer imgHeight;
}
