package com.m2m.entity;

import lombok.Data;

@Data
public class ExtGift {
    private Long giftId;

    private String name;

    private String image;

    private Integer imageWidth;

    private Integer imageHeight;

    private Integer price;

    private Integer addPrice;

    private String gifImage;

    private Integer duration;

    private Integer sortNumber;
}
