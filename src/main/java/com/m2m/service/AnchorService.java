package com.m2m.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2m.domain.AppAnchor;
import com.m2m.domain.UserProfile;
import com.m2m.entity.ExtAnchor;
import com.m2m.entity.ExtAnchorInvite;
import com.m2m.entity.ExtAnchorInviteDetail;
import com.m2m.entity.ExtAnchorInviteDetailQuery;
import com.m2m.entity.ExtAnchorQuery;
import com.m2m.entity.ExtCmsAnchorInvite;
import com.m2m.entity.ExtCmsAnchorInviteDatail;
import com.m2m.entity.ExtCmsAppAnchor;
import com.m2m.entity.ExtGetAnchorInviteQuery;
import com.m2m.entity.ExtUserRegisterChannel;
import com.m2m.mapper.AppAnchorMapper;
import com.m2m.mapper.ExtAppAnchorMapper;
import com.m2m.util.DateUtil;

@Service
public class AnchorService extends BaseService {
	
	@Autowired
	private AppAnchorMapper appAnchorMapper;
	@Autowired
	private ExtAppAnchorMapper extAppAnchorMapper; 

	public ExtCmsAppAnchor getAnchorsByPage(Long pageIndex, Long pageSize) {
		ExtCmsAppAnchor extCmsAppAnchor = new ExtCmsAppAnchor();
		Long total = extAppAnchorMapper.getAppAnchorCount();
		Long start = getPageStart(total,pageSize,pageIndex);
		List<ExtAnchor> extAnchors = extAppAnchorMapper.getAppAnchorByPage(start,pageSize);
		extCmsAppAnchor.setTotal(total);
		extCmsAppAnchor.setData(extAnchors);
		return extCmsAppAnchor;
	}

	public void addAnchor(AppAnchor appAnchor) {
		appAnchorMapper.insertSelective(appAnchor);
	}

	public void deleteAnchor(Long id) {
		appAnchorMapper.deleteByPrimaryKey(id);
	}

	public void updateAnchor(AppAnchor appAnchor) {
		appAnchorMapper.updateByPrimaryKeySelective(appAnchor);
	}

	public ExtCmsAnchorInvite getAnchorInviteByPage(Long pageIndex, Long pageSize) {
		ExtCmsAnchorInvite extCmsAnchorInvite = new ExtCmsAnchorInvite();
		List<Long> uids = extAppAnchorMapper.getValidUids();
		if(uids.size()>0){
			Date timeBegin = DateUtil.getStartOfToday();
			Date timeEnd = DateUtil.getStartOfTomorrow();
			Long total = extAppAnchorMapper.getAnchorInviteCount(uids,timeBegin,timeEnd);
			Long start = getPageStart(total,pageSize,pageIndex);
			List<ExtAnchorInvite> extAnchorInvites = extAppAnchorMapper.getAnchorInviteByPage(start,pageSize,uids,timeBegin,timeEnd);
			extCmsAnchorInvite.setTotal(total);
			extCmsAnchorInvite.setData(extAnchorInvites);
		}
		return extCmsAnchorInvite;
	}

	public ExtCmsAnchorInvite getAnchorInviteByCondition(ExtGetAnchorInviteQuery extGetAnchorInviteQuery) {
		ExtCmsAnchorInvite extCmsAnchorInvite = new ExtCmsAnchorInvite();
		List<Long> uids = extAppAnchorMapper.getValidUids();
		if(uids.size()>0){
			Long total = extAppAnchorMapper.getAppAnchorCountByCondition(extGetAnchorInviteQuery,uids);
			if(extGetAnchorInviteQuery.getCreatedBegin()==null){
				extGetAnchorInviteQuery.setCreatedBegin(DateUtil.getStartOfToday());
			}
			if(extGetAnchorInviteQuery.getCreatedEnd()==null){
				extGetAnchorInviteQuery.setCreatedEnd(DateUtil.getStartOfTomorrow());
			}
			List<ExtAnchorInvite> extAnchorInvites = extAppAnchorMapper.getAnchorInviteByCondition(extGetAnchorInviteQuery,uids);
			extCmsAnchorInvite.setTotal(total);
			extCmsAnchorInvite.setData(extAnchorInvites);
		}
		return extCmsAnchorInvite;
	}

	public UserProfile isValidUid(long uid) {
		return extAppAnchorMapper.isValidUid(uid);
	}

