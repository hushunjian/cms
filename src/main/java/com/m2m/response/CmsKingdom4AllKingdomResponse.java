package com.m2m.response;

import com.m2m.entity.ExtKingdom;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsKingdom4AllKingdomResponse implements Serializable {
    private Long total;
    private List<ExtKingdom> data = new ArrayList<ExtKingdom>();
}
