package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsLottery implements Serializable {
    private Long total;

    private List<ExtLottery> data = new ArrayList<ExtLottery>();
}
