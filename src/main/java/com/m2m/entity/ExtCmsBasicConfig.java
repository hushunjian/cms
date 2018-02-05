package com.m2m.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsBasicConfig {
    private String name;
    private Integer groupId;
    private List<Item> items = new ArrayList<>();

    @Data
    public static class Item implements Serializable {
        private String key;
        private String value;
        private String des;
    }
}
