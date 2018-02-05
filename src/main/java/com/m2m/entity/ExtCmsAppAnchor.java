package com.m2m.entity;

import java.util.ArrayList;
import java.util.List;

import com.m2m.entity.ExtAnchor;

import lombok.Data;

@Data
public class ExtCmsAppAnchor {
	private Long total;
	private List<ExtAnchor> data = new ArrayList<ExtAnchor>();
}
