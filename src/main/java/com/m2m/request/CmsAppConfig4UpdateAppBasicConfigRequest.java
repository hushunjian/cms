package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAppConfig4UpdateAppBasicConfigRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Integer itemGroupId;
    @NotNull
    private String key;
    @NotNull
    private String des;
    @NotNull
    private String value;
}