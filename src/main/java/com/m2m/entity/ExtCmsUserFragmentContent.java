package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsUserFragmentContent {
    private Long total;

    private List<ExtUserFragmentContent> data = new ArrayList<>();
}
