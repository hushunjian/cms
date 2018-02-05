package com.m2m.copier;

import com.m2m.domain.CmsFeature;
import com.m2m.entity.ExtCmsFeature;
import com.m2m.entity.ExtCmsFeatureGroup;
import com.m2m.request.CmsFeature4AddRequest;
import com.m2m.request.CmsFeature4UpdateRequest;
import com.m2m.request.CmsFeature4UserRequest;
import com.m2m.request.CmsFeatureRequest;
import com.m2m.response.CmsFeatureGroupResponse;
import com.m2m.response.CmsFeatureResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeatureCopier {
    FeatureCopier INSTANCE = Mappers.getMapper(FeatureCopier.class);

    CmsFeature asImsFeature(CmsFeatureRequest bean);

    CmsFeature asImsFeature(CmsFeature4AddRequest bean);

    CmsFeature asImsFeature(CmsFeature4UpdateRequest bean);

    CmsFeatureResponse map(ExtCmsFeature bean);

    CmsFeatureResponse map(CmsFeature bean);

    CmsFeatureGroupResponse map(ExtCmsFeatureGroup bean);

    List<CmsFeatureResponse> asImsFeatureResponses(List<ExtCmsFeature> beans);

    List<CmsFeatureResponse> convert2ImsFeatureResponses(List<CmsFeature> beans);

    List<CmsFeatureGroupResponse> asCmsFeatureGroupResponses(List<ExtCmsFeatureGroup> beans);

    List<ExtCmsFeature> asExtCmsFeatures(List<CmsFeature4UserRequest> cmsFeature4Users);
}