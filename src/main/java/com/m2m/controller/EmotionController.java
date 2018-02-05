package com.m2m.controller;

import com.m2m.copier.EmotionCopier;
import com.m2m.domain.EmotionPack;
import com.m2m.domain.EmotionPackDetail;
import com.m2m.entity.ExtCmsEmotion;
import com.m2m.entity.ExtCmsEmotionPack;
import com.m2m.entity.ExtEmotion;
import com.m2m.entity.ExtEmotionPack;
import com.m2m.exception.SystemException;
import com.m2m.request.*;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsEmotionPackResponse;
import com.m2m.response.CmsEmotionResponse;
import com.m2m.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Transactional
@RequestMapping("/api/emotion")
public class EmotionController extends BaseController {
    @Autowired
    private EmotionService emotionService;

    @ResponseBody
    @RequestMapping(value = "getAllEmotionPacks")
    public Object getAllEmotionPacks(@NotNull @RequestParam String token,
                                     @NotNull @RequestParam Long pageIndex,
                                     @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsEmotionPack extCmsEmotionPack = emotionService.getAllEmotionPacks(pageIndex, pageSize);
        CmsEmotionPackResponse asCmsEmotionPackResponse = EmotionCopier.INSTANCE.asCmsEmotionPackResponse(extCmsEmotionPack);
        return success(asCmsEmotionPackResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllEmotions")
    public Object getAllEmotions(@NotNull @RequestParam String token,
                                 @NotNull @RequestParam Integer packId,
                                 @NotNull @RequestParam Long pageIndex,
                                 @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsEmotion extCmsEmotion = emotionService.getAllEmotions(packId, pageIndex, pageSize);
        CmsEmotionResponse asCmsEmotionResponse = EmotionCopier.INSTANCE.asCmsEmotionResponse(extCmsEmotion);
        return success(asCmsEmotionResponse);
    }

    @ResponseBody
    @RequestMapping(value = "addEmotionPack", method = RequestMethod.POST)
    public Object addEmotionPack(@Validated @RequestBody CmsEmotion4AddEmotionPackRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtEmotionPack extEmotionPack = EmotionCopier.INSTANCE.asExtEmotionPack(request);
        EmotionPack emotionPack = EmotionCopier.INSTANCE.asEmotionPack(extEmotionPack);
        emotionService.addEmotionPack(emotionPack);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "addEmotion", method = RequestMethod.POST)
    public Object addEmotion(@Validated @RequestBody CmsEmotion4AddEmotionRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtEmotion extEmotion = EmotionCopier.INSTANCE.asExtEmotion(request);
        EmotionPackDetail emotionPackDetail = EmotionCopier.INSTANCE.asEmotionPackDetail(extEmotion);
        emotionService.addEmotion(emotionPackDetail);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteEmotionPack", method = RequestMethod.POST)
    public Object deleteEmotionPack(@Validated @RequestBody CmsEmotion4DeleteEmotionPackRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        emotionService.deleteEmotionPack(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteEmotion", method = RequestMethod.POST)
    public Object deleteEmotion(@Validated @RequestBody CmsEmotion4DeleteEmotionRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        emotionService.deleteEmotion(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateEmotionPack", method = RequestMethod.POST)
    public Object updateEmotionPack(@Validated @RequestBody CmsEmotion4UpdateEmotionPackRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtEmotionPack extEmotionPack = EmotionCopier.INSTANCE.asExtEmotionPack(request);
        EmotionPack emotionPack = EmotionCopier.INSTANCE.asEmotionPack(extEmotionPack);
        emotionService.updateEmotionPack(emotionPack);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateEmotion", method = RequestMethod.POST)
    public Object updateEmotion(@Validated @RequestBody CmsEmotion4UpdateEmotionRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtEmotion extEmotion = EmotionCopier.INSTANCE.asExtEmotion(request);
        EmotionPackDetail emotionPackDetail = EmotionCopier.INSTANCE.asEmotionPackDetail(extEmotion);
        emotionService.updateEmotion(emotionPackDetail);
        return success();
    }
}
