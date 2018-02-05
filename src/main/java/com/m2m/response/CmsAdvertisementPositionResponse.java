package com.m2m.response;

import com.m2m.entity.ExtAdBanner;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CmsAdvertisementPositionResponse implements Serializable {
    private Long total;
    private List<ExtAdBanner> data;
}