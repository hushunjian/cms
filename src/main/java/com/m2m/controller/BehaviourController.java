package com.m2m.controller;

import com.m2m.copier.BehaviourCopier;
import com.m2m.entity.*;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsBehaviour4GetUserBehavioursSearchRequest;
import com.m2m.request.CmsBehaviour4GetUserBehavioursTotalSearchRequest;
import com.m2m.request.CmsBehaviour4RegisteredUserSearchRequest;
import com.m2m.response.CmsBehaviour4GetRegisteredUserResponse;
import com.m2m.response.CmsBehaviour4GetUserBehaviourTotalResponse;
import com.m2m.response.CmsBehaviour4GetUserBehavioursResponse;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.service.BehaviourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/behaviour")
public class BehaviourController extends BaseController {
    @Autowired
    private BehaviourService behaviourService;

    @ResponseBody
    @RequestMapping(value = "registeredUser")
    public Object getAllAppUsers(@NotNull @RequestParam String token,
                                 @NotNull @RequestParam Long pageIndex,
                                 @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsRegisteredUser extCmsRegisteredUser = behaviourService.getRegisteredUser(pageIndex, pageSize);
        CmsBehaviour4GetRegisteredUserResponse cmsBehaviour4GetRegisteredUserResponse = BehaviourCopier.INSTANCE.asCmsBehaviour4GetRegisteredUserResponse(extCmsRegisteredUser);
        return success(cmsBehaviour4GetRegisteredUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "registeredUserSearch", method = RequestMethod.POST)
    public Object getAppUserSearch(@Validated @RequestBody CmsBehaviour4RegisteredUserSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtRegisteredUserQuery extRegisteredUserQuery = BehaviourCopier.INSTANCE.asExtRegisteredUserQuery(request);
        ExtCmsRegisteredUser extCmsRegisteredUser = behaviourService.getRegisteredUserSearch(extRegisteredUserQuery);
        CmsBehaviour4GetRegisteredUserResponse cmsBehaviour4GetRegisteredUserResponse = BehaviourCopier.INSTANCE.asCmsBehaviour4GetRegisteredUserResponse(extCmsRegisteredUser);
        return success(cmsBehaviour4GetRegisteredUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getUserBehaviors")
    public Object getUserBehaviors(@NotNull @RequestParam String token,
                                   @NotNull @RequestParam Long pageIndex,
                                   @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsUserBehaviour extCmsUserBehaviour = behaviourService.getUserBehaviors(pageIndex, pageSize);
        CmsBehaviour4GetUserBehavioursResponse cmsBehaviour4GetUserBehavioursResponse = BehaviourCopier.INSTANCE.asCmsBehaviour4GetUserBehavioursResponse(extCmsUserBehaviour);
        return success(cmsBehaviour4GetUserBehavioursResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getUserBehaviorsSearch", method = RequestMethod.POST)
    public Object getUserBehaviorsSearch(@Validated @RequestBody CmsBehaviour4GetUserBehavioursSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtCmsUserBehaviourQuery extCmsUserBehaviourQuery = BehaviourCopier.INSTANCE.asExtCmsUserBehaviourQuery(request);
        ExtCmsUserBehaviour extCmsUserBehaviour = behaviourService.getUserBehaviorsSearch(extCmsUserBehaviourQuery);
        CmsBehaviour4GetUserBehavioursResponse cmsBehaviour4GetUserBehavioursResponse = BehaviourCopier.INSTANCE.asCmsBehaviour4GetUserBehavioursResponse(extCmsUserBehaviour);
        return success(cmsBehaviour4GetUserBehavioursResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getUserBehaviorsTotal")
    public Object getUserBehaviorsTotal(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsUserBehaviourTotal extCmsUserBehaviourTotal = behaviourService.getUserBehaviorsTotal();
        CmsBehaviour4GetUserBehaviourTotalResponse cmsBehaviour4GetUserBehaviourTotalResponse = BehaviourCopier.INSTANCE.asCmsBehaviour4GetUserBehaviourTotalResponse(extCmsUserBehaviourTotal);
        return success(cmsBehaviour4GetUserBehaviourTotalResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getUserBehaviorsTotalSearch", method = RequestMethod.POST)
    public Object getUserBehaviorsTotalSearch(@Validated @RequestBody CmsBehaviour4GetUserBehavioursTotalSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtCmsUserBehaviourTotalQuery extCmsUserBehaviourTotalQuery = BehaviourCopier.INSTANCE.asExtCmsUserBehaviourTotalQuery(request);
        ExtCmsUserBehaviourTotal extCmsUserBehaviourTotal = behaviourService.getUserBehaviorsTotalByCondition(extCmsUserBehaviourTotalQuery);
        CmsBehaviour4GetUserBehaviourTotalResponse cmsBehaviour4GetUserBehaviourTotalResponse = BehaviourCopier.INSTANCE.asCmsBehaviour4GetUserBehaviourTotalResponse(extCmsUserBehaviourTotal);
        return success(cmsBehaviour4GetUserBehaviourTotalResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAppAllPages")
    public Object getAppAllPages(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtAppPage> extAppPages = behaviourService.getAppAllPages();
        return success(extAppPages);
    }
}
