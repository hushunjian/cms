package com.m2m.response;

import com.m2m.entity.ExtUserFragmentContent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsAppUser4GetUserFragmentContentResponse {
    private Long total;

    private List<ExtUserFragmentContent> data = new ArrayList<>();
}
