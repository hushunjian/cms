package com.m2m.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m2m.copier.IndustryCopier;
import com.m2m.domain.UserIndustry;
import com.m2m.entity.ExtCmsIndustrial;
import com.m2m.entity.ExtIndustryQuery;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsIndustry4AddIndustryResquest;
import com.m2m.request.CmsIndustry4GetIndustrialSearchRequest;
import com.m2m.request.CmsIndustry4SortEntryRequest;
import com.m2m.request.CmsIndustry4UpdateIndustryRequest;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsIndustrial4GetIndustrialManagementResponse;
import com.m2m.service.IndustrialService;

@RestController
@Transactional
@RequestMapping("/api/industrial")
public class IndustrialController extends BaseController{
	
	@Autowired
	private IndustrialService industrialService;
	
	@ResponseBody
    @RequestMapping(value = "getIndustrialManage")
	public Object getIndustrialManage(@NotNull @RequestParam String token) throws SystemException{
		CmsCredentialResponse credential = validate(token);
		ExtCmsIndustrial extCmsIndustrial = industrialService.getIndustrialManagement();
		CmsIndustrial4GetIndustrialManagementResponse cmsIndustrial4GetIndustrialManagementResponse = IndustryCopier.INSTANCE.asCmsIndustrial4GetIndustrialManagementResponse(extCmsIndustrial);
		return success(cmsIndustrial4GetIndustrialManagementResponse);
	}
	
	@ResponseBody
	@RequestMapping(value = "addIndustrial", method = RequestMethod.POST)
	public Object addIndustrial(@Validated @RequestBody CmsIndustry4AddIndustryResquest request) throws SystemException{
		CmsCredentialResponse credential = validate(request.getToken());
		UserIndustry userIndustry = IndustryCopier.INSTANCE.asUserIndustry(request);
		industrialService.addIndustrial(userIndustry);
		return success();
	}
	 
	@ResponseBody
	@RequestMapping(value = "updateIndustrial", method = RequestMethod.POST)
	public Object updateIndustrial(@Validated @RequestBody CmsIndustry4UpdateIndustryRequest request) throws SystemException{
		CmsCredentialResponse credential = validate(request.getToken());
		UserIndustry userIndustry = IndustryCopier.INSTANCE.asUserIndustry(request);
		industrialService.updateIndustrial(userIndustry);
		return success();
	}
	 
	@ResponseBody
	@RequestMapping(value = "getIndustrialManageSearch", method = RequestMethod.POST)
	public Object getIndustrialManageSearch(@Validated @RequestBody CmsIndustry4GetIndustrialSearchRequest request) throws SystemException{
		CmsCredentialResponse credential = validate(request.getToken());
		ExtIndustryQuery extIndustryQuery = IndustryCopier.INSTANCE.asExtIndustryQuery(request);
		ExtCmsIndustrial extCmsIndustrial = industrialService.getIndustrialManagementSearch(extIndustryQuery);
		CmsIndustrial4GetIndustrialManagementResponse cmsIndustrial4GetIndustrialManagementResponse = IndustryCopier.INSTANCE.asCmsIndustrial4GetIndustrialManagementResponse(extCmsIndustrial);
		return success(cmsIndustrial4GetIndustrialManagementResponse);
	}
	
	@ResponseBody
	@RequestMapping(value = "sortEntry", method = RequestMethod.POST)
	public Object sortEntry(@Validated @RequestBody CmsIndustry4SortEntryRequest request) throws SystemException{
		CmsCredentialResponse credential = validate(request.getToken());
		List<UserIndustry> userIndustrial = IndustryCopier.INSTANCE.asUserIndustrial(request.getTargetData());
		industrialService.sortEntry(userIndustrial);
		return success();
	}
}
