package com.m2m.copier;

import com.m2m.domain.AdBanner;
import com.m2m.domain.AdInfo;
import com.m2m.entity.ExtAdBanner;
import com.m2m.entity.ExtCmsAdBanner;
import com.m2m.entity.ExtCmsAdInfo;
import com.m2m.request.CmsAdvertisement4AddRequest;
import com.m2m.request.CmsAdvertisement4UpdateRequest;
import com.m2m.request.CmsAdvertisementPosition4AddRequest;
import com.m2m.request.CmsAdvertisementPositionRequest;
import com.m2m.response.CmsAdvertisementPositionResponse;
import com.m2m.response.CmsAdvertisementResponse;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdvertisementCopier {
    AdvertisementCopier INSTANCE = Mappers.getMapper(AdvertisementCopier.class);

    @Mapping(source = "name", target = "adBannerName")
    @Mapping(source = "position", target = "bannerPosition")
    @Mapping(source = "width", target = "adBannerWidth")
    @Mapping(source = "height", target = "adBannerHeight")
    AdBanner asAdBanner(CmsAdvertisementPositionRequest bean);

    @Mapping(source = "name", target = "adTitle")
    @Mapping(source = "cover", target = "adCover")
    @Mapping(source = "positionId", target = "bannerId")
    @Mapping(source = "coverWidth", target = "adCoverWidth")
    @Mapping(source = "coverHeight", target = "adCoverHeight")
    @Mapping(source = "url", target = "adUrl")
    @Mapping(source = "validAt", target = "effectiveTime")
    @Mapping(source = "probability", target = "displayProbability")
    AdInfo asAdInfo(CmsAdvertisement4AddRequest bean);

    @Mapping(source = "name", target = "adTitle")
    @Mapping(source = "cover", target = "adCover")
    @Mapping(source = "positionId", target = "bannerId")
    @Mapping(source = "coverWidth", target = "adCoverWidth")
    @Mapping(source = "coverHeight", target = "adCoverHeight")
    @Mapping(source = "url", target = "adUrl")
    @Mapping(source = "validAt", target = "effectiveTime")
    @Mapping(source = "probability", target = "displayProbability")
    AdInfo asAdInfo(CmsAdvertisement4UpdateRequest bean);

    @Mapping(source = "name", target = "adBannerName")
    @Mapping(source = "position", target = "bannerPosition")
    @Mapping(source = "width", target = "adBannerWidth")
    @Mapping(source = "height", target = "adBannerHeight")
    AdBanner asAdBanner(CmsAdvertisementPosition4AddRequest bean);

    @Mapping(source = "adBannerName", target = "name")
    @Mapping(source = "bannerPosition", target = "position")
    @Mapping(source = "adBannerHeight", target = "height")
    @Mapping(source = "adBannerWidth", target = "width")
    @Mapping(source = "createTime", target = "createdAt")
    ExtAdBanner asExtAdBanner(AdBanner bean);

    CmsAdvertisementPositionResponse asCmsAdvertisementPositionResponse(ExtCmsAdBanner bean);

    CmsAdvertisementResponse asCmsAdvertisementResponse(ExtCmsAdInfo bean);
}