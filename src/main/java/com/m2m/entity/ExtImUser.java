package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExtImUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String token;

    private String userId;
}
