package com.m2m.copier;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.m2m.domain.AppAnchor;
import com.m2m.entity.ExtAnchorInviteDetailQuery;
import com.m2m.entity.ExtAnchorQuery;
import com.m2m.entity.ExtCmsAnchorInvite;
import com.m2m.entity.ExtCmsAnchorInviteDatail;
import com.m2m.entity.ExtCmsAppAnchor;
import com.m2m.entity.ExtGetAnchorInviteQuery;
import com.m2m.request.CmsAnchor4AddAnchorRequest;
import com.m2m.request.CmsAnchor4ExportAnchorInviteDetailRequest;
import com.m2m.request.CmsAnchor4GetAnchorInviteDetailSearchRequest;
import com.m2m.request.CmsAnchor4GetAnchorInviteSearchRequest;
import com.m2m.request.CmsAnchor4GetAnchorSearchRequest;
import com.m2m.request.CmsAnchor4UpdateAnchorRequest;
import com.m2m.response.CmsAnchor4GetAnchorInviteDatailResponse;
import com.m2m.response.CmsAnchor4GetAnchorInviteResponse;
import com.m2m.response.CmsAnchor4GetAnchorsResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppAnchorCopier {
    AppAnchorCopier INSTANCE = Mappers.getMapper(AppAnchorCopier.class);

    CmsAnchor4GetAnchorsResponse asCmsAnchor4GetAnchorsResponse(ExtCmsAppAnchor bean);
    
    AppAnchor asAppAnchor(CmsAnchor4AddAnchorRequest bean);
    
    AppAnchor asAppAnchor(CmsAnchor4UpdateAnchorRequest bean);
    
    CmsAnchor4GetAnchorInviteResponse asCmsAnchor4GetAnchorInviteResponse(ExtCmsAnchorInvite bean);
    
    ExtGetAnchorInviteQuery asExtGetAnchorInviteQuery(CmsAnchor4GetAnchorInviteSearchRequest bean);
    
    CmsAnchor4GetAnchorInviteDatailResponse asCmsAnchor4GetAnchorInviteDatailResponse(ExtCmsAnchorInviteDatail bean);
    
    ExtAnchorInviteDetailQuery asExtAnchorInviteDetailQuery(CmsAnchor4GetAnchorInviteDetailSearchRequest bean);
    
    ExtAnchorInviteDetailQuery asExtAnchorInviteDetailQuery(CmsAnchor4ExportAnchorInviteDetailRequest bean);
    
    ExtAnchorQuery asExtAnchorQuery(CmsAnchor4GetAnchorSearchRequest bean);
}