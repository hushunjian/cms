package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsLotteryUser implements Serializable {
    private Long total;

    private List<ExtLotteryUser> data = new ArrayList<ExtLotteryUser>();
}
