package com.m2m.controller;

import com.m2m.copier.GiftCopier;
import com.m2m.domain.GiftInfo;
import com.m2m.entity.ExtCmsGift4GetAllGift;
import com.m2m.entity.ExtGift;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsGift4AddGiftRequest;
import com.m2m.request.CmsGift4DeleteGiftRequest;
import com.m2m.request.CmsGift4UpdateGiftRequest;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsGift4GetAllGiftResponse;
import com.m2m.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Transactional
@RequestMapping("/api/gift")
public class GiftController extends BaseController {
    @Autowired
    private GiftService giftService;

    @ResponseBody
    @RequestMapping(value = "getAllGifts")
    public Object getAllGifts(@NotNull @RequestParam String token,
                              @NotNull @RequestParam Long pageIndex,
                              @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsGift4GetAllGift extCmsGift4GetAllGift = giftService.getAllGifts(pageIndex, pageSize);
        CmsGift4GetAllGiftResponse cmsGift4GetAllGiftResponse = GiftCopier.INSTANCE.asCmsGift4GetAllGiftResponse(extCmsGift4GetAllGift);
        return success(cmsGift4GetAllGiftResponse);
    }

    @ResponseBody
    @RequestMapping(value = "addGift", method = RequestMethod.POST)
    public Object addGift(@Validated @RequestBody CmsGift4AddGiftRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtGift extGiftInfo = GiftCopier.INSTANCE.asExtGiftInfo(request);
        GiftInfo giftInfo = GiftCopier.INSTANCE.asGiftInfo(extGiftInfo);
        giftService.addGift(giftInfo);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteGift", method = RequestMethod.POST)
    public Object deleteGift(@Validated @RequestBody CmsGift4DeleteGiftRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        giftService.deleteGift(request.getGiftId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateGift", method = RequestMethod.POST)
    public Object updateGift(@Validated @RequestBody CmsGift4UpdateGiftRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtGift extGiftInfo = GiftCopier.INSTANCE.asExtGiftInfo(request);
        GiftInfo giftInfo = GiftCopier.INSTANCE.asGiftInfo(extGiftInfo);
        giftService.updateGift(giftInfo);
        return success();
    }
}
