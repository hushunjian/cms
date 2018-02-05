package com.m2m.copier;

import com.m2m.domain.BillBoard;
import com.m2m.domain.BillboardDetails;
import com.m2m.domain.BillboardRelation;
import com.m2m.entity.*;
import com.m2m.request.CmsListRank4AddEntryRequest.TargetInfo;
import com.m2m.request.*;
import com.m2m.request.CmsListRank4SortEntryRequest.SortEntry;
import com.m2m.request.CmsListRank4SortOnlineListRankRequest.ListRank;
import com.m2m.response.CmsListRank4GetAllListRankKingdomsResponse;
import com.m2m.response.CmsListRank4GetAllListRankSetsResponse;
import com.m2m.response.CmsListRank4GetAllListRankUsersResponse;
import com.m2m.response.CmsListRank4GetAppUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListRankCopier {
    ListRankCopier INSTANCE = Mappers.getMapper(ListRankCopier.class);


    @Mapping(source = "image", target = "img")
    ExtListRank asExtListRank(BillBoard bean);

    List<ExtListRank> asExtListRank(List<BillBoard> beans);

    @Mapping(source = "img", target = "image")
    BillBoard asBillBoard(CmsListRank4AddListRankRequest bean);

    @Mapping(source = "img", target = "image")
    BillBoard asBillBoard(CmsListRank4UpdateListRankRequest bean);

    CmsListRank4GetAllListRankKingdomsResponse asCmsListRank4GetAllListRankKingdomsResponse(ExtCmsRankKingdom bean);

    CmsListRank4GetAllListRankUsersResponse asCmsListRank4GetAllListRankUsersResponse(ExtCmsRankUser bean);

    CmsListRank4GetAllListRankSetsResponse asCmsListRank4GetAllListRankSetsResponse(ExtCmsRankSet bean);

    @Mapping(source = "id", target = "bid")
    @Mapping(source = "id", target = "id", ignore = true)
    BillboardDetails asBillboardDetails(CmsListRank4AddOnlineListRankRequest bean);

    BillboardDetails asBillboardDetails(CmsListRank4SetOnlineListRankRequest bean);

    BillboardDetails asBillboardDetails(ListRank bean);

    List<BillboardDetails> asBillboardDetailsList(ListRank[] beans);

    BillboardRelation asBillboardRelation(TargetInfo bean);

    List<BillboardRelation> asBillboardRelationList(TargetInfo[] beans);

    BillboardRelation asBillboardRelation(SortEntry bean);

    List<BillboardRelation> asBillboardRelationList(SortEntry[] beans);

    CmsListRank4GetAppUserResponse asCmsListRank4GetAppUserResponse(ExtCmsListRankAppUser bean);

    ExtListRankAppUserQuery asExtListRankAppUserQuery(CmsListRankGetAppUserSearchRequest bean);
}