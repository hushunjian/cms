package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAdvertisementPositionRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long id;
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