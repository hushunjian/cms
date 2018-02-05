package com.m2m.response;

import com.m2m.entity.ExtEmotionPack;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsEmotionPackResponse implements Serializable {
    private Long total;
    private List<ExtEmotionPack> data = new ArrayList<ExtEmotionPack>();
}
