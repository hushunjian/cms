package com.m2m.response;

import com.m2m.entity.ExtKingdomValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsKindom4GetKingdomValueResponse {
    private Long total;
    private List<ExtKingdomValue> data = new ArrayList<ExtKingdomValue>();
}
