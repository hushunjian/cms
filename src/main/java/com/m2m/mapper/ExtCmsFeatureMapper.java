package com.m2m.mapper;

import com.m2m.domain.CmsRole;
import com.m2m.entity.ExtCmsFeature;
import com.m2m.entity.ExtCmsFeatureGroup;
import com.m2m.entity.ExtFeatureQuery;
import com.m2m.entity.ExtRoleFeature;

import java.util.List;

public interface ExtCmsFeatureMapper {
    List<ExtCmsFeatureGroup> findAllFeatures();

    void addFeatures(ExtRoleFeature extRoleFeature);

    List<ExtCmsFeature> findByRoleId(Integer roleId);

    List<ExtCmsFeatureGroup> getUserFeatures(Integer userId);

    List<CmsRole> getUserRoles(Integer userId);

    ExtCmsFeatureGroup getFeature(ExtFeatureQuery extFeatureQuery);
}

