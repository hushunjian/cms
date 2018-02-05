package com.m2m.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.m2m.Constant;
import com.m2m.domain.Content;
import com.m2m.domain.ContentExample;
import com.m2m.domain.KingdomCountDayExample;
import com.m2m.domain.LiveFavorite;
import com.m2m.domain.LiveFavoriteExample;
import com.m2m.domain.LotteryAppoint;
import com.m2m.domain.LotteryAppointExample;
import com.m2m.domain.Topic;
import com.m2m.domain.TopicExample;
import com.m2m.domain.TopicFragmentWithBLOBs;
import com.m2m.domain.TopicListed;
import com.m2m.domain.TopicNews;
import com.m2m.domain.UserNo;
import com.m2m.domain.UserNoExample;
import com.m2m.domain.UserProfile;
import com.m2m.domain.UserProfileExample;
import com.m2m.entity.ExtCmsKingdom;
import com.m2m.entity.ExtCmsKingdomListed;
import com.m2m.entity.ExtCmsKingdomReview;
import com.m2m.entity.ExtCmsKingdomValue;
import com.m2m.entity.ExtCmsLottery;
import com.m2m.entity.ExtCmsLotteryUser;
import com.m2m.entity.ExtInvitationNum;
import com.m2m.entity.ExtKingdom;
import com.m2m.entity.ExtKingdomQuery;
import com.m2m.entity.ExtKingdomRestTime;
import com.m2m.entity.ExtKingdomReview;
import com.m2m.entity.ExtKingdomUserQuery;
import com.m2m.entity.ExtKingdomValue;
import com.m2m.entity.ExtListedKingdom;
import com.m2m.entity.ExtLottery;
import com.m2m.entity.ExtLotteryUser;
import com.m2m.entity.ExtUserRegisterChannel;
import com.m2m.eventbus.ApplicationEventBus;
import com.m2m.eventbus.event.PersonalMessageEvent;
import com.m2m.exception.CanNotChangKingToYourSelfException;
import com.m2m.exception.NewKingNotExistsException;
import com.m2m.exception.OldKingNotExistsException;
import com.m2m.exception.SystemException;
import com.m2m.exception.TimeParseFailException;
import com.m2m.exception.TopicListedNotFindException;
import com.m2m.exception.TopicNotExistsException;
import com.m2m.exception.UserNotFindException;
import com.m2m.mapper.ContentMapper;
import com.m2m.mapper.ExtKingdomMapper;
import com.m2m.mapper.ExtLotteryInfoMapper;
import com.m2m.mapper.KingdomCountDayMapper;
import com.m2m.mapper.LiveFavoriteMapper;
import com.m2m.mapper.LotteryAppointMapper;
import com.m2m.mapper.TopicFragmentMapper;
import com.m2m.mapper.TopicListedMapper;
import com.m2m.mapper.TopicMapper;
import com.m2m.mapper.TopicNewsMapper;
import com.m2m.mapper.UserNoMapper;
import com.m2m.mapper.UserProfileMapper;
import com.m2m.util.DateUtil;

@Service
public class KingdomService extends BaseService {

    @Autowired
    private KingdomCountDayMapper kingdomCountDayMapper;

    @Autowired
    private ExtKingdomMapper extKingdomMapper;

    @Autowired
    private ExtLotteryInfoMapper extLotteryInfoMapper;

    @Autowired
    private LotteryAppointMapper lotteryAppointMapper;

    @Autowired
    private LocalConfigService localConfigService;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private LiveFavoriteMapper liveFavoriteMapper;

    @Autowired
    private UserNoMapper userNoMapper;

    @Autowired
    private TopicListedMapper topicListedMapper;

    @Autowired
    private TopicFragmentMapper topicFragmentMapper;

    @Autowired
    private TopicNewsMapper topicNewsMapper;
    
    @Autowired
    private ApplicationEventBus applicationEventBus;

    private static final String EXCHANGE_RATE = "EXCHANGE_RATE";

