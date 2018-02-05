package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsKingdomListed {
    private Long total;
    private List<ExtListedKingdom> data = new ArrayList<ExtListedKingdom>();
}
