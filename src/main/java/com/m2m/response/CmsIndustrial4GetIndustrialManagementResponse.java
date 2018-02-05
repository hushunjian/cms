package com.m2m.response;

import java.util.ArrayList;
import java.util.List;

import com.m2m.entity.ExtIndustry;

import lombok.Data;

@Data
public class CmsIndustrial4GetIndustrialManagementResponse {
	private long total;
	private List<ExtIndustry> data = new ArrayList<ExtIndustry>();
}
