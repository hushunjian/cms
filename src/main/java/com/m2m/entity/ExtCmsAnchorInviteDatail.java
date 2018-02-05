package com.m2m.entity;

import java.util.ArrayList;
import java.util.List;

import com.m2m.entity.ExtAnchorInviteDetail;

import lombok.Data;

@Data
public class ExtCmsAnchorInviteDatail {
	private Long total;
	private List<ExtAnchorInviteDetail> data = new ArrayList<ExtAnchorInviteDetail>();
}
