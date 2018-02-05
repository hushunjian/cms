package com.m2m.entity;

import lombok.Data;

@Data
public class ExtAnchorQuery {
	private Long pageIndex;
	private Long pageSize;
	private Long uid;
	private String realName;
	private String comment;
}
