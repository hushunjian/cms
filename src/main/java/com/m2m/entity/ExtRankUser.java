package com.m2m.entity;

import lombok.Data;

@Data
public class ExtRankUser {
    private long id;
    private long uid;
    private String userNick;
    private int isV;
    private int sort;
}
