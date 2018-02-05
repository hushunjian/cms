package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsKingdomValue {
    private Long total;
    private List<ExtKingdomValue> data = new ArrayList<ExtKingdomValue>();
}
