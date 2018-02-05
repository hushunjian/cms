package com.m2m.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

import com.m2m.Constant;
import com.m2m.copier.FeatureCopier;
import com.m2m.copier.RoleCopier;
import com.m2m.copier.UserCopier;
import com.m2m.domain.CmsCredential;
import com.m2m.domain.CmsRole;
import com.m2m.domain.CmsUser;
import com.m2m.entity.ExtCmsFeature;
import com.m2m.entity.ExtCmsFeatureGroup;
import com.m2m.exception.AuthenticationException;
import com.m2m.exception.InvalidPasswordException;
import com.m2m.exception.SystemException;
import com.m2m.mail.ExMailService;
import com.m2m.mail.Mailer;
import com.m2m.request.CmsUser4AddRequest;
import com.m2m.request.CmsUser4DeleteRequest;
import com.m2m.request.CmsUser4LoginRequest;
import com.m2m.request.CmsUser4PasswordRequest;
import com.m2m.request.CmsUser4UpdateRequest;
import com.m2m.request.CmsUserFeatureRequest;
import com.m2m.request.CmsUserRestPasswordRequest;
import com.m2m.request.CmsUserRoleRequest;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsFeatureGroupResponse;
import com.m2m.response.CmsRoleResponse;
import com.m2m.response.CmsUser4LoginResponse;
import com.m2m.response.CmsUserResponse;
import com.m2m.service.CmsFeatureService;
import com.m2m.service.CmsUserService;
import com.m2m.util.MD5;
import com.m2m.util.Password;

