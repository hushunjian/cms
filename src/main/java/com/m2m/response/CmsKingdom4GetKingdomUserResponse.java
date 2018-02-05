package com.m2m.response;

import com.m2m.entity.ExtKingdomReview;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsKingdom4GetKingdomUserResponse {
    private Long total;
    private List<ExtKingdomReview> data = new ArrayList<>();
}
