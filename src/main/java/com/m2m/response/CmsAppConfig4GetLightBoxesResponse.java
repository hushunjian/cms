package com.m2m.response;

import com.m2m.entity.ExtLightBox;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsAppConfig4GetLightBoxesResponse {
    private Long total;

    private List<ExtLightBox> data = new ArrayList<ExtLightBox>();
}
