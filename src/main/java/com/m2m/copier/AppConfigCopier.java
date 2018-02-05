package com.m2m.copier;

import com.m2m.domain.AppLightboxSource;
import com.m2m.entity.ExtLightBox;
import com.m2m.request.CmsAppConfig4AddLightBoxRequest;
import com.m2m.request.CmsAppConfig4UpdateLightBoxRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppConfigCopier {
    AppConfigCopier INSTANCE = Mappers.getMapper(AppConfigCopier.class);

    @Mapping(source = "secondaryText", target = "subText")
    @Mapping(source = "mainTone", target = "mainColor")
    @Mapping(source = "linkUrl", target = "link")
    @Mapping(source = "startTime", target = "beginAt")
    @Mapping(source = "endTime", target = "endAt")
    ExtLightBox asExtLightBox(AppLightboxSource bean);

    List<ExtLightBox> asExtLightBoxList(List<AppLightboxSource> beans);

    @Mapping(source = "subText", target = "secondaryText")
    @Mapping(source = "mainColor", target = "mainTone")
    @Mapping(source = "link", target = "linkUrl")
    @Mapping(source = "beginAt", target = "startTime")
    @Mapping(source = "endAt", target = "endTime")
    AppLightboxSource asAppLightboxSource(CmsAppConfig4UpdateLightBoxRequest bean);

    @Mapping(source = "subText", target = "secondaryText")
    @Mapping(source = "mainColor", target = "mainTone")
    @Mapping(source = "link", target = "linkUrl")
    @Mapping(source = "beginAt", target = "startTime")
    @Mapping(source = "endAt", target = "endTime")
    AppLightboxSource asAppLightboxSource(CmsAppConfig4AddLightBoxRequest bean);

}