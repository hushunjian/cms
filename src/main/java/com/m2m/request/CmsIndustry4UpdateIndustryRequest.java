package com.m2m.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsIndustry4UpdateIndustryRequest extends CmsCredentialRequest implements Serializable {
	@NotNull
	private long id;
	private String firmName;
	private String coverImage;
	private String introduce;
}
