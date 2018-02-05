package com.m2m.response;

import com.m2m.entity.ExtUserBehaviour;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsBehaviour4GetUserBehavioursResponse {
    private Long total;
    private List<ExtUserBehaviour> data = new ArrayList<ExtUserBehaviour>();
}
