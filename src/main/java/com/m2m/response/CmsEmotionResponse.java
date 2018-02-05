package com.m2m.response;

import com.m2m.entity.ExtEmotion;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsEmotionResponse implements Serializable {
    private Long total;
    private List<ExtEmotion> data = new ArrayList<ExtEmotion>();
}
