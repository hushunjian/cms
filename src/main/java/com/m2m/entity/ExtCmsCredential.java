package com.m2m.entity;

import lombok.Data;

@Data
public class ExtCmsCredential {
    private String token;
    private Integer id;
    private String name;
    private String mobile;
    private String password;
}