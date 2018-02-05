package com.m2m.response;

import com.m2m.entity.ExtTagKing;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsTag4GetAllTagKingdomResponse implements Serializable {
    private Long total;
    private List<ExtTagKing> data = new ArrayList<ExtTagKing>();
}
