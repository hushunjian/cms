package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsAllTagKingdom {
    private Long total;

    private List<ExtTagKing> data = new ArrayList<ExtTagKing>();
}
