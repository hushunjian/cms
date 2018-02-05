package com.m2m.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ExtAdInfo implements Serializable {

    private static final long serialVersionUID = 2364883259144794692L;

    private Long id;

    private String name;

    private String cover;

    private Long positionId;

    private String positionName;

    private Integer probability;

    private Integer coverWidth;

    private Integer coverHeight;

    private Date validAt;

    private Integer type;

    private Long topicId;

    private String url;
    private Date createdAt;
}
