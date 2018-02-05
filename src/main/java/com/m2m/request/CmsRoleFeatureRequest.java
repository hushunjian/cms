package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsRoleFeatureRequest extends CmsCredentialRequest implements Serializable {
    private Integer roleId;
    private List<Integer> featureIds;
}