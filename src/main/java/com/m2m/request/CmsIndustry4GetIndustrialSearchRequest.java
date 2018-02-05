package com.m2m.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsIndustry4GetIndustrialSearchRequest extends CmsCredentialRequest implements Serializable {
	private String firmName;
	private Date createdBegin;
	private Date createdEnd;
}
