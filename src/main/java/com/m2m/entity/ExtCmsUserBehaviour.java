package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsUserBehaviour {
    private Long total;
    private List<ExtUserBehaviour> data = new ArrayList<ExtUserBehaviour>();
}