    public Long getKingdomCount() {
        KingdomCountDayExample example = new KingdomCountDayExample();
        return kingdomCountDayMapper.countByExample(example);
    }

    public List<ExtLotteryUser> setInvitationNumByList(List<ExtLotteryUser> extLotteryUsers) {
        if (extLotteryUsers.size() > 0) {
            List<Long> uids = new ArrayList<Long>();
            for (ExtLotteryUser extLotteryUser : extLotteryUsers) {
                uids.add(extLotteryUser.getUserId());
            }
            List<ExtInvitationNum> invitationNumList = extLotteryInfoMapper.getInvitationNumByUids(uids);
            for (ExtLotteryUser u : extLotteryUsers) {
                u.setInvitationNum(0);
                for (ExtInvitationNum num : invitationNumList) {
                    if (num.getUid().equals(u.getUserId())) {
                        u.setInvitationNum(num.getInvitationNum());
                    }
                }
            }
        }
        return extLotteryUsers;
    }

    public List<ExtKingdom> setKingdomLinkByList(List<ExtKingdom> extKingdoms) {
        for (ExtKingdom extKingdom : extKingdoms) {
            extKingdom.setLink(String.format("%s%s%s", localConfigService.getWebappUrl(), "/", extKingdom.getTopicId()));
        }
        return extKingdoms;
    }

    public ExtCmsKingdom getAllKingdomsInfoByPage(Long pageIndex, Long pageSize) {
        ExtCmsKingdom extCmsKingdomToKingdomInfo = new ExtCmsKingdom();
        Long totalRecord = getKingdomCount();
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtKingdom> extKingdoms = extKingdomMapper.getAllKingdomsInfoByPage(start, pageSize);
        extKingdoms = setKingdomLinkByList(extKingdoms);
        extCmsKingdomToKingdomInfo.setTotal(totalRecord);
        extCmsKingdomToKingdomInfo.setData(extKingdoms);
        return extCmsKingdomToKingdomInfo;
    }

    public ExtCmsLottery getAllLotteriesByPage(Long pageIndex, Long pageSize) {
        ExtCmsLottery extCmsLotteryInfo = new ExtCmsLottery();
        Long totalRecord = extLotteryInfoMapper.getAllLotteriesCount();
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtLottery> extLotterys = extLotteryInfoMapper.getAllLotteriesByPage(start, pageSize);
        extCmsLotteryInfo.setData(extLotterys);
        extCmsLotteryInfo.setTotal(totalRecord);
        return extCmsLotteryInfo;
    }

    public ExtCmsKingdom getKingdomSearch(ExtKingdomQuery extKingdomQuery) {
        ExtCmsKingdom extCmsKingdomToKingdomInfo = new ExtCmsKingdom();
        Long totalRecord = extKingdomMapper.getKingdomCountByCondition(extKingdomQuery);
        List<ExtKingdom> extKingdoms = extKingdomMapper.getKingdomInfoByCondition(extKingdomQuery);
        extKingdoms = setKingdomLinkByList(extKingdoms);
        extCmsKingdomToKingdomInfo.setTotal(totalRecord);
        extCmsKingdomToKingdomInfo.setData(extKingdoms);
        return extCmsKingdomToKingdomInfo;
    }

    public ExtCmsLottery getLotterySearch(Long pageIndex, Long pageSize, String topicName, String lotteryName) {
        ExtCmsLottery extCmsLotteryInfo = new ExtCmsLottery();
        Long totalRecord = extLotteryInfoMapper.getLotteriesCountByCondition(topicName, lotteryName);
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtLottery> extLotterys = extLotteryInfoMapper.getLotteriesByCondition(start, pageSize, topicName, lotteryName);
        extCmsLotteryInfo.setData(extLotterys);
        extCmsLotteryInfo.setTotal(totalRecord);
        return extCmsLotteryInfo;
    }

