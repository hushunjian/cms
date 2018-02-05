package com.m2m.response;

import com.m2m.entity.ExtTag;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsTag4GetAllTagsResponse implements Serializable {
    private Long total;
    private List<ExtTag> data = new ArrayList<ExtTag>();
}
