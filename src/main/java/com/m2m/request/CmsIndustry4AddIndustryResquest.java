package com.m2m.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsIndustry4AddIndustryResquest extends CmsCredentialRequest implements Serializable {
	@NotNull
	private String firmName;
	@NotNull
	private String coverImage;
	private String introduce;
	private Integer sort;
}
