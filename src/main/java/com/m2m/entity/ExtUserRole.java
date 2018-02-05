package com.m2m.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExtUserRole implements Serializable {
    private Integer userId;
    private List<Integer> roleIds;
}