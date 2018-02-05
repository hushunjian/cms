package com.m2m.copier;

import com.m2m.domain.CmsUser;
import com.m2m.request.CmsUser4AddRequest;
import com.m2m.request.CmsUser4DeleteRequest;
import com.m2m.request.CmsUser4UpdateRequest;
import com.m2m.request.CmsUserRequest;
import com.m2m.response.CmsUser4LoginResponse;
import com.m2m.response.CmsUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCopier {
    UserCopier INSTANCE = Mappers.getMapper(UserCopier.class);

    CmsUser asImsUser(CmsUserRequest bean);

    CmsUser asImsUser(CmsUser4AddRequest bean);

    CmsUser asImsUser(CmsUser4DeleteRequest bean);

    CmsUser asImsUser(CmsUser4UpdateRequest bean);

    CmsUserResponse asCmsUserResponse(CmsUser bean);

    CmsUser4LoginResponse asCmsUser4LoginResponse(CmsUser bean);

    List<CmsUserResponse> asCmsUserResponses(List<CmsUser> beans);
}