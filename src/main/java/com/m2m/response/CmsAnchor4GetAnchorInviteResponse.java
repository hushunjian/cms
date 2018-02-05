package com.m2m.response;

import java.util.ArrayList;
import java.util.List;

import com.m2m.entity.ExtAnchorInvite;

import lombok.Data;

@Data
public class CmsAnchor4GetAnchorInviteResponse {
	private Long total;
	private List<ExtAnchorInvite> data = new ArrayList<ExtAnchorInvite>();
}
