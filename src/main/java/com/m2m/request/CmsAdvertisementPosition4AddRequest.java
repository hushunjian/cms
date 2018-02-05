package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAdvertisementPosition4AddRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String position;
    @NotNull
    private Integer height;
    @NotNull
    private Integer width;
    @NotNull
    private Integer type;
}