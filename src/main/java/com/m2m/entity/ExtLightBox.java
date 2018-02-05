package com.m2m.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExtLightBox {
    private Integer id;

    private String image;

    private String mainText;

    private String subText;

    private String mainColor;

    private String link;

    private Date beginAt;

    private Date endAt;

    private Integer status;
}
