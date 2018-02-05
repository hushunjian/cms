package com.m2m.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ExtIndustry {
	private long id;
	private String firmName;
	private String coverImage;
	private String introduce;
	private Date createdAt;
	private int sort;
}
