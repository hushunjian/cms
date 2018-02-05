package com.m2m.copier;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.m2m.domain.UserIndustry;
import com.m2m.entity.ExtCmsIndustrial;
import com.m2m.entity.ExtIndustry;
import com.m2m.entity.ExtIndustryQuery;
import com.m2m.request.CmsIndustry4AddIndustryResquest;
import com.m2m.request.CmsIndustry4GetIndustrialSearchRequest;
import com.m2m.request.CmsIndustry4SortEntryRequest.SortEntry;
import com.m2m.request.CmsIndustry4UpdateIndustryRequest;
import com.m2m.response.CmsIndustrial4GetIndustrialManagementResponse;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndustryCopier {
    IndustryCopier INSTANCE = Mappers.getMapper(IndustryCopier.class);

    CmsIndustrial4GetIndustrialManagementResponse asCmsIndustrial4GetIndustrialManagementResponse(ExtCmsIndustrial bean);
    
    @Mapping(source="industryName",target="firmName")
    @Mapping(source="coverImg",target="coverImage")
    @Mapping(source="industryDescription",target="introduce")
    @Mapping(source="createTime",target="createdAt")
    ExtIndustry asExtIndustry(UserIndustry bean);
    
    List<ExtIndustry> asExtIndustrial(List<UserIndustry> beans);
    
    @Mapping(source="firmName",target="industryName")
    @Mapping(source="coverImage",target="coverImg")
    @Mapping(source="introduce",target="industryDescription")
    UserIndustry asUserIndustry(CmsIndustry4AddIndustryResquest bean);
    
    @Mapping(source="firmName",target="industryName")
    @Mapping(source="coverImage",target="coverImg")
    @Mapping(source="introduce",target="industryDescription")
    UserIndustry asUserIndustry(CmsIndustry4UpdateIndustryRequest bean);
    
    ExtIndustryQuery asExtIndustryQuery(CmsIndustry4GetIndustrialSearchRequest bean);
    
    UserIndustry asUserIndustry(SortEntry bean);
    
    List<UserIndustry> asUserIndustrial(SortEntry[] beans);
    
}