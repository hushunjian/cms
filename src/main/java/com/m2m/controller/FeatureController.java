package com.m2m.controller;

import com.m2m.Constant;
import com.m2m.copier.FeatureCopier;
import com.m2m.domain.CmsFeature;
import com.m2m.entity.ExtCmsFeatureGroup;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsFeature4AddRequest;
import com.m2m.request.CmsFeature4DeleteRequest;
import com.m2m.request.CmsFeature4UpdateRequest;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsFeatureGroupResponse;
import com.m2m.service.CmsFeatureService;
import com.m2m.service.CmsRoleService;
import com.m2m.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/feature")
public class FeatureController extends BaseController {
    @Autowired
    private CmsFeatureService cmsFeatureService;
    @Autowired
    private CmsRoleService cmsRoleService;
    @Autowired
    private CmsUserService cmsUserService;

    @RequestMapping("getAllFeatures")
    public Object getAllFeatures(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_FEATURE);
        List<ExtCmsFeatureGroup> extCmsFeatureGroups = cmsFeatureService.getAllFeatures();
        List<CmsFeatureGroupResponse> cmsFeatureGroupResponses = FeatureCopier.INSTANCE.asCmsFeatureGroupResponses(extCmsFeatureGroups);
        return success(cmsFeatureGroupResponses);
    }

    @RequestMapping("getFeatureById")
    public Object getFeatureById(@NotNull @RequestParam String token, @NotNull @RequestParam Integer featureId)
            throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_FEATURE);
        CmsFeature cmsFeature = cmsFeatureService.getFeatureById(featureId);
        return success(cmsFeature);
    }

    @RequestMapping(value = "addFeature", method = RequestMethod.POST)
    public Object addFeature(@Validated @RequestBody CmsFeature4AddRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_FEATURE);
        CmsFeature cmsFeature = FeatureCopier.INSTANCE.asImsFeature(request);
        cmsFeatureService.addFeature(cmsFeature);
        return success();
    }

    @RequestMapping(value = "deleteFeature", method = RequestMethod.POST)
    public Object deleteFeature(@Validated @RequestBody CmsFeature4DeleteRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_FEATURE);
        Integer featureId = request.getId();
        cmsUserService.deleteUserFeatureByFeatureId(featureId);
        cmsRoleService.deleteRoleFeatureByFeatureId(featureId);
        cmsFeatureService.deleteFeature(featureId);
        return success();
    }

    @RequestMapping(value = "updateFeature", method = RequestMethod.POST)
    public Object updateFeature(@Validated @RequestBody CmsFeature4UpdateRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_FEATURE);
        CmsFeature cmsFeature = FeatureCopier.INSTANCE.asImsFeature(request);
        cmsFeatureService.updateFeature(cmsFeature);
        return success();
    }
}

