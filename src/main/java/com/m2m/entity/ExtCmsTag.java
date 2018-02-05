package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsTag implements Serializable {

    private Long total;

    private List<ExtTagTemp> data = new ArrayList<ExtTagTemp>();


}