    public ExtCmsLotteryUser getLotteryUsers(Long pageIndex, Long pageSize, Integer lotteryId) {
        ExtCmsLotteryUser extCmsLotteryUserInfo = new ExtCmsLotteryUser();
        Long totalRecord = extLotteryInfoMapper.getLotteryUsersCountByLotteryId(lotteryId);
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtLotteryUser> extLotteryUsers = extLotteryInfoMapper.getLotteryUsersInfoByLotteryIdAndPage(start, pageSize, lotteryId);
        extLotteryUsers = setInvitationNumByList(extLotteryUsers);
        extCmsLotteryUserInfo.setTotal(totalRecord);
        extCmsLotteryUserInfo.setData(extLotteryUsers);
        return extCmsLotteryUserInfo;
    }

    public ExtCmsLotteryUser getLotteryUserSearch(Long pageIndex, Long pageSize,
                                                  Integer lotteryId, String userNick, Integer isAppoint) {
        ExtCmsLotteryUser extCmsLotteryUserInfo = new ExtCmsLotteryUser();
        Long totalRecord = extLotteryInfoMapper.getLotteryUsersSearchCountByCondition(lotteryId, userNick, isAppoint);
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtLotteryUser> extLotteryUsers = extLotteryInfoMapper.getLotteryUsersSearchInfoByCondition(start, pageSize, lotteryId, userNick, isAppoint);
        extLotteryUsers = setInvitationNumByList(extLotteryUsers);
        extCmsLotteryUserInfo.setTotal(totalRecord);
        extCmsLotteryUserInfo.setData(extLotteryUsers);
        return extCmsLotteryUserInfo;
    }

    public void setLotteryAppoint(Long lotteryId, Long userId, Integer isAppoint) {
        LotteryAppointExample example = new LotteryAppointExample();
        LotteryAppointExample.Criteria criteria = example.createCriteria();
        criteria.andLotteryIdEqualTo(lotteryId);
        criteria.andUidEqualTo(userId);
        List<LotteryAppoint> lotteryAppoints = lotteryAppointMapper.selectByExample(example);
        LotteryAppoint lotteryAppoint = null;
        if (lotteryAppoints.size() > 0) {
            lotteryAppoint = lotteryAppoints.get(0);
        }
        if (isAppoint == 0) {
            if (lotteryAppoint != null) {
                lotteryAppointMapper.deleteByPrimaryKey(lotteryAppoint.getId());
            }
        } else {
            if (lotteryAppoint == null) {
                lotteryAppoint = new LotteryAppoint();
                lotteryAppoint.setLotteryId(lotteryId);
                lotteryAppoint.setUid(userId);
                lotteryAppointMapper.insertSelective(lotteryAppoint);
            }
        }
    }

    public void refreshCache() {
        extKingdomMapper.truncateKingdomCountDay();
        extKingdomMapper.statKingdomCountDay();
    }

    public ExtCmsKingdomValue getKingdomValues(Long pageIndex, Long pageSize) {
        ExtCmsKingdomValue extCmsKingdomValue = new ExtCmsKingdomValue();
        Long total = extKingdomMapper.getKingdomValueCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtKingdomValue> extKingdomValues = extKingdomMapper.getKingdomValuesByPage(start, pageSize);
        extCmsKingdomValue.setData(extKingdomValues);
        extCmsKingdomValue.setTotal(total);
        return extCmsKingdomValue;
    }

    public ExtCmsKingdomValue getKingdomValueSearch(Long pageIndex, Long pageSize,
                                                    String topicName, String orderParam, String order) {
        ExtCmsKingdomValue extCmsKingdomValue = new ExtCmsKingdomValue();
        Long total = extKingdomMapper.getKingdomValueCountByCondition(topicName);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtKingdomValue> extKingdomValues = extKingdomMapper.getKingdomValuesByConditionAndByPage(start, pageSize, topicName, orderParam, order);
        extCmsKingdomValue.setData(extKingdomValues);
        extCmsKingdomValue.setTotal(total);
        return extCmsKingdomValue;
    }

