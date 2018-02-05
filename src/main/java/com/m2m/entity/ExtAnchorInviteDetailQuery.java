package com.m2m.entity;

import java.util.Date;

import lombok.Data;
@Data
public class ExtAnchorInviteDetailQuery {
	private Long pageIndex;
	private Long pageSize;
	private Long uid;
	private Date joinBegin;
	private Date joinEnd;
	private Long raffleTopicId;
}
