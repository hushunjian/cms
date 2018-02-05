package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsFeatureRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Integer id;
    private String name;
}