    public ExtCmsKingdomReview getKingdomUsers(Long pageIndex, Long pageSize, Long topicId) {
        ExtCmsKingdomReview extCmsKingdomReview = new ExtCmsKingdomReview();
        Date createTimeBegin = DateUtil.getStartOfToday();
        Date createTimeEnd = DateUtil.getStartOfTomorrow();
        List<Long> uids = getUidByTopicIdAndTime(topicId,createTimeBegin,createTimeEnd,null);
        if(uids.size()>0){
        	Long total = extKingdomMapper.getKingdomUserCountByTopicId(topicId,uids,createTimeBegin,createTimeEnd);
        	Long start = getPageStart(total, pageSize, pageIndex);
        	List<ExtKingdomReview> extKingdomReviews = extKingdomMapper.getKingdomUserByTopicAndPage(start, pageSize, topicId,uids,createTimeBegin,createTimeEnd);
        	extKingdomReviews = setRegisterChannel(extKingdomReviews);
        	extCmsKingdomReview.setData(extKingdomReviews);
        	extCmsKingdomReview.setTotal(total);
        }
        return extCmsKingdomReview;
    }
    
    private List<ExtKingdomReview> setRegisterChannel(List<ExtKingdomReview> extKingdomReviews){
    	List<ExtUserRegisterChannel> usersRegisterChannels = extKingdomMapper.getUsersRegisterChannel();
    	for(ExtKingdomReview extKingdomReview : extKingdomReviews){
    		for(ExtUserRegisterChannel extUserRegisterChannel : usersRegisterChannels){
    			if(extKingdomReview.getRegisterChannel().equals(extUserRegisterChannel.getCode())){
    				extKingdomReview.setRegisterChannel(extUserRegisterChannel.getChannelName());
    				break;
    			}
    		}
    	}
    	return extKingdomReviews;
    }
    
    private List<Long> getUidByTopicIdAndTime(Long topicId,Date createTimeBegin,Date createTimeEnd,String fragment){
    	return extKingdomMapper.getUidsByTopicIdAndTime(topicId,createTimeBegin,createTimeEnd,fragment);
    }
    
    public ExtCmsKingdomReview getKingdomUserSearch(ExtKingdomUserQuery extKingdomUserQuery) {
        ExtCmsKingdomReview extCmsKingdomReview = new ExtCmsKingdomReview();
        if(extKingdomUserQuery.getJoinBegin() == null){
        	extKingdomUserQuery.setJoinBegin(DateUtil.getStartOfToday());
    	}
    	if(extKingdomUserQuery.getJoinEnd() == null){
    		extKingdomUserQuery.setJoinEnd(DateUtil.getStartOfTomorrow());
    	}
        List<Long> uids = getUidByTopicIdAndTime(extKingdomUserQuery.getTopicId(),extKingdomUserQuery.getJoinBegin(),extKingdomUserQuery.getJoinEnd(),extKingdomUserQuery.getFragment());
        if(uids.size()>0){
        	Long total = extKingdomMapper.getKingdomUserCountByCondition(extKingdomUserQuery,uids);
        	List<ExtKingdomReview> extKingdomReviews = extKingdomMapper.getKingdomUserByCondition(extKingdomUserQuery,uids);
        	extKingdomReviews = setRegisterChannel(extKingdomReviews);
        	extCmsKingdomReview.setData(extKingdomReviews);
        	extCmsKingdomReview.setTotal(total);
        }
        return extCmsKingdomReview;
    }

    public ExtCmsKingdomListed getListedKingdoms(Long pageIndex, Long pageSize) {
        ExtCmsKingdomListed extCmsKingdomListed = new ExtCmsKingdomListed();
        Long total = extKingdomMapper.getListedKingdomCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtListedKingdom> extListedKingdoms = extKingdomMapper.getListedKingdomsByPage(start, pageSize);
        extCmsKingdomListed.setData(extListedKingdoms);
        extCmsKingdomListed.setTotal(total);
        return extCmsKingdomListed;
    }

