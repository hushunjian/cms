package com.m2m.response;

import com.m2m.entity.ExtUserKingdomContent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CmsAppUser4GetUserKingdomContentResponse {

    private Long total;

    private List<ExtUserKingdomContent> data = new ArrayList<>();
}