@RestController
@Transactional
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    private CmsUserService userService;
    @Autowired
    private CmsFeatureService cmsFeatureService;
    @Autowired
    private ExMailService exMailService;

    @ResponseBody
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Object test() throws SystemException {
        Map<String, Object> rst = new HashMap<>();
        rst.put("message", "ok");
        return rst;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@Validated @RequestBody CmsUser4LoginRequest imsUserRequest) throws SystemException {
        String password = MD5.getMD5InHex(imsUserRequest.getPassword());
        CmsUser cmsUserFound = userService.find(imsUserRequest.getName(), password);
        if (cmsUserFound != null) {
            CmsCredential cmsCredential = cmsCredentialService.allocate(cmsUserFound.getId(), imsUserRequest.getName());
            CmsUser cmsUser = userService.getUserById(cmsCredential.getUserId());
            CmsUser4LoginResponse cmsUserResponse = UserCopier.INSTANCE.asCmsUser4LoginResponse(cmsUser);
            cmsUserResponse.setToken(cmsCredential.getToken());
            return success(cmsUserResponse);
        } else {
            throw new AuthenticationException();
        }
    }

    @ResponseBody
    @RequestMapping(value = "logout")
    public Object logout(@NotNull @RequestParam String token) throws SystemException {
        cmsCredentialService.remove(token);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public Object updatePassword(@Validated @RequestBody CmsUser4PasswordRequest imsUser4PasswordVO)
            throws SystemException {
        CmsCredentialResponse cmsCredentialResponse = validate(imsUser4PasswordVO.getToken());
        if(!Pattern.matches(Constant.REGEX_PASSWORD, imsUser4PasswordVO.getNewPassword())){
        	throw new InvalidPasswordException();
        }
        CmsUser cmsUserFound = userService.getUserById(cmsCredentialResponse.getId());
        String oldPassword = cmsUserFound.getPassword();
        if (oldPassword.equals(MD5.getMD5InHex(imsUser4PasswordVO.getPassword()))) {
            cmsUserFound.setPassword(MD5.getMD5InHex(imsUser4PasswordVO.getNewPassword()));
            userService.updateUser(cmsUserFound);
        } else {
            throw new AuthenticationException();
        }
        return success();
    }

    @RequestMapping("getAllUsers")
    public Object getAllUsers(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER);
        List<CmsUser> cmsUsers = userService.getAllUsers();
        List<CmsUserResponse> cmsUserResponses = UserCopier.INSTANCE.asCmsUserResponses(cmsUsers);
        return success(cmsUserResponses);
    }

    @RequestMapping("getUserById")
    public Object getUserById(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER);
        CmsUser cmsUser = userService.getUserById(credential.getId());
        CmsUserResponse cmsUserResponse = UserCopier.INSTANCE.asCmsUserResponse(cmsUser);
        return success(cmsUserResponse);
    }

    @RequestMapping("getMyRoles")
    public Object getMyRoles(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse cmsCredentialResponse = validate(token);
        Integer userId = cmsCredentialResponse.getId();
        List<CmsRole> cmsRoles = userService.getUserRoles(userId);
        List<CmsRoleResponse> cmsRoleResponses = RoleCopier.INSTANCE.asImsRoleResponses(cmsRoles);
        return success(cmsRoleResponses);
    }

    @RequestMapping("getUserRoles")
    public Object getUserRoles(@NotNull @RequestParam String token, @NotNull @RequestParam Integer userId)
            throws SystemException {
        CmsCredentialResponse credential = validate(token);
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER_FEATURE);
        List<CmsRole> cmsRoles = userService.getUserRoles(userId);
        List<CmsRoleResponse> cmsRoleResponses = RoleCopier.INSTANCE.asImsRoleResponses(cmsRoles);
        return success(cmsRoleResponses);
    }

    @RequestMapping(value = "getMyFeatures")
    public Object getUserFeatures(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse cmsCredentialResponse = validate(token);
        Integer userId = cmsCredentialResponse.getId();
        List<ExtCmsFeatureGroup> extCmsFeatureGroups = userService.getUserFeatures(userId);
        List<CmsFeatureGroupResponse> cmsFeatureGroupResponses = FeatureCopier.INSTANCE.asCmsFeatureGroupResponses(extCmsFeatureGroups);
        return success(cmsFeatureGroupResponses);
    }

    @RequestMapping(value = "getUserFeatures")
    public Object getUserFeaturesByUserId(@NotNull @RequestParam String token, @NotNull @RequestParam Integer userId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtCmsFeatureGroup> extCmsFeatureGroups = userService.getUserFeatures(userId);
        List<CmsFeatureGroupResponse> cmsFeatureGroupResponses = FeatureCopier.INSTANCE.asCmsFeatureGroupResponses(extCmsFeatureGroups);
        return success(cmsFeatureGroupResponses);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Object addUser(@Validated @RequestBody CmsUser4AddRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER);
        String password = Password.random();
        CmsUser cmsUser = UserCopier.INSTANCE.asImsUser(request);
        cmsUser.setPassword(MD5.getMD5InHex(password));
        userService.addUser(cmsUser);
        String receiver = String.format("%s%s", cmsUser.getName(), "@mail.me-to-me.com");
        String subject = "CMS用户信息";
        String message = String.format("用户名：%s\n账号密码：%s", cmsUser.getName(),password);
        Mailer.send(exMailService, receiver, subject, message);
        return success();
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public Object deleteUser(@Validated @RequestBody CmsUser4DeleteRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER);
        CmsUser cmsUser = UserCopier.INSTANCE.asImsUser(request);
        userService.deleteImsUser(cmsUser.getId());
        return success();
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public Object updateUser(@Validated @RequestBody CmsUser4UpdateRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER);
        CmsUser cmsUser = UserCopier.INSTANCE.asImsUser(request);
        userService.updateImsUser(cmsUser);
        return success();
    }

    @RequestMapping(value = "updateUserFeatures", method = RequestMethod.POST)
    public Object updateUserFeatures(@Validated @RequestBody CmsUserFeatureRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER_FEATURE);
        List<ExtCmsFeature> extCmsFeatures = FeatureCopier.INSTANCE.asExtCmsFeatures(request.getFeatures());
        userService.updateUserFeatures(request.getUserId(), extCmsFeatures);
        return success();
    }

    @RequestMapping(value = "updateUserRoles", method = RequestMethod.POST)
    public Object updateUserRoles(@Validated @RequestBody CmsUserRoleRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER_FEATURE);
        userService.updateUserRoles(request.getUserId(), request.getRoleIds());
        return success();
    }
    
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public Object resetPassword(@Validated @RequestBody CmsUserRestPasswordRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        cmsFeatureService.hasFeature(credential.getId(), Constant.PRIVILEGE_USER_FEATURE);
        String password = Password.random();
        CmsUser cmsUser = userService.getUserById(request.getId());
        cmsUser.setPassword(MD5.getMD5InHex(password));
        userService.resetPassword(cmsUser);
        String receiver = String.format("%s%s", cmsUser.getName(), "@mail.me-to-me.com");
        String subject = "CMS用户信息";
        String message = String.format("用户名：%s\n重置后密码：%s", cmsUser.getName(),password);
        Mailer.send(exMailService, receiver, subject, message);
        return success();
    }

}
