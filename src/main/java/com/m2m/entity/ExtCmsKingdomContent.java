package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsKingdomContent {

    private Long total;

    private List<ExtUserKingdomContent> data = new ArrayList<>();
}
