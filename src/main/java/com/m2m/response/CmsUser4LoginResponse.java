package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsUser4LoginResponse implements Serializable {
    private String token;
    private Integer id;
    private String name;
    private String mobile;
}