package com.m2m.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.m2m.copier.AppAnchorCopier;
import com.m2m.domain.AppAnchor;
import com.m2m.domain.UserProfile;
import com.m2m.entity.ExcelData;
import com.m2m.entity.ExtAnchorInviteDetail;
import com.m2m.entity.ExtAnchorInviteDetailQuery;
import com.m2m.entity.ExtAnchorQuery;
import com.m2m.entity.ExtCmsAnchorInvite;
import com.m2m.entity.ExtCmsAnchorInviteDatail;
import com.m2m.entity.ExtCmsAppAnchor;
import com.m2m.entity.ExtGetAnchorInviteQuery;
import com.m2m.exception.NoExportDataException;
import com.m2m.exception.SystemException;
import com.m2m.exception.UserNotFindException;
import com.m2m.request.CmsAnchor4AddAnchorRequest;
import com.m2m.request.CmsAnchor4DeleteAnchorRequest;
import com.m2m.request.CmsAnchor4ExportAnchorInviteDetailRequest;
import com.m2m.request.CmsAnchor4GetAnchorInviteDetailSearchRequest;
import com.m2m.request.CmsAnchor4GetAnchorInviteSearchRequest;
import com.m2m.request.CmsAnchor4GetAnchorSearchRequest;
import com.m2m.request.CmsAnchor4UpdateAnchorRequest;
import com.m2m.response.CmsAnchor4GetAnchorInviteDatailResponse;
import com.m2m.response.CmsAnchor4GetAnchorInviteResponse;
import com.m2m.response.CmsAnchor4GetAnchorsResponse;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.service.AnchorService;
import com.m2m.util.ExportExcelUtils;

@RestController
@Transactional
@RequestMapping("/api/anchor")
public class AnchorController extends BaseController {
    @Autowired
    private AnchorService anchorService;

    @ResponseBody
    @RequestMapping(value = "getAnchors")
    public Object getAnchors(@NotNull @RequestParam String token,
                             @NotNull @RequestParam Long pageIndex,
                             @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAppAnchor extCmsAppAnchor = anchorService.getAnchorsByPage(pageIndex,pageSize);
        CmsAnchor4GetAnchorsResponse cmsAnchor4GetAnchorsResponse = AppAnchorCopier.INSTANCE.asCmsAnchor4GetAnchorsResponse(extCmsAppAnchor);
        return success(cmsAnchor4GetAnchorsResponse);
    }
    
    @ResponseBody
    @RequestMapping(value = "getAnchorSearch", method = RequestMethod.POST)
    public Object getAnchorSearch(@Validated @RequestBody CmsAnchor4GetAnchorSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtAnchorQuery extAnchorQuery = AppAnchorCopier.INSTANCE.asExtAnchorQuery(request); 
        ExtCmsAppAnchor extCmsAppAnchor = anchorService.getAnchorsByCondition(extAnchorQuery);
        CmsAnchor4GetAnchorsResponse cmsAnchor4GetAnchorsResponse = AppAnchorCopier.INSTANCE.asCmsAnchor4GetAnchorsResponse(extCmsAppAnchor);
        return success(cmsAnchor4GetAnchorsResponse);
    }

    @ResponseBody
    @RequestMapping(value = "addAnchor", method = RequestMethod.POST)
    public Object addAnchor(@Validated @RequestBody CmsAnchor4AddAnchorRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        UserProfile user = anchorService.isValidUid(request.getUid());
        if(user!=null){
        	AppAnchor appAnchor = AppAnchorCopier.INSTANCE.asAppAnchor(request);
        	anchorService.addAnchor(appAnchor);
        }else{
        	throw new UserNotFindException();
        }
        return success();
    }
    
    @ResponseBody
    @RequestMapping(value = "deleteAnchor", method = RequestMethod.POST)
    public Object deleteAnchor(@Validated @RequestBody CmsAnchor4DeleteAnchorRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        anchorService.deleteAnchor(request.getId());
        return success();
    }
    
