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

import com.m2m.copier.AppUserCopier;
import com.m2m.entity.ExtAppUserQuery;
import com.m2m.entity.ExtCmsAllAppUser;
import com.m2m.entity.ExtCmsKingdomContent;
import com.m2m.entity.ExtCmsUserFragmentContent;
import com.m2m.entity.ExtUserRegisterChannel;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsAppUser4AddRobotRequest;
import com.m2m.request.CmsAppUser4DeleteUserFragmentContentRequest;
import com.m2m.request.CmsAppUser4DeleteUserKingdomContentRequest;
import com.m2m.request.CmsAppUser4GetAppUserSearchRequest;
import com.m2m.request.CmsAppUser4SetUserInviteStatusRequest;
import com.m2m.request.CmsAppUser4SetUserSilentStatusRequest;
import com.m2m.request.CmsAppUser4SetUserStatusRequest;
import com.m2m.request.CmsAppUser4SetUserVRequest;
import com.m2m.request.CmsAppUser4UpdateUserLevelRequest;
import com.m2m.response.CmsAppUser4GetAllAppUserResponse;
import com.m2m.response.CmsAppUser4GetUserFragmentContentResponse;
import com.m2m.response.CmsAppUser4GetUserKingdomContentResponse;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.service.AppUserService;

@RestController
@Transactional
@RequestMapping("/api/appuser")
public class AppUserController extends BaseController {
    @Autowired
    private AppUserService appUserService;

    @ResponseBody
    @RequestMapping(value = "getAllAppUsers")
    public Object getAllAppUsers(@NotNull @RequestParam String token,
                                 @NotNull @RequestParam Long pageIndex,
                                 @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAllAppUser extCmsAllAppUser = appUserService.getAllAppUsers(pageIndex, pageSize);
        CmsAppUser4GetAllAppUserResponse cmsAppUser4GetAllAppUserResponse = AppUserCopier.INSTANCE.asCmsAppUser4GetAllAppUserResponse(extCmsAllAppUser);
        return success(cmsAppUser4GetAllAppUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAppUserSearch", method = RequestMethod.POST)
    public Object getAppUserSearch(@Validated @RequestBody CmsAppUser4GetAppUserSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtAppUserQuery extAppUserQuery = AppUserCopier.INSTANCE.asExtAppUserQuery(request);
        ExtCmsAllAppUser extCmsAllAppUser = appUserService.getAppUserSearch(extAppUserQuery);
        CmsAppUser4GetAllAppUserResponse cmsAppUser4GetAllAppUserResponse = AppUserCopier.INSTANCE.asCmsAppUser4GetAllAppUserResponse(extCmsAllAppUser);
        return success(cmsAppUser4GetAllAppUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getUserKingdomContent")
    public Object getUserKingdomContent(@NotNull @RequestParam String token,
                                        @NotNull @RequestParam Long pageIndex,
                                        @NotNull @RequestParam Long pageSize,
                                        @NotNull @RequestParam Long uid) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdomContent extCmsKingdomContent = appUserService.getUserKingdomContent(uid, pageIndex, pageSize);
        CmsAppUser4GetUserKingdomContentResponse cmsAppUser4GetUserKingdomContentResponse = AppUserCopier.INSTANCE.asCmsAppUser4GetUserKingdomContentResponse(extCmsKingdomContent);
        return success(cmsAppUser4GetUserKingdomContentResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getUserFragmentContent")
    public Object getUserFragmentContent(@NotNull @RequestParam String token,
                                         @NotNull @RequestParam Long pageIndex,
                                         @NotNull @RequestParam Long pageSize,
                                         @NotNull @RequestParam Long uid) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsUserFragmentContent extCmsUserFragmentContent = appUserService.getUserFragmentContent(uid, pageIndex, pageSize);
        CmsAppUser4GetUserFragmentContentResponse cmsAppUser4GetUserFragmentContentResponse = AppUserCopier.INSTANCE.asCmsAppUser4GetUserFragmentContentResponse(extCmsUserFragmentContent);
        return success(cmsAppUser4GetUserFragmentContentResponse);
    }

    @ResponseBody
    @RequestMapping(value = "updateUserLevel", method = RequestMethod.POST)
    public Object updateUserLevel(@Validated @RequestBody CmsAppUser4UpdateUserLevelRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.updateUserLevel(request.getUid(), request.getNewLevel());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "setUserSilentStatus", method = RequestMethod.POST)
    public Object setUserSilentStatus(@Validated @RequestBody CmsAppUser4SetUserSilentStatusRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.setUserSilentStatus(request.getUid(), request.getSilentStatus());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "setUserV", method = RequestMethod.POST)
    public Object setUserV(@Validated @RequestBody CmsAppUser4SetUserVRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.setUserV(request.getUid(), request.getIsV());
        return success();
    }


    @ResponseBody
    @RequestMapping(value = "setUserStatus", method = RequestMethod.POST)
    public Object setUserStatus(@Validated @RequestBody CmsAppUser4SetUserStatusRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.setUserStatus(request.getUid(), request.getStatus());
        return success();
    }


    @ResponseBody
    @RequestMapping(value = "setUserInviteStatus", method = RequestMethod.POST)
    public Object setUserInviteStatus(@Validated @RequestBody CmsAppUser4SetUserInviteStatusRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.setUserInviteStatus(request.getUid(), request.getIsInvited());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "addRobot", method = RequestMethod.POST)
    public Object addRobot(@Validated @RequestBody CmsAppUser4AddRobotRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.addRobot(request.getPhoneBegin(), request.getCreatCount(), request.getOriginPassword());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteUserFragmentContent", method = RequestMethod.POST)
    public Object deleteUserFragmentContent(@Validated @RequestBody CmsAppUser4DeleteUserFragmentContentRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.deleteUserFragmentContent(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteUserKingdomContent", method = RequestMethod.POST)
    public Object deleteUserKingdomContent(@Validated @RequestBody CmsAppUser4DeleteUserKingdomContentRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appUserService.deleteUserKingdomContent(request.getId());
        return success();
    }
    
    @ResponseBody
    @RequestMapping(value = "getUsersRegisterChannel")
    public Object getUsersRegisterChannel(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtUserRegisterChannel> extUserRegisterChannels = appUserService.getUsersRegisterChannel();
        return success(extUserRegisterChannels);
    }
}
