package com.m2m.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ExtGetAnchorInviteQuery {
	private Long pageIndex;
	private Long pageSize;
	private Long uid;
	private Date createdBegin;
	private Date createdEnd;
}