    @ResponseBody
    @RequestMapping(value = "updateAnchor", method = RequestMethod.POST)
    public Object updateAnchor(@Validated @RequestBody CmsAnchor4UpdateAnchorRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        if(request.getUid()!=null){
        	UserProfile user = anchorService.isValidUid(request.getUid());
        	if(user==null){
               throw new UserNotFindException();
        	}
        }
    	AppAnchor appAnchor = AppAnchorCopier.INSTANCE.asAppAnchor(request);
    	anchorService.updateAnchor(appAnchor);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getAnchorInvite")
    public Object getAnchorInvite(@NotNull @RequestParam String token,
                             	  @NotNull @RequestParam Long pageIndex,
                             	  @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAnchorInvite extCmsAnchorInvite = anchorService.getAnchorInviteByPage(pageIndex,pageSize);
        CmsAnchor4GetAnchorInviteResponse cmsAnchor4GetAnchorInviteResponse = AppAnchorCopier.INSTANCE.asCmsAnchor4GetAnchorInviteResponse(extCmsAnchorInvite);
        return success(cmsAnchor4GetAnchorInviteResponse);
    }
    
    @ResponseBody
    @RequestMapping(value = "getAnchorInviteSearch", method = RequestMethod.POST)
    public Object getAnchorInviteSearch(@Validated @RequestBody CmsAnchor4GetAnchorInviteSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtGetAnchorInviteQuery extGetAnchorInviteQuery = AppAnchorCopier.INSTANCE.asExtGetAnchorInviteQuery(request);
        ExtCmsAnchorInvite extCmsAnchorInvite = anchorService.getAnchorInviteByCondition(extGetAnchorInviteQuery);
        CmsAnchor4GetAnchorInviteResponse cmsAnchor4GetAnchorInviteResponse = AppAnchorCopier.INSTANCE.asCmsAnchor4GetAnchorInviteResponse(extCmsAnchorInvite);
        return success(cmsAnchor4GetAnchorInviteResponse);
    }
    
    @ResponseBody
    @RequestMapping(value = "getAnchorInviteDetail")
    public Object getAnchorInviteDetail(@NotNull @RequestParam String token,
    									@NotNull @RequestParam Long pageIndex,
    									@NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAnchorInviteDatail extCmsAnchorInviteDatail = anchorService.getAnchorInviteDetailByPage(pageIndex,pageSize);
        CmsAnchor4GetAnchorInviteDatailResponse cmsAnchor4GetAnchorInviteDatailResponse = AppAnchorCopier.INSTANCE.asCmsAnchor4GetAnchorInviteDatailResponse(extCmsAnchorInviteDatail);
        return success(cmsAnchor4GetAnchorInviteDatailResponse);
    }
    
    @ResponseBody
    @RequestMapping(value = "getAnchorInviteDetailSearch", method = RequestMethod.POST)
    public Object getAnchorInviteDetailSearch(@Validated @RequestBody CmsAnchor4GetAnchorInviteDetailSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtAnchorInviteDetailQuery extAnchorInviteDetailQuery = AppAnchorCopier.INSTANCE.asExtAnchorInviteDetailQuery(request);
        ExtCmsAnchorInviteDatail extCmsAnchorInviteDatail = anchorService.getAnchorInviteDetailByCondition(extAnchorInviteDetailQuery);
        CmsAnchor4GetAnchorInviteDatailResponse cmsAnchor4GetAnchorInviteDatailResponse = AppAnchorCopier.INSTANCE.asCmsAnchor4GetAnchorInviteDatailResponse(extCmsAnchorInviteDatail);
        return success(cmsAnchor4GetAnchorInviteDatailResponse);
    }
    
    @ResponseBody
    @RequestMapping(value = "exportAnchorInviteDetail")
    public Object exportAnchorInviteDetail(HttpServletResponse response,CmsAnchor4ExportAnchorInviteDetailRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtAnchorInviteDetailQuery extAnchorInviteDetailQuery = AppAnchorCopier.INSTANCE.asExtAnchorInviteDetailQuery(request);
        List<ExtAnchorInviteDetail> extAnchorInviteDetails = anchorService.exportAnchorInviteDetail(extAnchorInviteDetailQuery);
        if(extAnchorInviteDetails.size()>0){
        	ExcelData data = new ExcelData();
        	List<Object> beans = new ArrayList<Object>();
        	for(ExtAnchorInviteDetail extAnchorInviteDetail : extAnchorInviteDetails){
        		beans.add(extAnchorInviteDetail);
        	}
        	data = ExportExcelUtils.setExcelData(beans);
        	ExportExcelUtils.exportExcel(response,"exportExcel.xlsx",data);
        }else{
        	throw new NoExportDataException();
        }
        return success();
    }
}
