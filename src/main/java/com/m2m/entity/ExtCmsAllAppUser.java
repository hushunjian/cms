package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsAllAppUser {

    private Long total;

    private List<ExtUserProfile> data = new ArrayList<ExtUserProfile>();
}