    public ExtCmsKingdomListed getListedKingdomSearch(String topicName, Integer status, Long pageIndex, Long pageSize) {
        ExtCmsKingdomListed extCmsKingdomListed = new ExtCmsKingdomListed();
        Long total = extKingdomMapper.getListedKingdomCountByCondition(topicName, status);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtListedKingdom> extListedKingdoms = extKingdomMapper.getListedKingdomsByConditionAndPage(topicName, status, start, pageSize);
        extCmsKingdomListed.setData(extListedKingdoms);
        extCmsKingdomListed.setTotal(total);
        return extCmsKingdomListed;
    }

    public void setKingdomRestTime(String startAt, String endAt) {
        redisService.set("REST_LISTED_START_DATE", startAt);
        redisService.set("REST_LISTED_END_DATE", endAt);
    }

    public Topic modifyTopic(Topic topic, Long uid) {
        JSONArray array = JSON.parseArray(topic.getCoreCircle());
        boolean needAdd = true;
        for (int i = 0; i < array.size(); i++) {
            if (array.getLong(i) == uid) {
                needAdd = false;
                break;
            }
        }
        Topic modifyTopic = new Topic();
        modifyTopic.setUid(uid);
        if (needAdd) {
            array.add(uid);
            modifyTopic.setCoreCircle(array.toString());
        }
        return modifyTopic;
    }

    public void updateKing(Long topicId, Long sourceUid, Long targetUid, Long optUid) throws UserNotFindException {
        UserProfile userProfile = getUserProfile(targetUid);
        if (userProfile == null) {
            throw new UserNotFindException();
        }
        Topic topic = topicMapper.selectByPrimaryKey(topicId);
        Topic updateTopic = modifyTopic(topic, targetUid);
        updateTopic(updateTopic, topicId);
        modifyLiveFavorite(topicId, sourceUid, targetUid);
        updateContent(targetUid, topicId);
        extKingdomMapper.insertTopicChangeHis(topicId, optUid, targetUid, sourceUid);
    }

    public void modifyLiveFavorite(Long topicId, Long sourceUid, Long targetUid) {
        List<LiveFavorite> sourceLiveFavorites = getLiveFavorites(sourceUid, topicId);
        if (sourceLiveFavorites.size() == 0) {
            LiveFavorite liveFavorite = new LiveFavorite();
            liveFavorite.setUid(sourceUid);
            liveFavorite.setTopicId(topicId);
            liveFavoriteMapper.insertSelective(liveFavorite);
        }
        List<LiveFavorite> stargetLiveFavorites = getLiveFavorites(targetUid, topicId);
        if (stargetLiveFavorites.size() > 0) {
            liveFavoriteMapper.deleteByPrimaryKey(stargetLiveFavorites.get(0).getId());
        }
    }

    public UserProfile getUserProfile(Long uid) {
        UserProfileExample example = new UserProfileExample();
        UserProfileExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<UserProfile> userProfiles = userProfileMapper.selectByExample(example);
        return userProfiles.size() > 0 ? userProfiles.get(0) : null;
    }

    public void updateTopic(Topic topic, Long topicId) {
        TopicExample example = new TopicExample();
        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(topicId);
        topicMapper.updateByExampleSelective(topic, example);
    }

    public void updateContent(Long uid, Long topicId) {
        Content record = new Content();
        record.setUid(uid);
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andForwardCidEqualTo(topicId);
        criteria.andTypeEqualTo(3);
        contentMapper.updateByExampleSelective(record, example);
    }

    public List<LiveFavorite> getLiveFavorites(Long uid, Long topicId) {
        LiveFavoriteExample example = new LiveFavoriteExample();
        LiveFavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andTopicIdEqualTo(topicId);
        return liveFavoriteMapper.selectByExample(example);
    }

    public void setListedKingdomStatus(Long id, Integer status) {
        extKingdomMapper.setListedKingdomStatus(id, status);
    }

