package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsUserLogResponse implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer featureId;
}