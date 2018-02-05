package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsCredentialResponse implements Serializable {
    private String token;
    private Integer id;
    private String mobile;
    private String password;
}