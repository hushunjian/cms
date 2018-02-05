package com.m2m.copier;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.m2m.entity.ExtAppUserQuery;
import com.m2m.entity.ExtCmsAllAppUser;
import com.m2m.entity.ExtCmsKingdomContent;
import com.m2m.entity.ExtCmsUserFragmentContent;
import com.m2m.request.CmsAppUser4GetAppUserSearchRequest;
import com.m2m.response.CmsAppUser4GetAllAppUserResponse;
import com.m2m.response.CmsAppUser4GetUserFragmentContentResponse;
import com.m2m.response.CmsAppUser4GetUserKingdomContentResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserCopier {
    AppUserCopier INSTANCE = Mappers.getMapper(AppUserCopier.class);

    CmsAppUser4GetAllAppUserResponse asCmsAppUser4GetAllAppUserResponse(ExtCmsAllAppUser bean);

    ExtAppUserQuery asExtAppUserQuery(CmsAppUser4GetAppUserSearchRequest bean);

    CmsAppUser4GetUserKingdomContentResponse asCmsAppUser4GetUserKingdomContentResponse(ExtCmsKingdomContent bean);

    CmsAppUser4GetUserFragmentContentResponse asCmsAppUser4GetUserFragmentContentResponse(ExtCmsUserFragmentContent bean);
    
}