package com.m2m.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestExcelRequest extends CmsCredentialRequest implements Serializable {
	private String tagName;

    private Date startTime;

    private Date endTime;

    private Integer isSystem;

    private Integer isRecommend;

    private Integer topicCountStart;

    private Integer topicCountEnd;

    private Integer parentTagId;

    private Integer noParentTag;
}
