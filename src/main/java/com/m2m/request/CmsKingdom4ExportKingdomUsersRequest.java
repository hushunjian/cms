package com.m2m.request;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4ExportKingdomUsersRequest extends CmsCredentialRequest implements Serializable {
	private String fragment;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date joinBegin;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date joinEnd;
	private Integer isFirstReview;
	private Long topicId;
}
