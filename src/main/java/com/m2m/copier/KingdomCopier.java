package com.m2m.copier;

import com.m2m.entity.*;
import com.m2m.request.CmsKingdom4ExportKingdomUsersRequest;
import com.m2m.request.CmsKingdom4GetKingdomSearchRequest;
import com.m2m.request.CmsKingdom4GetKingdomUserSearchRequest;
import com.m2m.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KingdomCopier {
    KingdomCopier INSTANCE = Mappers.getMapper(KingdomCopier.class);

    CmsKingdom4AllKingdomResponse asCmsKingdom4AllKingdomsResponses(ExtCmsKingdom bean);

    CmsKingdom4AllLotteryResponse asCmsKingdom4AllLotteryResponse(ExtCmsLottery bean);

    ExtKingdomQuery asExtKingdomInfoQuery(CmsKingdom4GetKingdomSearchRequest bean);

    CmsKingdomGetLotteryUsersResponse asCmsKingdomGetLotteryUsersResponse(ExtCmsLotteryUser bean);

    CmsKindom4GetKingdomValueResponse asCmsKindom4GetKingdomValueResponse(ExtCmsKingdomValue bean);

    CmsKingdom4GetKingdomUserResponse asCmsKingdom4GetKingdomUserResponse(ExtCmsKingdomReview bean);

    ExtKingdomUserQuery asExtKingdomUserQuery(CmsKingdom4GetKingdomUserSearchRequest bean);
    
    ExtKingdomUserQuery asExtKingdomUserQuery(CmsKingdom4ExportKingdomUsersRequest bean);

    CmsKingdom4GetAllListedKingdomResponse asCmsKingdom4GetAllListedKingdomResponse(ExtCmsKingdomListed bean);
}