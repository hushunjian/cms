package com.m2m.service;

import com.m2m.domain.CmsFeature;
import com.m2m.domain.CmsFeatureExample;
import com.m2m.domain.CmsRoleFeatureExample;
import com.m2m.domain.CmsUserFeatureExample;
import com.m2m.entity.ExtCmsFeatureGroup;
import com.m2m.entity.ExtFeatureQuery;
import com.m2m.exception.AccessControlException;
import com.m2m.exception.FeatureAlreadyExistsException;
import com.m2m.exception.SystemException;
import com.m2m.mapper.CmsFeatureMapper;
import com.m2m.mapper.CmsRoleFeatureMapper;
import com.m2m.mapper.CmsUserFeatureMapper;
import com.m2m.mapper.ExtCmsFeatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsFeatureService {
    @Autowired
    private CmsFeatureMapper featureMapper;
    @Autowired
    private ExtCmsFeatureMapper extCmsFeatureMapper;
    @Autowired
    private CmsRoleFeatureMapper cmsRoleFeatureMapper;
    @Autowired
    private CmsUserFeatureMapper cmsUserFeatureMapper;

    public List<ExtCmsFeatureGroup> getAllFeatures() {
        List<ExtCmsFeatureGroup> extCmsFeatureGroups = extCmsFeatureMapper.findAllFeatures();
        return extCmsFeatureGroups;
    }

    public CmsFeature getFeatureById(Integer id) {
        return featureMapper.selectByPrimaryKey(id);
    }

    public void addFeature(CmsFeature cmsFeature) throws SystemException {
        if (exists(cmsFeature.getName())) {
            throw new FeatureAlreadyExistsException();
        }
        featureMapper.insertSelective(cmsFeature);
    }

    public Boolean exists(String name) {
        CmsFeatureExample cmsFeatureExample = new CmsFeatureExample();
        CmsFeatureExample.Criteria criteria = cmsFeatureExample.createCriteria();
        criteria.andNameEqualTo(name);
        Long count = featureMapper.countByExample(cmsFeatureExample);
        return count > 0 ? true : false;
    }

    public void deleteRoleFeatureByRoleId(Integer roleId) {
        CmsRoleFeatureExample cmsRoleFeatureExample = new CmsRoleFeatureExample();
        CmsRoleFeatureExample.Criteria criteria = cmsRoleFeatureExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        cmsRoleFeatureMapper.deleteByExample(cmsRoleFeatureExample);
    }

    public void deleteRoleFeatureByFeatureId(Integer featureId) {
        CmsRoleFeatureExample cmsRoleFeatureExample = new CmsRoleFeatureExample();
        CmsRoleFeatureExample.Criteria criteria = cmsRoleFeatureExample.createCriteria();
        criteria.andFeatureIdEqualTo(featureId);
        cmsRoleFeatureMapper.deleteByExample(cmsRoleFeatureExample);
    }

    public void deleteFeature(Integer featureId) {
        deleteUserFeatureByFeatureId(featureId);
        deleteRoleFeatureByFeatureId(featureId);
        featureMapper.deleteByPrimaryKey(featureId);
    }

    public void deleteUserFeatureByFeatureId(Integer featureId) {
        CmsUserFeatureExample cmsUserFeatureExample = new CmsUserFeatureExample();
        CmsUserFeatureExample.Criteria criteria = cmsUserFeatureExample.createCriteria();
        criteria.andUserIdEqualTo(featureId);
        cmsUserFeatureMapper.deleteByExample(cmsUserFeatureExample);
    }

    public void updateFeature(CmsFeature cmsFeature) {
        featureMapper.updateByPrimaryKeySelective(cmsFeature);
    }

    public boolean hasFeature(Integer userId, Integer featureId) throws SystemException {
        ExtFeatureQuery extFeatureQuery = new ExtFeatureQuery();
        extFeatureQuery.setUserId(userId);
        extFeatureQuery.setFeatureId(featureId);
        ExtCmsFeatureGroup extCmsFeatureGroup = extCmsFeatureMapper.getFeature(extFeatureQuery);
        if (extCmsFeatureGroup == null) {
            throw new AccessControlException();
        }
        return true;
    }
}
