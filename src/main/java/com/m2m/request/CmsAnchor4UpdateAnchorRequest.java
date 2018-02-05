package com.m2m.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAnchor4UpdateAnchorRequest extends CmsCredentialRequest implements Serializable {
	@NotNull
	private Long id;
	private Long uid;
	private String realName;
	private String comment;
}
