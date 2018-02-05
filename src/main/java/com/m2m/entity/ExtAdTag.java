package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExtAdTag implements Serializable {

    private static final long serialVersionUID = 6095470432012969026L;
    private Long id;
    private String name;
    private int position;
    private Long positionId;
}
