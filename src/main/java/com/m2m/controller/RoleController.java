package com.m2m.controller;

import com.m2m.Constant;
import com.m2m.copier.FeatureCopier;
import com.m2m.copier.RoleCopier;
import com.m2m.domain.CmsRole;
import com.m2m.entity.ExtCmsFeature;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsRole4AddRequest;
import com.m2m.request.CmsRole4DeleteRequest;
import com.m2m.request.CmsRoleFeatureRequest;
import com.m2m.request.CmsRoleRequest;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsFeatureResponse;
import com.m2m.response.CmsRoleResponse;
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
@RequestMapping("/api/role")
public class RoleController extends BaseController {
    @Autowired
    private CmsRoleService cmsRoleService;
    @Autowired
    private CmsFeatureService cmsFeatureService;
    @Autowired
    private CmsUserService cmsUserService;

    @RequestMapping("getAllRoles")
    public Object getAllRoles(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_ROLE);
        List<CmsRole> cmsRoles = cmsRoleService.getAllRoles();
        List<CmsRoleResponse> cmsRoleRespons = RoleCopier.INSTANCE.asImsRoleResponses(cmsRoles);
        return success(cmsRoleRespons);
    }

    @RequestMapping("getRoleFeatures")
    public Object getRoleFeatures(@NotNull @RequestParam String token, @NotNull @RequestParam Integer roleId)
            throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_ROLE);
        List<ExtCmsFeature> userFeatures = cmsRoleService.getRoleFeatures(roleId);
        List<CmsFeatureResponse> cmsFeatureResponse = FeatureCopier.INSTANCE.asImsFeatureResponses(userFeatures);
        return success(cmsFeatureResponse);
    }

    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public Object addRole(@Validated @RequestBody CmsRole4AddRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_ROLE);
        CmsRole cmsRole = RoleCopier.INSTANCE.asImsRole(request);
        cmsRoleService.addRole(cmsRole);
        return success();
    }

    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    public Object deleteRole(@Validated @RequestBody CmsRole4DeleteRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_ROLE);
        Integer roleId = request.getId();
        cmsFeatureService.deleteRoleFeatureByRoleId(roleId);
        cmsUserService.deleteUserRoleByRoleId(roleId);
        cmsRoleService.deleteImsRole(roleId);
        return success();
    }

    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    public Object updateRole(@Validated @RequestBody CmsRoleRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_ROLE);
        CmsRole cmsRole = RoleCopier.INSTANCE.asImsRole(request);
        cmsRoleService.updateImsRole(cmsRole);
        return success();
    }

    @RequestMapping(value = "updateRoleFeatures", method = RequestMethod.POST)
    public Object updateRoleFeatures(@Validated @RequestBody CmsRoleFeatureRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_ROLE);
        cmsRoleService.updateRoleFeatures(request.getRoleId(), request.getFeatureIds());
        return success();
    }
}
