package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExtEmotion implements Serializable {
    private Integer id;

    private Integer packId;

    private String name;

    private String cover;

    private Integer order;

    private String extra;

    private Integer width;

    private Integer height;
}
