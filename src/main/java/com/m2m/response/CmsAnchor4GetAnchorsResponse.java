package com.m2m.response;

import java.util.ArrayList;
import java.util.List;

import com.m2m.entity.ExtAnchor;

import lombok.Data;

@Data
public class CmsAnchor4GetAnchorsResponse {
	private long total;
	private List<ExtAnchor> data = new ArrayList<ExtAnchor>();
}
