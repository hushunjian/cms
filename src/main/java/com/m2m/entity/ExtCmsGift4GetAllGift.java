package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsGift4GetAllGift implements Serializable {
    private Long total;

    private List<ExtGift> data = new ArrayList<ExtGift>();

}
