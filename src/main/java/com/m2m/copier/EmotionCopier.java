package com.m2m.copier;

import com.m2m.domain.EmotionPack;
import com.m2m.domain.EmotionPackDetail;
import com.m2m.entity.ExtCmsEmotion;
import com.m2m.entity.ExtCmsEmotionPack;
import com.m2m.entity.ExtEmotion;
import com.m2m.entity.ExtEmotionPack;
import com.m2m.request.CmsEmotion4AddEmotionPackRequest;
import com.m2m.request.CmsEmotion4AddEmotionRequest;
import com.m2m.request.CmsEmotion4UpdateEmotionPackRequest;
import com.m2m.request.CmsEmotion4UpdateEmotionRequest;
import com.m2m.response.CmsEmotionPackResponse;
import com.m2m.response.CmsEmotionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmotionCopier {
    EmotionCopier INSTANCE = Mappers.getMapper(EmotionCopier.class);

    CmsEmotionPackResponse asCmsEmotionPackResponse(ExtCmsEmotionPack bean);

    CmsEmotionResponse asCmsEmotionResponse(ExtCmsEmotion bean);

    ExtEmotionPack asExtEmotionPack(CmsEmotion4AddEmotionPackRequest bean);

    ExtEmotion asExtEmotion(CmsEmotion4AddEmotionRequest bean);

    ExtEmotionPack asExtEmotionPack(CmsEmotion4UpdateEmotionPackRequest bean);

    ExtEmotion asExtEmotion(CmsEmotion4UpdateEmotionRequest bean);


    @Mapping(source = "name", target = "title")
    @Mapping(source = "cover", target = "image")
    @Mapping(source = "cover", target = "thumb")
    @Mapping(source = "order", target = "orderNum")
    @Mapping(source = "width", target = "w")
    @Mapping(source = "height", target = "h")
    @Mapping(source = "width", target = "thumbW")
    @Mapping(source = "height", target = "thumbH")
    EmotionPackDetail asEmotionPackDetail(ExtEmotion bean);

    @Mapping(source = "type", target = "emojiType")
    @Mapping(source = "packVersion", target = "version")
    @Mapping(source = "analyVersion", target = "pVersion")
    @Mapping(source = "order", target = "orderNum")
    EmotionPack asEmotionPack(ExtEmotionPack bean);
}