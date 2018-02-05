package com.m2m.copier;

import com.m2m.entity.*;
import com.m2m.request.CmsBehaviour4GetUserBehavioursSearchRequest;
import com.m2m.request.CmsBehaviour4GetUserBehavioursTotalSearchRequest;
import com.m2m.request.CmsBehaviour4RegisteredUserSearchRequest;
import com.m2m.response.CmsBehaviour4GetRegisteredUserResponse;
import com.m2m.response.CmsBehaviour4GetUserBehaviourTotalResponse;
import com.m2m.response.CmsBehaviour4GetUserBehavioursResponse;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BehaviourCopier {
    BehaviourCopier INSTANCE = Mappers.getMapper(BehaviourCopier.class);

    CmsBehaviour4GetRegisteredUserResponse asCmsBehaviour4GetRegisteredUserResponse(ExtCmsRegisteredUser bean);

    ExtRegisteredUserQuery asExtRegisteredUserQuery(CmsBehaviour4RegisteredUserSearchRequest bean);

    CmsBehaviour4GetUserBehavioursResponse asCmsBehaviour4GetUserBehavioursResponse(ExtCmsUserBehaviour bean);

    ExtCmsUserBehaviourQuery asExtCmsUserBehaviourQuery(CmsBehaviour4GetUserBehavioursSearchRequest bean);

    CmsBehaviour4GetUserBehaviourTotalResponse asCmsBehaviour4GetUserBehaviourTotalResponse(ExtCmsUserBehaviourTotal bean);

    ExtCmsUserBehaviourTotalQuery asExtCmsUserBehaviourTotalQuery(CmsBehaviour4GetUserBehavioursTotalSearchRequest bean);

}