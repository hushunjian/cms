package com.m2m.service;

import com.m2m.domain.*;
import com.m2m.entity.ExtCmsFeature;
import com.m2m.entity.ExtCmsFeatureGroup;
import com.m2m.entity.ExtUserFeature;
import com.m2m.entity.ExtUserRole;
import com.m2m.exception.AuthenticationException;
import com.m2m.exception.SystemException;
import com.m2m.exception.UserAlreadyExistsException;
import com.m2m.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsUserService {
    @Autowired
    private CmsUserMapper userMapper;
    @Autowired
    private CmsUserFeatureMapper cmsUserFeatureMapper;
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    private ExtCmsFeatureMapper extCmsFeatureMapper;
    @Autowired
    private ExtCmsUserFeatureMapper extUserCmsFeatureMapper;
    @Autowired
    private ExtCmsUserRoleMapper extCmsUserRoleMapper;

    public Boolean exists(String name) {
        CmsUserExample cmsUserExample = new CmsUserExample();
        CmsUserExample.Criteria criteria = cmsUserExample.createCriteria();
        criteria.andNameEqualTo(name);
        Long count = userMapper.countByExample(cmsUserExample);
        return count > 0 ? true : false;
    }

    public CmsUser find(String name, String password) throws AuthenticationException {
        CmsUserExample example = new CmsUserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<CmsUser> cmsUsers = userMapper.selectByExample(example);
        if (cmsUsers.size() == 0) {
            throw new AuthenticationException();
        } else {
            return cmsUsers.get(0);
        }
    }

    public void updateUser(CmsUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public List<CmsUser> getAllUsers() {
        CmsUserExample example = new CmsUserExample();
        return userMapper.selectByExample(example);
    }

    public void addUser(CmsUser cmsUser) throws SystemException {
        if (exists(cmsUser.getName())) {
            throw new UserAlreadyExistsException();
        }
        userMapper.insertSelective(cmsUser);
        
    }

    public void deleteImsUser(Integer userId) {
        deleteUserRoleByUserId(userId);
        deleteUserFeatureByUserId(userId);
        userMapper.deleteByPrimaryKey(userId);
    }

    public void updateImsUser(CmsUser user) {
        userMapper.updateByPrimaryKeySelective(user);

    }


    public void deleteUserFeatureByUserId(Integer userId, Integer featureId) {
        CmsUserFeatureExample cmsUserFeatureExample = new CmsUserFeatureExample();
        CmsUserFeatureExample.Criteria criteria = cmsUserFeatureExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andFeatureIdEqualTo(featureId);
        cmsUserFeatureMapper.deleteByExample(cmsUserFeatureExample);
    }

    public void deleteUserFeatureByUserId(Integer userId) {
        CmsUserFeatureExample cmsUserFeatureExample = new CmsUserFeatureExample();
        CmsUserFeatureExample.Criteria criteria = cmsUserFeatureExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        cmsUserFeatureMapper.deleteByExample(cmsUserFeatureExample);
    }

    public void deleteUserFeatureByFeatureId(Integer featureId) {
        CmsUserFeatureExample cmsUserFeatureExample = new CmsUserFeatureExample();
        CmsUserFeatureExample.Criteria criteria = cmsUserFeatureExample.createCriteria();
        criteria.andFeatureIdEqualTo(featureId);
        cmsUserFeatureMapper.deleteByExample(cmsUserFeatureExample);
    }

    public void deleteUserRoleByRoleId(Integer roleId) {
        CmsUserRoleExample cmsUserRoleExample = new CmsUserRoleExample();
        CmsUserRoleExample.Criteria criteria = cmsUserRoleExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        cmsUserRoleMapper.deleteByExample(cmsUserRoleExample);
    }

    public void deleteUserRoleByUserId(Integer userId) {
        CmsUserRoleExample cmsUserRoleExample = new CmsUserRoleExample();
        CmsUserRoleExample.Criteria criteria = cmsUserRoleExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        cmsUserRoleMapper.deleteByExample(cmsUserRoleExample);
    }

    public List<ExtCmsFeatureGroup> getUserFeatures(Integer userId) {
        return extCmsFeatureMapper.getUserFeatures(userId);
    }

    public CmsUser getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<CmsRole> getUserRoles(Integer userId) {
        return extCmsFeatureMapper.getUserRoles(userId);
    }

    public void updateUserFeatures(Integer userId, List<ExtCmsFeature> features) {
        deleteUserFeatureByUserId(userId);
        ExtUserFeature extUserFeature = new ExtUserFeature();
        extUserFeature.setUserId(userId);
        extUserFeature.setFeatures(features);
        extUserCmsFeatureMapper.addFeatures(extUserFeature);
    }

    public void updateUserRoles(Integer userId, List<Integer> roleIds) {
        deleteUserRoleByUserId(userId);
        ExtUserRole extUserRole = new ExtUserRole();
        extUserRole.setUserId(userId);
        extUserRole.setRoleIds(roleIds);
        extCmsUserRoleMapper.addRoles(extUserRole);
    }

	public void resetPassword(CmsUser cmsUser) {
		userMapper.updateByPrimaryKeySelective(cmsUser);
	}

}
