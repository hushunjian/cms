package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsKingdomReview {
    private Long total;
    private List<ExtKingdomReview> data = new ArrayList<>();
}
