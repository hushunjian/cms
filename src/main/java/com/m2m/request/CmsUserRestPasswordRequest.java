package com.m2m.request;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsUserRestPasswordRequest extends CmsCredentialRequest implements Serializable {
    private Integer id;
}