    public void kingdomTransaction(Long id, Long meNumber) throws SystemException {
        UserNo userNo = getUserNoByMeNumber(meNumber);
        if (userNo == null) {
            throw new UserNotFindException();
        }
        TopicListed topicListed = topicListedMapper.selectByPrimaryKey(id);
        if (topicListed == null) {
            throw new TopicListedNotFindException();
        }
        Topic topic = topicMapper.selectByPrimaryKey(topicListed.getTopicId());
        if (topic == null) {
            throw new TopicNotExistsException();
        }
        UserProfile oldKing = getUserProfileByUid(topic.getUid());
        if (null == oldKing) {
            throw new OldKingNotExistsException();
        }
        UserProfile newKing = getUserProfileByUid(userNo.getUid());
        if (null == newKing) {
            throw new NewKingNotExistsException();
        }
        if (topic.getUid() == userNo.getUid()) {
            throw new CanNotChangKingToYourSelfException();
        }
        Topic updateTopic = modifyTopic(topic, userNo.getUid());
        updateTopic.setId(topic.getId());
        updateTopic(updateTopic, topic.getId());
        modifyLiveFavorite(topic.getId(), topic.getUid(), userNo.getUid());
        extKingdomMapper.updateContentUid(userNo.getUid(), topic.getId());
        extKingdomMapper.insertTransferRecord(newKing.getUid(), oldKing.getUid(), topicListed.getPrice(),
                topicListed.getPriceRmb(), topic.getId());
        TopicFragmentWithBLOBs topicFragment = new TopicFragmentWithBLOBs();
        topicFragment.setTopicId(topic.getId());
        topicFragment.setUid(newKing.getUid());
        topicFragment.setType(52);
        topicFragment.setContentType(21);
        JSONObject extra = new JSONObject();
        extra.put("type", "kingdomOTD");
        extra.put("only", UUID.randomUUID().toString() + "-" + new Random().nextInt());
        extra.put("price", topicListed.getPrice());
        extra.put("uid", newKing.getUid());
        extra.put("avatar", Constant.QINIU_DOMAIN + "/" + newKing.getAvatar());
        extra.put("name", newKing.getNickName());
        extra.put("oldUid", oldKing.getUid());
        extra.put("oldAvatar", Constant.QINIU_DOMAIN + "/" + oldKing.getAvatar());
        extra.put("oldName", oldKing.getNickName());
        topicFragment.setExtra(extra.toJSONString());
        topicFragment.setScore(0);
        topicFragmentMapper.insertSelective(topicFragment);
        extKingdomMapper.deleteBlockTopicByTopicId(topic.getId());
        String exchangeRageConfig = redisService.get(Constant.APP_CONFIG_KEY_PRE + EXCHANGE_RATE);
        int exchangeRage = 100;
        if (!StringUtils.isEmpty(exchangeRageConfig)) {
            exchangeRage = Integer.valueOf(exchangeRageConfig).intValue();
        }
        double priceRMB = (double) topic.getPrice() / (double) exchangeRage;
        priceRMB = new BigDecimal(priceRMB).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        TopicNews topicNews = new TopicNews();
        topicNews.setTopicId(topic.getId());
        topicNews.setType(0);
        topicNews.setContent(oldKing.getNickName() + "的《" + topic.getTitle() + "》以" + priceRMB + "元成功转让，欢迎新国王" + newKing.getNickName() + "闪亮登场");
        topicNewsMapper.insertSelective(topicNews);
        topicListedMapper.deleteByPrimaryKey(topicListed.getId());
        StringBuffer oldKingMessage = new StringBuffer();
        oldKingMessage.append("您上市的王国《").append(topic.getTitle()).append("》已成交，快去确认收款吧。");
        PersonalMessageEvent oldKingMessageEvent = new PersonalMessageEvent();
        oldKingMessageEvent.setUid(topic.getUid());
        oldKingMessageEvent.setMessage(oldKingMessage.toString());
        applicationEventBus.post(oldKingMessageEvent);
        
        StringBuffer newKingMessage = new StringBuffer();
        newKingMessage.append("您收购的王国《").append(topic.getTitle()).append("》已经成交，您已经成为新国王了，快去您的王国看看吧！");
        PersonalMessageEvent newKingMessageEvent = new PersonalMessageEvent();
        newKingMessageEvent.setUid(newKing.getUid());
        newKingMessageEvent.setMessage(newKingMessage.toString());
        applicationEventBus.post(newKingMessageEvent);
    }

