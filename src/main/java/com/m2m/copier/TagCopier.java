package com.m2m.copier;

import com.m2m.domain.TopicTag;
import com.m2m.entity.*;
import com.m2m.mapstruct.TypeMapper;
import com.m2m.request.CmsTag4AddTagRequest;
import com.m2m.request.CmsTag4GetTagSearchRequest;
import com.m2m.request.CmsTag4UpdateTagRequest;
import com.m2m.response.CmsTag4GetAllTagKingdomResponse;
import com.m2m.response.CmsTag4GetAllTagsResponse;
import com.m2m.response.CmsTag4GetParentTagResponse;
import com.m2m.response.CmsTag4GetUserHobbyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TypeMapper.class)
public interface TagCopier {
    TagCopier INSTANCE = Mappers.getMapper(TagCopier.class);

    CmsTag4GetAllTagsResponse asCmsTag4GetAllTagsResponse(ExtCmsTag bean);

    @Mapping(source = "tagName", target = "tag")
    @Mapping(source = "parentTagId", target = "pid")
    @Mapping(source = "sortNumber", target = "orderNum")
    @Mapping(source = "isSystem", target = "isSys")
    @Mapping(source = "isRecommend", target = "isRec")
    @Mapping(source = "coverImage", target = "coverImg")
    TopicTag asTopicTag(CmsTag4AddTagRequest bean);

    @Mapping(source = "tagName", target = "tag")
    @Mapping(source = "parentTagId", target = "pid")
    @Mapping(source = "sortNumber", target = "orderNum")
    @Mapping(source = "isSystem", target = "isSys")
    @Mapping(source = "isRecommend", target = "isRec")
    @Mapping(source = "coverImage", target = "coverImg")
    @Mapping(source = "tagId", target = "id")
    TopicTag asTopicTag(CmsTag4UpdateTagRequest bean);

    ExtTagSearchQuery asExtTagSearchQuery(CmsTag4GetTagSearchRequest bean);

    List<CmsTag4GetParentTagResponse> asCmsTag4GetParentTagResponse(List<ExtParentTag> beans);

    List<CmsTag4GetUserHobbyResponse> asCmsTag4GetUserHobbyResponse(List<ExtUserHobby> beans);

    CmsTag4GetAllTagKingdomResponse asCmsTag4GetAllTagKingdomResponse(ExtCmsAllTagKingdom bean);

}