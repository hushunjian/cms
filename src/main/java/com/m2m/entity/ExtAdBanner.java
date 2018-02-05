package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExtAdBanner implements Serializable {

    private static final long serialVersionUID = 478679301755089258L;

    private Long id;

    private String name;

    private String position;

    private int width;

    private int height;

    private Date createdAt;

    private int type;
}
