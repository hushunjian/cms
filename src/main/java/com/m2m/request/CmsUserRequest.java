package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsUserRequest extends CmsCredentialRequest implements Serializable {
    private String name;
    private String password;
    private String mobile;
}