    public UserProfile getUserProfileByUid(Long uid) {
        UserProfileExample example = new UserProfileExample();
        UserProfileExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<UserProfile> userProfiles = userProfileMapper.selectByExample(example);
        return userProfiles.size() > 0 ? userProfiles.get(0) : null;
    }

    public UserNo getUserNoByMeNumber(Long meNumber) {
        UserNoExample example = new UserNoExample();
        example.createCriteria().andMeNumberEqualTo(meNumber);
        List<UserNo> list = userNoMapper.selectByExample(example);
        return list.size() > 0 ? list.get(0) : null;
    }


    public ExtCmsKingdomListed getPendingListedKingdoms(Long pageIndex, Long pageSize) {
        ExtCmsKingdomListed extCmsKingdomListed = new ExtCmsKingdomListed();
        Long total = extKingdomMapper.getPendingListedKingdomsCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtListedKingdom> extListedKingdoms = extKingdomMapper.getPendingListedKingdomsByPage(start, pageSize);
        extCmsKingdomListed.setData(extListedKingdoms);
        extCmsKingdomListed.setTotal(total);
        return extCmsKingdomListed;
    }

    public ExtCmsKingdomListed getPendingListedKingdomSearch(String topicName, Long pageIndex, Long pageSize) {
        ExtCmsKingdomListed extCmsKingdomListed = new ExtCmsKingdomListed();
        Long total = extKingdomMapper.getPendingListedKingdomCountByCondition(topicName);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtListedKingdom> extListedKingdoms = extKingdomMapper.getPendingListedKingdomByPageAndCondition(topicName, start, pageSize);
        extCmsKingdomListed.setData(extListedKingdoms);
        extCmsKingdomListed.setTotal(total);
        return extCmsKingdomListed;
    }

    public ExtKingdomRestTime getKingdomRestTime() throws SystemException {
        ExtKingdomRestTime extKingdomRestTime = new ExtKingdomRestTime();
        String restStrDateBegin = redisService.get("REST_LISTED_START_DATE");
        String restStrDateEnd = redisService.get("REST_LISTED_END_DATE");
        try {
        	if(!StringUtils.isEmpty(restStrDateBegin)){
        		extKingdomRestTime.setStartAt(new SimpleDateFormat("yyyy-MM-dd").parse(restStrDateBegin).getTime());
        	}
        	if(!StringUtils.isEmpty(restStrDateEnd)){
        		extKingdomRestTime.setEndAt(new SimpleDateFormat("yyyy-MM-dd").parse(restStrDateEnd).getTime());
        	}
        } catch (ParseException e) {
            throw new TimeParseFailException();
        }
        return extKingdomRestTime;
    }

	public List<ExtKingdomReview> exportKingdomUsers(ExtKingdomUserQuery extKingdomUserQuery) {
		List<ExtKingdomReview> extKingdomReviews  = new ArrayList<ExtKingdomReview>();
        if(extKingdomUserQuery.getJoinBegin() == null){
        	extKingdomUserQuery.setJoinBegin(DateUtil.getStartOfToday());
    	}
    	if(extKingdomUserQuery.getJoinEnd() == null){
    		extKingdomUserQuery.setJoinEnd(DateUtil.getStartOfTomorrow());
    	}
        List<Long> uids = getUidByTopicIdAndTime(extKingdomUserQuery.getTopicId(),extKingdomUserQuery.getJoinBegin(),extKingdomUserQuery.getJoinEnd(),extKingdomUserQuery.getFragment());
        if(uids.size()>0){
        	extKingdomReviews = extKingdomMapper.getKingdomUserByCondition(extKingdomUserQuery,uids);
        }
		return extKingdomReviews;
	}
}
