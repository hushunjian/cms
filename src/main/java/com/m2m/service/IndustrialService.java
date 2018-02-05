package com.m2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2m.copier.IndustryCopier;
import com.m2m.domain.UserIndustry;
import com.m2m.domain.UserIndustryExample;
import com.m2m.entity.ExtCmsIndustrial;
import com.m2m.entity.ExtIndustry;
import com.m2m.entity.ExtIndustryQuery;
import com.m2m.mapper.ExtUserIndustryMapper;
import com.m2m.mapper.UserIndustryMapper;

@Service
public class IndustrialService extends BaseService {
	
	@Autowired
	private UserIndustryMapper userIndustryMapper;
	
	@Autowired
	private ExtUserIndustryMapper  extUserIndustryMapper;
	
	public ExtCmsIndustrial getIndustrialManagement() {
		ExtCmsIndustrial extCmsIndustrial = new ExtCmsIndustrial();
		UserIndustryExample example = new UserIndustryExample();
		UserIndustryExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort asc ");
		Long total = userIndustryMapper.countByExample(example);
		List<UserIndustry> userIndustrial = userIndustryMapper.selectByExample(example);
		List<ExtIndustry> extIndustrial = IndustryCopier.INSTANCE.asExtIndustrial(userIndustrial);
		extCmsIndustrial.setTotal(total);
		extCmsIndustrial.setData(extIndustrial);
		return extCmsIndustrial;
	}

	public void addIndustrial(UserIndustry userIndustry) {
		userIndustryMapper.insertSelective(userIndustry);
	}

	public void updateIndustrial(UserIndustry userIndustry) {
		userIndustryMapper.updateByPrimaryKeySelective(userIndustry);
	}

	public ExtCmsIndustrial getIndustrialManagementSearch(ExtIndustryQuery extIndustryQuery) {
		ExtCmsIndustrial extCmsIndustrial = new ExtCmsIndustrial();
		UserIndustryExample example = new UserIndustryExample();
		UserIndustryExample.Criteria criteria = example.createCriteria();
		if(extIndustryQuery.getFirmName()!=null){
			criteria.andIndustryNameLike("%"+extIndustryQuery.getFirmName()+"%");
		}
		if(extIndustryQuery.getCreatedBegin()!=null){
			criteria.andCreateTimeGreaterThanOrEqualTo(extIndustryQuery.getCreatedBegin());
		}
		if(extIndustryQuery.getCreatedEnd()!=null){
			criteria.andCreateTimeLessThanOrEqualTo(extIndustryQuery.getCreatedEnd());
		}
		example.setOrderByClause("sort asc ");
		Long total = userIndustryMapper.countByExample(example);
		List<UserIndustry> userIndustrial = userIndustryMapper.selectByExample(example);
		List<ExtIndustry> extIndustrial = IndustryCopier.INSTANCE.asExtIndustrial(userIndustrial);
		extCmsIndustrial.setTotal(total);
		extCmsIndustrial.setData(extIndustrial);
		return extCmsIndustrial;
	}

	public void sortEntry(List<UserIndustry> userIndustrial) {
		extUserIndustryMapper.sortEntry(userIndustrial);
	}

}
