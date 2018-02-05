package com.m2m.controller;

import com.m2m.Constant;
import com.m2m.copier.AdvertisementCopier;
import com.m2m.domain.AdBanner;
import com.m2m.domain.AdInfo;
import com.m2m.entity.ExtAdTag;
import com.m2m.entity.ExtCmsAdBanner;
import com.m2m.entity.ExtCmsAdInfo;
import com.m2m.exception.SystemException;
import com.m2m.exception.URLFormatErrorException;
import com.m2m.request.*;
import com.m2m.response.CmsAdvertisementPositionResponse;
import com.m2m.response.CmsAdvertisementResponse;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@Transactional
@RequestMapping("/api/advertisement")
public class AdvertisementController extends BaseController {

    @Autowired
    private AdvertisementService advertisementService;

    @ResponseBody
    @RequestMapping(value = "updateAdvertisementPosition", method = RequestMethod.POST)
    public Object updateAdvertisementPosition(@Validated @RequestBody CmsAdvertisementPositionRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        AdBanner adBanner = AdvertisementCopier.INSTANCE.asAdBanner(request);
        advertisementService.updateAdBanner(adBanner);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteAdvertisement", method = RequestMethod.POST)
    public Object deleteAdvertisement(@Validated @RequestBody CmsAdvertisement4DeleteRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        advertisementService.delAdInfo(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteAdvertisementPosition", method = RequestMethod.POST)
    public Object deleteAdvertisementPosition(@Validated @RequestBody CmsAdvertisementPosition4DeleteRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        advertisementService.delAdBanner(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping("getAllLinkTags")
    public Object getAllLinkTags(@NotNull @RequestParam String token,
                                 @NotNull @RequestParam Long positionId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtAdTag> extAdTags = advertisementService.getAllLinkTags(positionId);
        return success(extAdTags);
    }

    @ResponseBody
    @RequestMapping(value = "unlinkLinkTag", method = RequestMethod.POST)
    public Object unlinkLinkTag(@Validated @RequestBody CmsLinkTag4DeleteRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        advertisementService.unlinkLinkTag(request.getId(), request.getPositionId());
        return success();
    }

    @ResponseBody
    @RequestMapping("getAllAdvertisementPositions")
    public Object getAllAdvertisementPositions(@NotNull @RequestParam String token,
                                               @NotNull @RequestParam Long pageIndex,
                                               @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAdBanner extCmsAdBanner = advertisementService.searchAdBannerListPage(pageIndex, pageSize);
        CmsAdvertisementPositionResponse cmsAdvertisementPositionResponse = AdvertisementCopier.INSTANCE.asCmsAdvertisementPositionResponse(extCmsAdBanner);
        return success(cmsAdvertisementPositionResponse);
    }

    @ResponseBody
    @RequestMapping(value = "addAdvertisement", method = RequestMethod.POST)
    public Object addAdvertisement(@Validated @RequestBody CmsAdvertisement4AddRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        if (request.getType() == 0) {
        	if(!Pattern.matches(Constant.URL_REGEX, request.getUrl())){
        		throw new URLFormatErrorException();
        	}
        }
        AdInfo adInfo = AdvertisementCopier.INSTANCE.asAdInfo(request);
        advertisementService.addAdInfo(adInfo);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateAdvertisement", method = RequestMethod.POST)
    public Object updateAdvertisement(@Validated @RequestBody CmsAdvertisement4UpdateRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        if (request.getType() == 0) {
        	if(!Pattern.matches(Constant.URL_REGEX, request.getUrl())){
        		throw new URLFormatErrorException();
        	}
        }
        AdInfo adInfo = AdvertisementCopier.INSTANCE.asAdInfo(request);
        advertisementService.updateAdInfo(adInfo);
        return success();
    }

    @ResponseBody
    @RequestMapping("getAdvertisementsByPositionId")
    public Object getAdvertisementsByPositionId(@NotNull @RequestParam String token,
                                                @NotNull @RequestParam Long positionId,
                                                @NotNull @RequestParam Long pageIndex,
                                                @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAdInfo extCmsAdInfo = advertisementService.searchAdInfoListPage(positionId, pageIndex, pageSize);
        CmsAdvertisementResponse cmsAdvertisementResponse = AdvertisementCopier.INSTANCE.asCmsAdvertisementResponse(extCmsAdInfo);
        return success(cmsAdvertisementResponse);
    }

    @ResponseBody
    @RequestMapping("getAllAdvertisements")
    public Object getAllAdvertisements(@NotNull @RequestParam String token,
                                       @NotNull @RequestParam Long pageIndex,
                                       @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAdInfo extCmsAdInfo = advertisementService.getAllAdvertisements(pageIndex, pageSize);
        CmsAdvertisementResponse cmsAdvertisementResponse = AdvertisementCopier.INSTANCE.asCmsAdvertisementResponse(extCmsAdInfo);
        return success(cmsAdvertisementResponse);
    }

    @ResponseBody
    @RequestMapping("addAdvertisementPosition")
    public Object addAdvertisementPosition(@Validated @RequestBody CmsAdvertisementPosition4AddRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        AdBanner adBanner = AdvertisementCopier.INSTANCE.asAdBanner(request);
        advertisementService.addAdBanner(adBanner);
        return success();
    }

    @ResponseBody
    @RequestMapping("addLinkTag")
    public Object addLinkTag(@Validated @RequestBody CmsAdvertisement4AddLinkTagRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        advertisementService.addAdTag(request.getId(), request.getName(), request.getPositionId(), request.getPosition());
        return success();
    }
}
