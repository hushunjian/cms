package com.m2m.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ExtIndustryQuery {
	private String firmName;
	private Date createdBegin;
	private Date createdEnd;
}
