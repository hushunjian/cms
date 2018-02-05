package com.m2m.controller;

import com.m2m.Constant;
import com.m2m.copier.AppConfigCopier;
import com.m2m.domain.AppLightboxSource;
import com.m2m.entity.ExtCmsBasicConfig;
import com.m2m.entity.ExtLightBox;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsAppConfig4AddLightBoxRequest;
import com.m2m.request.CmsAppConfig4DeleteLightBoxRequest;
import com.m2m.request.CmsAppConfig4UpdateAppBasicConfigRequest;
import com.m2m.request.CmsAppConfig4UpdateLightBoxRequest;
import com.m2m.response.CmsAppConfig4GetLightBoxesResponse;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.service.AppConfigService;
import com.m2m.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/appconfig")
public class AppConfigController extends BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private AppConfigService appConfigService;

    @ResponseBody
    @RequestMapping(value = "getAppBasicConfig")
    public Object getAppBasicConfig(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtCmsBasicConfig> extCmsBasicConfigs = appConfigService.getAppBasicConfig();
        return success(extCmsBasicConfigs);
    }

    @ResponseBody
    @RequestMapping(value = "refreshCache")
    public Object refreshCache(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        appConfigService.refreshCache();
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateAppBasicConfig", method = RequestMethod.POST)
    public Object updateAppBasicConfig(@Validated @RequestBody CmsAppConfig4UpdateAppBasicConfigRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appConfigService.updateAppBasicConfig(request.getKey(), request.getValue());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getValue")
    public Object getValue(@NotNull @RequestParam String key) throws SystemException {
        key = Constant.APP_CONFIG_KEY_PRE + key;
        String value = redisService.get(key);
        return success(value);
    }


    @ResponseBody
    @RequestMapping(value = "getLightBoxes")
    public Object getLightBoxes(@NotNull @RequestParam String token,
                                @NotNull @RequestParam Long pageIndex,
                                @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<AppLightboxSource> appLightboxSourceList = appConfigService.getLightBoxList(pageIndex, pageSize);
        Long total = appConfigService.getListBoxCount();
        List<ExtLightBox> extLightBoxes = AppConfigCopier.INSTANCE.asExtLightBoxList(appLightboxSourceList);
        CmsAppConfig4GetLightBoxesResponse cmsAppConfig4GetLightBoxesResponse = new CmsAppConfig4GetLightBoxesResponse();
        cmsAppConfig4GetLightBoxesResponse.setData(extLightBoxes);
        cmsAppConfig4GetLightBoxesResponse.setTotal(total);
        return success(cmsAppConfig4GetLightBoxesResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getLightBoxesByTime")
    public Object getLightBoxesByTime(@NotNull @RequestParam String token,
                                      @NotNull @RequestParam Long searchTime,
                                      @NotNull @RequestParam Long pageIndex,
                                      @NotNull @RequestParam Long pageSize) throws SystemException, ParseException {
        CmsCredentialResponse credential = validate(token);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(searchTime);
        Date searchDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        List<AppLightboxSource> appLightboxSourceList = appConfigService.getLightBoxListByTime(searchDate, pageIndex, pageSize);
        Long total = appConfigService.getListBoxCountByTime(searchDate);
        List<ExtLightBox> extLightBoxs = AppConfigCopier.INSTANCE.asExtLightBoxList(appLightboxSourceList);
        CmsAppConfig4GetLightBoxesResponse cmsAppConfig4GetLightBoxsResponse = new CmsAppConfig4GetLightBoxesResponse();
        cmsAppConfig4GetLightBoxsResponse.setData(extLightBoxs);
        cmsAppConfig4GetLightBoxsResponse.setTotal(total);
        return success(cmsAppConfig4GetLightBoxsResponse);
    }

    @ResponseBody
    @RequestMapping(value = "updateLightBox", method = RequestMethod.POST)
    public Object updateLightBox(@Validated @RequestBody CmsAppConfig4UpdateLightBoxRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        AppLightboxSource appLightboxSource = AppConfigCopier.INSTANCE.asAppLightboxSource(request);
        appConfigService.updateLightBox(appLightboxSource);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteLightBox", method = RequestMethod.POST)
    public Object deleteLightBox(@Validated @RequestBody CmsAppConfig4DeleteLightBoxRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        appConfigService.deleteLightBox(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "addLightBox", method = RequestMethod.POST)
    public Object addLightBox(@Validated @RequestBody CmsAppConfig4AddLightBoxRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        AppLightboxSource appLightboxSource = AppConfigCopier.INSTANCE.asAppLightboxSource(request);
        appConfigService.addLightBox(appLightboxSource);
        return success();
    }
}