	public ExtCmsAnchorInviteDatail getAnchorInviteDetailByPage(Long pageIndex, Long pageSize) {
		ExtCmsAnchorInviteDatail extCmsAnchorInviteDatail = new ExtCmsAnchorInviteDatail();
		List<Long> uids = extAppAnchorMapper.getValidUids();
		if(uids.size()>0){
			Date timeBegin = DateUtil.getStartOfToday();
			Date timeEnd = DateUtil.getStartOfTomorrow();
			Long total = extAppAnchorMapper.getAnchorInviteDetailCount(timeBegin,timeEnd,uids);
			Long start = getPageStart(total,pageSize,pageIndex);
			List<ExtAnchorInviteDetail> extAnchorInviteDetails = extAppAnchorMapper.getAnchorInviteDetailByPage(timeBegin,timeEnd,start,pageSize,uids);
			extAnchorInviteDetails = setRegisterChannel(extAnchorInviteDetails);
			extCmsAnchorInviteDatail.setData(extAnchorInviteDetails);
			extCmsAnchorInviteDatail.setTotal(total);
		}
		return extCmsAnchorInviteDatail;
	}
	
	private List<ExtAnchorInviteDetail> setRegisterChannel(List<ExtAnchorInviteDetail> extAnchorInviteDetails){
    	List<ExtUserRegisterChannel> usersRegisterChannels = extAppAnchorMapper.getUsersRegisterChannel();
    	for(ExtAnchorInviteDetail extAnchorInviteDetail : extAnchorInviteDetails){
    		for(ExtUserRegisterChannel extUserRegisterChannel : usersRegisterChannels){
    			if(extAnchorInviteDetail.getRegisterChannel().equals(extUserRegisterChannel.getCode())){
    				extAnchorInviteDetail.setRegisterChannel(extUserRegisterChannel.getChannelName());
    				break;
    			}
    		}
    	}
    	return extAnchorInviteDetails;
    }

	public ExtCmsAnchorInviteDatail getAnchorInviteDetailByCondition(ExtAnchorInviteDetailQuery extAnchorInviteDetailQuery) {
		ExtCmsAnchorInviteDatail extCmsAnchorInviteDatail = new ExtCmsAnchorInviteDatail();
		List<Long> uids = extAppAnchorMapper.getValidUids();
		if(uids.size()>0){
			if(extAnchorInviteDetailQuery.getJoinBegin()==null){
				extAnchorInviteDetailQuery.setJoinBegin(DateUtil.getStartOfToday());
			}
			if(extAnchorInviteDetailQuery.getJoinEnd()==null){
				extAnchorInviteDetailQuery.setJoinEnd(DateUtil.getStartOfTomorrow());
			}
			Long total = extAppAnchorMapper.getAnchorInviteDetailCountByCondition(extAnchorInviteDetailQuery,uids);
			List<ExtAnchorInviteDetail> extAnchorInviteDetails = extAppAnchorMapper.getAnchorInviteDetailByCondition(extAnchorInviteDetailQuery,uids);
			extAnchorInviteDetails = setRegisterChannel(extAnchorInviteDetails);
			extCmsAnchorInviteDatail.setData(extAnchorInviteDetails);
			extCmsAnchorInviteDatail.setTotal(total);
		}
		return extCmsAnchorInviteDatail;
	}

	public ExtCmsAppAnchor getAnchorsByCondition(ExtAnchorQuery extAnchorQuery) {
		ExtCmsAppAnchor extCmsAppAnchor = new ExtCmsAppAnchor();
		long total = extAppAnchorMapper.getAnchorsCountByCondition(extAnchorQuery);
		List<ExtAnchor> extAnchors = extAppAnchorMapper.getAnchorsByCondition(extAnchorQuery);
		extCmsAppAnchor.setData(extAnchors);
		extCmsAppAnchor.setTotal(total);
		return extCmsAppAnchor;
	}

	public List<ExtAnchorInviteDetail> exportAnchorInviteDetail(ExtAnchorInviteDetailQuery extAnchorInviteDetailQuery) {
		List<ExtAnchorInviteDetail> extAnchorInviteDetails = new ArrayList<ExtAnchorInviteDetail>();
		List<Long> uids = extAppAnchorMapper.getValidUids();
		if(uids.size()>0){
			if(extAnchorInviteDetailQuery.getJoinBegin()==null){
				extAnchorInviteDetailQuery.setJoinBegin(DateUtil.getStartOfToday());
			}
			if(extAnchorInviteDetailQuery.getJoinEnd()==null){
				extAnchorInviteDetailQuery.setJoinEnd(DateUtil.getStartOfTomorrow());
			}
			extAnchorInviteDetails = extAppAnchorMapper.getAnchorInviteDetailByCondition(extAnchorInviteDetailQuery,uids);
		}
		return extAnchorInviteDetails;
	}

}
