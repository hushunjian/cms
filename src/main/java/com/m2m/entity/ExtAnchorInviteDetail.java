package com.m2m.entity;

import java.util.Date;

import com.m2m.annotation.ExcelTitle;

import lombok.Data;

@Data
public class ExtAnchorInviteDetail {
	@ExcelTitle("主播UID")
	private Long uid;
	@ExcelTitle("主播昵称")
	private String nickName;
	@ExcelTitle("主播真实姓名")
	private String realName;
	@ExcelTitle("日期")
	private String dateTime;
	@ExcelTitle("被邀请人UID")
	private Long invitedUid;
	@ExcelTitle("被邀请人昵称")
	private String invitedNickName;
	@ExcelTitle("被邀请人注册时间")
	private Date createdAt;
	@ExcelTitle("被邀请人注册渠道")
	private String registerChannel;
	@ExcelTitle("被邀请人登录设备信息")
	private String logonDevice;
	@ExcelTitle("是否参与抽奖王国抽奖")
	private Integer isJoinRaffle;
}	
