package com.m2m.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2m.Constant;
import com.m2m.domain.Content;
import com.m2m.domain.ContentExample;
import com.m2m.domain.User;
import com.m2m.domain.UserToken;
import com.m2m.entity.ExtAppUserQuery;
import com.m2m.entity.ExtCmsAllAppUser;
import com.m2m.entity.ExtCmsKingdomContent;
import com.m2m.entity.ExtCmsUserFragmentContent;
import com.m2m.entity.ExtImUser;
import com.m2m.entity.ExtUserFragmentContent;
import com.m2m.entity.ExtUserKingdomContent;
import com.m2m.entity.ExtUserProfile;
import com.m2m.entity.ExtUserRegisterChannel;
import com.m2m.exception.SystemException;
import com.m2m.exception.TimeParseFailException;
import com.m2m.exception.UserAlreadyExistsException;
import com.m2m.mapper.ContentMapper;
import com.m2m.mapper.ExtAppUserMapper;
import com.m2m.util.MD5;

@Service
public class AppUserService extends BaseService {
    @Autowired
    private ExtAppUserMapper extAppUserMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private LocalConfigService localConfigService;
    
    @Autowired
    private RedisService redisService;

    public static String getMask() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int temp = random.nextInt(10);
            sb.append(temp);
        }
        return sb.toString();
    }

    public ExtCmsAllAppUser getAllAppUsers(Long pageIndex, Long pageSize) throws SystemException {
        ExtCmsAllAppUser extCmsAllAppUser = new ExtCmsAllAppUser();
        Long total = extAppUserMapper.getAllAppUserCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtUserProfile> extUserProfiles = extAppUserMapper.getAllAppUserListByPage(start, pageSize);
        extUserProfiles = setRegisterChannelAndLastOptTime(extUserProfiles);
        extCmsAllAppUser.setData(extUserProfiles);
        extCmsAllAppUser.setTotal(total);
        return extCmsAllAppUser;
    }
    
    private List<ExtUserProfile> setRegisterChannelAndLastOptTime(List<ExtUserProfile> extUserProfiles) throws TimeParseFailException {
    	List<ExtUserRegisterChannel> usersRegisterChannels = extAppUserMapper.getUsersRegisterChannel();
    	for(ExtUserProfile extUserProfile : extUserProfiles){
    		String dateStr = redisService.get(Constant.USER_LASTTIME+extUserProfile.getUid());
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		try {
    			if(dateStr!=null){
    				Date lastOptAt = sdf.parse(dateStr);
    				extUserProfile.setLastOptAt(lastOptAt);
    			}
			} catch (ParseException e) {
				throw new TimeParseFailException();
			}
    		for(ExtUserRegisterChannel extUserRegisterChannel : usersRegisterChannels){
    			if(extUserProfile.getRegisterChannel().equals(extUserRegisterChannel.getCode())){
    				extUserProfile.setRegisterChannel(extUserRegisterChannel.getChannelName());
    				break;
    			}
    		}
    	}
    	return extUserProfiles;
	}
    
	public ExtCmsAllAppUser getAppUserSearch(ExtAppUserQuery extAppUserQuery) throws SystemException {
        ExtCmsAllAppUser extCmsAllAppUser = new ExtCmsAllAppUser();
        Long total = extAppUserMapper.getAllAppUserCountByCondition(extAppUserQuery);
        List<ExtUserProfile> extUserProfiles = extAppUserMapper.getAllAppUserListByCondition(extAppUserQuery);
        extUserProfiles = setRegisterChannelAndLastOptTime(extUserProfiles);
        extCmsAllAppUser.setData(extUserProfiles);
        extCmsAllAppUser.setTotal(total);
        return extCmsAllAppUser;
    }

    public void updateUserLevel(Long uid, Integer newLevel) {
        extAppUserMapper.updateUserLevel(uid, newLevel);
    }

    public void setUserSilentStatus(Long uid, Integer silentStatus) {
        if (silentStatus == 1) {
            extAppUserMapper.setUserSilentStatus(uid);
        } else {
            extAppUserMapper.deleteUserSilentStatus(uid);
        }
    }

    public void setUserV(Long uid, Integer isV) {
        extAppUserMapper.setUserV(uid, isV);
    }

    public void setUserStatus(Long uid, Integer status) {
        extAppUserMapper.setUserStatus(uid, status);
    }

    public void setUserInviteStatus(Long uid, Integer isInvited) {
        extAppUserMapper.setUserInviteStatus(uid, isInvited);
    }

    public void addRobot(Long phoneBegin, Integer creatCount, String originPassword) throws SystemException {
        List<User> users = new ArrayList<User>();
        List<String> phones = new ArrayList<String>();
        for (int i = 0; i < creatCount; i++) {
            User user = new User();
            String salt = getMask();
            user.setEncrypt(MD5.getMD5InHex(originPassword, salt));
            user.setSalt(salt);
            user.setUserName(phoneBegin + "" + i);
            user.setCreateTime(new Date());
            users.add(user);
            phones.add(phoneBegin + "" + i);
        }
        List<User> existUserList = extAppUserMapper.checkUserExistByPhoneList(phones);
        for (int i = 0; i < users.size(); i++) {
            User newUser = users.get(i);
            for (User existUser : existUserList) {
                if (newUser.getUserName().equals(existUser.getUserName())) {
                    users.remove(newUser);
                    continue;
                }
            }
        }
        if (users.size() > 0) {
            extAppUserMapper.createUserByList(users);
            List<ExtImUser> extImUserList = new ArrayList<>();
            for (User user : users) {
                ExtImUser extImUser = localConfigService.getIMUserToken(String.valueOf(user.getUid()), user.getUserName(), "");
                if (extImUser.getUserId() == null) {
                    extImUser.setUserId(String.valueOf(user.getUid()));
                }
                if (extImUser.getToken() == null) {
                    extImUser.setToken(" ");
                }
                extImUserList.add(extImUser);
            }
            extAppUserMapper.createImConfigByList(extImUserList);
            extAppUserMapper.createUserProfileByList(users);
            List<UserToken> userTokens = new ArrayList<>();
            for (User user : users) {
                UserToken userToken = new UserToken();
                userToken.setUid(user.getUid());
                userToken.setToken(getUserToken());
                userTokens.add(userToken);
            }
            extAppUserMapper.createUserTokeByList(userTokens);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    public String getUserToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public ExtCmsKingdomContent getUserKingdomContent(Long uid, Long pageIndex, Long pageSize) {
        ExtCmsKingdomContent extCmsKingdomContent = new ExtCmsKingdomContent();
        Long total = extAppUserMapper.getUserKingdomContentCountByUid(uid);
        Long start = getPageStart(total, pageSize, pageIndex);
        extCmsKingdomContent.setTotal(total);
        List<ExtUserKingdomContent> extUserKingdomContents = extAppUserMapper.getUserKingdomContentByPageAndUid(uid, start, pageSize);
        extCmsKingdomContent.setData(extUserKingdomContents);
        return extCmsKingdomContent;
    }

    public ExtCmsUserFragmentContent getUserFragmentContent(Long uid, Long pageIndex, Long pageSize) {
        ExtCmsUserFragmentContent extCmsUserFragmentContent = new ExtCmsUserFragmentContent();
        Long total = extAppUserMapper.getUserFragmentContentCountByUid(uid);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtUserFragmentContent> extUserFragmentContents = extAppUserMapper.getUserFragmentContentByUidAndPage(uid, start, pageSize);
        extCmsUserFragmentContent.setData(extUserFragmentContents);
        extCmsUserFragmentContent.setTotal(total);
        return extCmsUserFragmentContent;
    }

    public void deleteUserFragmentContent(Long id) {
        extAppUserMapper.deleteTopicFragmentById(id);
        extAppUserMapper.deleteTopicImageByFid(id);
        extAppUserMapper.deleteTopicBarrageByFid(id);
        extAppUserMapper.insertDeleteLog(1, id, -100);
    }

    public void deleteUserKingdomContent(Long id) {
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andForwardCidEqualTo(id);
        criteria.andTypeEqualTo(3);
        List<Content> contents = contentMapper.selectByExample(example);
        if (contents.size() > 0) {
            Content content = contents.get(0);
            Long forwardCid = content.getForwardCid();
            extAppUserMapper.deleteTopicById(forwardCid);
            extAppUserMapper.insertDeleteLog(5, forwardCid, -100);
            extAppUserMapper.updateAkingdomByTopicId(forwardCid);
            extAppUserMapper.deleteAkingdomListByTopicId(forwardCid);
            extAppUserMapper.deleteAggregationTopic(forwardCid);
            extAppUserMapper.updateTopicAggregationApply(forwardCid);
            extAppUserMapper.deleteBannerTopic(forwardCid);
            extAppUserMapper.deleteTopicTagByTopicId(forwardCid);
            extAppUserMapper.deleteTopicBillboard(forwardCid);
            extAppUserMapper.deleteTopicListed(forwardCid);
        }
    }

	public List<ExtUserRegisterChannel> getUsersRegisterChannel() {
		return extAppUserMapper.getUsersRegisterChannel();
	}

}
