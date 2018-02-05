package com.m2m.copier;

import com.m2m.domain.GiftInfo;
import com.m2m.entity.ExtCmsGift4GetAllGift;
import com.m2m.entity.ExtGift;
import com.m2m.request.CmsGift4AddGiftRequest;
import com.m2m.request.CmsGift4UpdateGiftRequest;
import com.m2m.response.CmsGift4GetAllGiftResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GiftCopier {
    GiftCopier INSTANCE = Mappers.getMapper(GiftCopier.class);

    CmsGift4GetAllGiftResponse asCmsGift4GetAllGiftResponse(ExtCmsGift4GetAllGift bean);

    ExtGift asExtGiftInfo(CmsGift4AddGiftRequest bean);

    ExtGift asExtGiftInfo(CmsGift4UpdateGiftRequest bean);

    @Mapping(source = "giftId", target = "id")
    @Mapping(source = "duration", target = "playTime")
    GiftInfo asGiftInfo(ExtGift bean);

}