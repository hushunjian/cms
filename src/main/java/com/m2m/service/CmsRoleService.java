package com.m2m.service;

import com.m2m.domain.CmsRole;
import com.m2m.domain.CmsRoleExample;
import com.m2m.domain.CmsRoleFeatureExample;
import com.m2m.domain.CmsUserRoleExample;
import com.m2m.entity.ExtCmsFeature;
import com.m2m.entity.ExtRoleFeature;
import com.m2m.exception.RoleAlreadyExistsException;
import com.m2m.exception.SystemException;
import com.m2m.mapper.CmsRoleFeatureMapper;
import com.m2m.mapper.CmsRoleMapper;
import com.m2m.mapper.CmsUserRoleMapper;
import com.m2m.mapper.ExtCmsFeatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsRoleService {
    @Autowired
    private CmsRoleMapper roleMapper;
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    private CmsRoleFeatureMapper cmsRoleFeatureMapper;
    @Autowired
    private ExtCmsFeatureMapper extCmsFeatureMapper;

    public List<CmsRole> getAllRoles() {
        CmsRoleExample example = new CmsRoleExample();
        List<CmsRole> rtn = roleMapper.selectByExample(example);
        return rtn;
    }

    public Boolean exists(String name) {
        CmsRoleExample cmsRoleExample = new CmsRoleExample();
        CmsRoleExample.Criteria criteria = cmsRoleExample.createCriteria();
        criteria.andNameEqualTo(name);
        Long count = roleMapper.countByExample(cmsRoleExample);
        return count > 0 ? true : false;
    }

    public void addRole(CmsRole cmsRole) throws SystemException {
        if (exists(cmsRole.getName())) {
            throw new RoleAlreadyExistsException();
        }
        roleMapper.insertSelective(cmsRole);
    }

    public void deleteImsRole(Integer id) {
        deleteUserRoleByRoleId(id);
        deleteRoleFeatureByRoleId(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    public void updateImsRole(CmsRole user) {
        roleMapper.updateByPrimaryKeySelective(user);
    }


    public List<ExtCmsFeature> getRoleFeatures(Integer roleId) {
        return extCmsFeatureMapper.findByRoleId(roleId);
    }

    public void addRoleFeature(Integer roleId, List<Integer> featureIds) {
        ExtRoleFeature extRoleFeature = new ExtRoleFeature();
        extRoleFeature.setRoleId(roleId);
        extRoleFeature.setFeatureIds(featureIds);
        extCmsFeatureMapper.addFeatures(extRoleFeature);
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

    public void deleteUserRoleByRoleId(Integer roleId) {
        CmsUserRoleExample cmsUserRoleExample = new CmsUserRoleExample();
        CmsUserRoleExample.Criteria criteria = cmsUserRoleExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        cmsUserRoleMapper.deleteByExample(cmsUserRoleExample);
    }

    public void updateRoleFeatures(Integer roleId, List<Integer> featureIds) {
        deleteRoleFeatureByRoleId(roleId);
        addRoleFeature(roleId, featureIds);
    }
}
