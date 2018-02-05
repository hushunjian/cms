package com.m2m.entity;

import lombok.Data;

import java.util.Date;

import com.m2m.annotation.ExcelTitle;

@Data
public class ExtKingdomReview {
	@ExcelTitle("发言人UID")
    private Long uid;
	@ExcelTitle("发言人昵称")
    private String userNick;
	@ExcelTitle("发言人米汤账号")
    private Integer meNumber;
	@ExcelTitle("发言人注册时间")
    private Date joinAt;
	@ExcelTitle("发言次数")
    private Integer reviewCount;
	@ExcelTitle("是否第一次发言(1:是;2:否)")
    private Integer isFirstReview;
	@ExcelTitle("注册渠道")
    private String registerChannel;
	@ExcelTitle("登录设备信息")
    private String logonDevice;
	@ExcelTitle("发言日期")
    private String dateTime;
    
}
