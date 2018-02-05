package com.m2m.response;

import com.m2m.entity.ExtAdInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CmsAdvertisementResponse implements Serializable {
    private Long total;
    private List<ExtAdInfo> data;
}