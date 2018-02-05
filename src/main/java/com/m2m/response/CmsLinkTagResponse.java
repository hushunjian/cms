package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsLinkTagResponse implements Serializable {
    private Integer id;
    private String name;
    private Integer positionId;
    private String position;
}