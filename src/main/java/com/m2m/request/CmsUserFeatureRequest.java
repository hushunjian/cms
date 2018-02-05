package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsUserFeatureRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Integer userId;
    @NotNull
    private List<CmsFeature4UserRequest> features;
}