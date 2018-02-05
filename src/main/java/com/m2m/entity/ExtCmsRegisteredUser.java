package com.m2m.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtCmsRegisteredUser {
    private long total;
    private List<ExtRegisteredUser> data = new ArrayList<ExtRegisteredUser>();
}
