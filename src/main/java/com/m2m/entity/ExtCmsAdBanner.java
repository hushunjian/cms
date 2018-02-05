package com.m2m.entity;

import com.m2m.domain.AdBanner;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsAdBanner {
    private Long total;

    private List<AdBanner> data = new ArrayList<>();
}
