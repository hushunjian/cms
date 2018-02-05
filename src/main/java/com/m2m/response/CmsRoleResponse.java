package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsRoleResponse implements Serializable {
    private Integer id;
    private String name;
}