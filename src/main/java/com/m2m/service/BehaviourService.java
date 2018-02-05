package com.m2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2m.entity.ExtAppPage;
import com.m2m.entity.ExtCmsRegisteredUser;
import com.m2m.entity.ExtCmsUserBehaviour;
import com.m2m.entity.ExtCmsUserBehaviourQuery;
import com.m2m.entity.ExtCmsUserBehaviourTotal;
import com.m2m.entity.ExtCmsUserBehaviourTotalQuery;
import com.m2m.entity.ExtRegisteredUser;
import com.m2m.entity.ExtRegisteredUserQuery;
import com.m2m.entity.ExtUserBehaviour;
import com.m2m.entity.ExtUserRegisterChannel;
import com.m2m.mapper.ExtBehaviourMapper;

@Service
public class BehaviourService extends BaseService {

    @Autowired
    private ExtBehaviourMapper extBehaviourMapper;

    public ExtCmsRegisteredUser getRegisteredUser(Long pageIndex, Long pageSize) {
        ExtCmsRegisteredUser extCmsRegisteredUser = new ExtCmsRegisteredUser();
        Long total = extBehaviourMapper.getRegisteredUserCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtRegisteredUser> extRegisteredUsers = extBehaviourMapper.getRegisteredUserByPage(start, pageSize);
        extRegisteredUsers = setRegisterChannel(extRegisteredUsers);
        extCmsRegisteredUser.setData(extRegisteredUsers);
        extCmsRegisteredUser.setTotal(total);
        return extCmsRegisteredUser;
    }
    
    private List<ExtRegisteredUser> setRegisterChannel(List<ExtRegisteredUser> extRegisteredUsers){
    	List<ExtUserRegisterChannel> usersRegisterChannels = extBehaviourMapper.getUsersRegisterChannel();
    	for(ExtRegisteredUser extRegisteredUser : extRegisteredUsers){
    		for(ExtUserRegisterChannel extUserRegisterChannel : usersRegisterChannels){
    			if(extRegisteredUser.getRegisterChannel().equals(extUserRegisterChannel.getCode())){
    				extRegisteredUser.setRegisterChannel(extUserRegisterChannel.getChannelName());
    				break;
    			}
    		}
    	}
    	return extRegisteredUsers;
    }

    public ExtCmsRegisteredUser getRegisteredUserSearch(ExtRegisteredUserQuery extRegisteredUserQuery) {
        ExtCmsRegisteredUser extCmsRegisteredUser = new ExtCmsRegisteredUser();
        Long total = extBehaviourMapper.getRegisteredUserCountByCondition(extRegisteredUserQuery);
        List<ExtRegisteredUser> extRegisteredUsers = extBehaviourMapper.getRegisteredUserByCondition(extRegisteredUserQuery);
        extRegisteredUsers = setRegisterChannel(extRegisteredUsers);
        extCmsRegisteredUser.setData(extRegisteredUsers);
        extCmsRegisteredUser.setTotal(total);
        return extCmsRegisteredUser;
    }

    public ExtCmsUserBehaviour getUserBehaviors(Long pageIndex, Long pageSize) {
        ExtCmsUserBehaviour extCmsUserBehaviour = new ExtCmsUserBehaviour();
        Long total = extBehaviourMapper.getUserBehaviorsCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtUserBehaviour> extUserBehaviours = extBehaviourMapper.getUserBehaviorsByPage(start, pageSize);
        extCmsUserBehaviour.setData(extUserBehaviours);
        extCmsUserBehaviour.setTotal(total);
        return extCmsUserBehaviour;
    }

    public ExtCmsUserBehaviour getUserBehaviorsSearch(ExtCmsUserBehaviourQuery extCmsUserBehaviourQuery) {
        ExtCmsUserBehaviour extCmsUserBehaviour = new ExtCmsUserBehaviour();
        Long total = extBehaviourMapper.getUserBehaviorsCountByCondition(extCmsUserBehaviourQuery);
        List<ExtUserBehaviour> extUserBehaviours = extBehaviourMapper.getUserBehaviorsByCondition(extCmsUserBehaviourQuery);
        extCmsUserBehaviour.setData(extUserBehaviours);
        extCmsUserBehaviour.setTotal(total);
        return extCmsUserBehaviour;
    }

    public ExtCmsUserBehaviourTotal getUserBehaviorsTotal() {
        return extBehaviourMapper.getUserBehaviorsTotal();
    }

    public ExtCmsUserBehaviourTotal getUserBehaviorsTotalByCondition(ExtCmsUserBehaviourTotalQuery extCmsUserBehaviourTotalQuery) {
        return extBehaviourMapper.getUserBehaviorsTotalByCondition(extCmsUserBehaviourTotalQuery);
    }

    public List<ExtAppPage> getAppAllPages() {
        return extBehaviourMapper.getAppAllPages();
    }


}
