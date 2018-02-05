package com.m2m.entity;

import java.util.ArrayList;
import java.util.List;

import com.m2m.entity.ExtAnchorInvite;

import lombok.Data;

@Data
public class ExtCmsAnchorInvite {
	private Long total;
	private List<ExtAnchorInvite> data = new ArrayList<ExtAnchorInvite>();
}
