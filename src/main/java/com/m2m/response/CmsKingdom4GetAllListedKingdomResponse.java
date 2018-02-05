package com.m2m.response;

import com.m2m.entity.ExtListedKingdom;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsKingdom4GetAllListedKingdomResponse {
    private Long total;
    private List<ExtListedKingdom> data = new ArrayList<ExtListedKingdom>();
}
