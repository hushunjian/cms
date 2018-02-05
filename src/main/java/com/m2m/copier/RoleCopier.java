package com.m2m.copier;

import com.m2m.domain.CmsRole;
import com.m2m.request.CmsRole4AddRequest;
import com.m2m.request.CmsRoleRequest;
import com.m2m.response.CmsRoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleCopier {
    RoleCopier INSTANCE = Mappers.getMapper(RoleCopier.class);

    CmsRole asImsRole(CmsRoleRequest bean);

    CmsRole asImsRole(CmsRole4AddRequest bean);

    CmsRoleResponse asImsRole(CmsRole bean);

    List<CmsRoleResponse> asImsRoleResponses(List<CmsRole> beans);
}