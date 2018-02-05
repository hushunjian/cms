package com.m2m.response;

import com.m2m.entity.ExtGift;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsGift4GetAllGiftResponse implements Serializable {
    private Long total;
    private List<ExtGift> data = new ArrayList<ExtGift>();
}
