package com.m2m.copier;

import com.m2m.domain.CmsCredential;
import com.m2m.entity.ExtCmsCredential;
import com.m2m.request.CmsCredentialRequest;
import com.m2m.response.CmsCredentialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CredentialCopier {
    CredentialCopier INSTANCE = Mappers.getMapper(CredentialCopier.class);

    CmsCredentialResponse asImsCredentialResponse(ExtCmsCredential bean);

    CmsCredential asImsCredential(CmsCredentialRequest bean);
}