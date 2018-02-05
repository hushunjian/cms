package com.m2m.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAnchor4GetAnchorInviteSearchRequest extends CmsCredentialRequest implements Serializable {
	@NotNull
	private Long pageIndex;
	@NotNull
	private Long pageSize;
	private Long uid;
	private Date createdBegin;
	private Date createdEnd;
}
