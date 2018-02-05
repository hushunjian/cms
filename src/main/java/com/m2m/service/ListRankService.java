package com.m2m.service;

import com.m2m.Constant;
import com.m2m.domain.*;
import com.m2m.entity.*;
import com.m2m.exception.SystemException;
import com.m2m.exception.TargetInformationNotExist;
import com.m2m.exception.TopicTagNotFoundException;
import com.m2m.mapper.BillBoardMapper;
import com.m2m.mapper.BillboardDetailsMapper;
import com.m2m.mapper.BillboardRelationMapper;
import com.m2m.mapper.ExtListRankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListRankService extends BaseService {

    @Autowired
    private BillBoardMapper billBoardMapper;

    @Autowired
    private ExtListRankMapper extListRankMapper;

    @Autowired
    private BillboardDetailsMapper billboardDetailsMapper;

    @Autowired
    private BillboardRelationMapper billboardRelationMapper;

    @Autowired
    private LocalConfigService localConfigService;

    @Autowired
    private RedisService redisService;

    public List<ExtListRank> getAllListRanks(Long pageIndex, Long pageSize, Integer type) {
        return extListRankMapper.getAllListRanks(pageIndex, pageSize, type);
    }

    public Long getAllListRankCount() {
        BillBoardExample example = new BillBoardExample();
        return billBoardMapper.countByExample(example);
    }

    public List<ExtBillBoardMode> getAllListRankModes() {
        return extListRankMapper.getAllListRankModes();
    }

    public Long getAllListRankModeCount() {
        return extListRankMapper.getAllListRankModeCount();
    }

    public void addListRank(BillBoard billBoard) {
        billBoardMapper.insertSelective(billBoard);
    }

    public void deleteListRank(Long id) {
        billBoardMapper.deleteByPrimaryKey(id);
        BillboardRelationExample example = new BillboardRelationExample();
        BillboardRelationExample.Criteria criteria = example.createCriteria();
        criteria.andTargetIdEqualTo(id);
        billboardRelationMapper.deleteByExample(example);
    }

    public void updateListRank(BillBoard billBoard) {
        billBoardMapper.updateByPrimaryKeySelective(billBoard);
    }

    public ExtCmsRankKingdom getAllListRankKingdoms(Long billBoardId) throws SystemException {
        ExtCmsRankKingdom extCmsRankKingdom = new ExtCmsRankKingdom();
        BillBoard billBoard = billBoardMapper.selectByPrimaryKey(billBoardId);
        extCmsRankKingdom.setListType(billBoard.getType());
        extCmsRankKingdom.setMode(billBoard.getMode());
        List<ExtRankKingdom> extRankKingdoms = getKingdomRank(billBoard.getMode(), billBoard.getId());
        extCmsRankKingdom.setData(extRankKingdoms);
        return extCmsRankKingdom;
    }

    public ExtCmsRankUser getAllListRankUsers(Long billBoardId) {
        ExtCmsRankUser extCmsRankUser = new ExtCmsRankUser();
        BillBoard billBoard = billBoardMapper.selectByPrimaryKey(billBoardId);
        extCmsRankUser.setListType(billBoard.getType());
        extCmsRankUser.setMode(billBoard.getMode());
        List<ExtRankUser> extRankUsers = getUserRank(billBoard.getMode(), billBoard.getId());
        extCmsRankUser.setData(extRankUsers);
        return extCmsRankUser;
    }

    public ExtCmsRankSet getAllListRankSets(Long billBoardId) {
        ExtCmsRankSet extCmsRankSet = new ExtCmsRankSet();
        BillBoard billBoard = billBoardMapper.selectByPrimaryKey(billBoardId);
        extCmsRankSet.setListType(billBoard.getType());
        extCmsRankSet.setMode(billBoard.getMode());
        List<ExtRankSet> extRankSets = extListRankMapper.getRankSet(billBoardId);
        extCmsRankSet.setData(extRankSets);
        return extCmsRankSet;
    }

    public List<ExtRankUser> getUserRank(Integer mode, Long billBoardId) {
        List<ExtRankUser> extRankUsers = new ArrayList<ExtRankUser>();
        if (mode > 0) {
            extRankUsers = getAutoUserRank(mode);
        } else {
            extRankUsers = getManualUserRank(billBoardId);
        }
        return extRankUsers;
    }

    public List<ExtRankKingdom> getKingdomRank(Integer mode, Long billBoardId) throws SystemException {
        List<ExtRankKingdom> extRankKingdoms = new ArrayList<ExtRankKingdom>();
        if (mode > 0) {
            extRankKingdoms = getAutoKingdomRank(mode);
        } else {
            extRankKingdoms = getManualKingdomRank(billBoardId);
        }
        return extRankKingdoms;
    }


    public List<BillboardRelation> getBillboardRelations(Long billBoardId) {
        BillboardRelationExample example = new BillboardRelationExample();
        BillboardRelationExample.Criteria criteria = example.createCriteria();
        criteria.andSourceIdEqualTo(billBoardId);
        return billboardRelationMapper.selectByExample(example);
    }

    public List<ExtRankKingdom> setExtRankKingdomLink(List<ExtRankKingdom> extRankKingdoms) {
        if (extRankKingdoms.size() > 0) {
            for (int i = 0; i < extRankKingdoms.size(); i++) {
                extRankKingdoms.get(i).setLink(String.format("%s%s%s", localConfigService.getWebappUrl(), "/", extRankKingdoms.get(i).getId()));
            }
        }
        return extRankKingdoms;
    }

    public List<ExtRankKingdom> getManualKingdomRank(Long billBoardId) {
        List<ExtRankKingdom> extRankKingdoms = new ArrayList<ExtRankKingdom>();
        List<BillboardRelation> billboardRelations = getBillboardRelations(billBoardId);
        if (billboardRelations.size() > 0) {
            extRankKingdoms = extListRankMapper.getExtRankKingdomsByBillBoardId(billBoardId);
            extRankKingdoms = setExtRankKingdomLink(extRankKingdoms);
        }
        return extRankKingdoms;
    }

    private List<ExtRankUser> getManualUserRank(Long billBoardId) {
        List<ExtRankUser> extRankUsers = new ArrayList<ExtRankUser>();
        List<BillboardRelation> billboardRelations = getBillboardRelations(billBoardId);
        if (billboardRelations.size() > 0) {
            extRankUsers = extListRankMapper.getExtRankUsersByBillBoardId(billBoardId);
        }
        return extRankUsers;
    }

    private List<ExtRankUser> getAutoUserRank(Integer mode) {
        List<ExtRankUser> extRankUsers = new ArrayList<ExtRankUser>();
        String currentCacheKey = null;
        switch (mode) {
            case Constant.MOSTACTIVE_USER:
                extRankUsers = extListRankMapper.getActiveUser();
                break;
            case Constant.MOSTPOPULAR_USER:
                currentCacheKey = redisService.get(Constant.BILLBOARD_KEY_POPULAR_PEOPLE);
                if (StringUtils.isEmpty(currentCacheKey)) {
                    currentCacheKey = Constant.BILLBOARD_KEY_TARGET1;
                }
                extRankUsers = extListRankMapper.getUserByConfig(Constant.BILLBOARD_KEY_POPULAR_PEOPLE + currentCacheKey);
                break;
            case Constant.TALKATIVE_KINGDOM:
                currentCacheKey = redisService.get(Constant.BILLBOARD_KEY_JAY_PEOPLE);
                if (StringUtils.isEmpty(currentCacheKey)) {
                    currentCacheKey = Constant.BILLBOARD_KEY_TARGET1;
                }
                extRankUsers = extListRankMapper.getUserByConfig(Constant.BILLBOARD_KEY_JAY_PEOPLE + currentCacheKey);
                break;
            case Constant.NEW_REGISTERED_GUY:
                extRankUsers = extListRankMapper.getUserByGender(1);
                break;
            case Constant.NEW_REGISTERED_GIRL:
                extRankUsers = extListRankMapper.getUserByGender(0);
                break;
            case Constant.NEW_REGISTERED_USER:
                extRankUsers = extListRankMapper.getNewRegisterUsers();
                break;
            case Constant.HOT_RICE_METOME_USER:
                extRankUsers = extListRankMapper.getHotRiceUsers();
                break;
            case Constant.PERSONAL_RICE_LIST:
                extRankUsers = extListRankMapper.getUserByCoins();
                break;
            case Constant.NUMBER_OF_FOREIGN_SHARES_USER_LIST:
                extRankUsers = extListRankMapper.getUserByShareCount();
                break;
        }
        return extRankUsers;
    }

    public List<ExtRankKingdom> getAutoKingdomRank(Integer mode) throws SystemException {
        List<ExtRankKingdom> extRankKingdoms = new ArrayList<ExtRankKingdom>();
        String currentCacheKey = null;
        String tagName = extListRankMapper.getTagNameByMode(mode);
        switch (mode) {
            case Constant.HOT_INTERACION:
                extRankKingdoms = extListRankMapper.getHotInteractionTopics();
                break;
            case Constant.COLORFUL_TOPIC:
                currentCacheKey = redisService.get(Constant.BILLBOARD_KEY_COLOURFUL_KINGDOM);
                if (StringUtils.isEmpty(currentCacheKey)) {
                    currentCacheKey = Constant.BILLBOARD_KEY_TARGET1;
                }
                extRankKingdoms = extListRankMapper.getTopicByConfig(Constant.BILLBOARD_KEY_COLOURFUL_KINGDOM + currentCacheKey);
                break;
            case Constant.ALONE_TOPIC:
                currentCacheKey = redisService.get(Constant.BILLBOARD_KEY_LONELY_KINGDOM);
                if (StringUtils.isEmpty(currentCacheKey)) {
                    currentCacheKey = Constant.BILLBOARD_KEY_TARGET1;
                }
                extRankKingdoms = extListRankMapper.getTopicByConfig(Constant.BILLBOARD_KEY_LONELY_KINGDOM + currentCacheKey);
                break;
            case Constant.LATEST_UPDATE_KINGDOM:
                extRankKingdoms = extListRankMapper.getTopicByUpdateTime();
                break;
            case Constant.MOST_VALUABLE_KINGDOM:
                extRankKingdoms = extListRankMapper.getTopicByPrice();
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM:
                extRankKingdoms = extListRankMapper.getFastIncrPriceTopic();
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_EXERCISE_SEX:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_EXERCISE_SEX:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_NOT_TALKATIVE:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_TALKATIVE:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_VIDEOANDLIGHT:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_VIDEOANDLIGHT:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINDOM_OF_BUILDING_MORETHAN_HOUSE:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_BUILDING_MORETHAN_HOUSE:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_GAME_WORLD:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_GAME_WORLD:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_PLAY_LOST_NOTHING:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_PLAY_LOST_NOTHING:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_POOPOFFICER:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_POOPOFFICER:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_TRAVEL_IS_MYATTITUDE:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_TRAVEL_IS_MYATTITUDE:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.MOST_VALUABLE_KINGDOM_OF_MIDNIGHT_DINER:
                extRankKingdoms = getExtRankKingdomMostRiceByTag(tagName);
                break;
            case Constant.FAST_INCR_VALUABLE_KINGDOM_OF_MIDINGHT_DINER:
                extRankKingdoms = getExtRankingdomFastIncrByTag(tagName);
                break;
            case Constant.NUMBER_OF_FOREIGN_READING_TOPIC_LIST:
                extRankKingdoms = extListRankMapper.getOutReadKingdoms();
                break;
            case Constant.ATPRESENT_LOTTERY_TOPIC:
                extRankKingdoms = extListRankMapper.getLotteryKingdoms();
                break;
            case Constant.EXCELLENT_NEW_TOPIC:
                extRankKingdoms = extListRankMapper.getNewExcellentKingdoms();
                break;
        }
        extRankKingdoms = setExtRankKingdomLink(extRankKingdoms);
        return extRankKingdoms;
    }

    public List<ExtRankKingdom> getExtRankKingdomMostRiceByTag(String tagName) throws SystemException {
        List<ExtRankKingdom> extRankKingdoms = new ArrayList<ExtRankKingdom>();
        List<Long> tagIds = extListRankMapper.getTagIdByTagName(tagName);
        if (tagIds.size() > 0) {
            extRankKingdoms = extListRankMapper.getExtRankKingdomsMostRiceByTagIds(tagIds);
            return extRankKingdoms;
        } else {
            throw new TopicTagNotFoundException();
        }
    }

    public List<ExtRankKingdom> getExtRankingdomFastIncrByTag(String tagName) throws SystemException {
        List<ExtRankKingdom> extRankKingdoms = new ArrayList<ExtRankKingdom>();
        List<Long> tagIds = extListRankMapper.getTagIdByTagName(tagName);
        if (tagIds.size() > 0) {
            extRankKingdoms = extListRankMapper.getExtRankKingdomsFastIncrByTagIds(tagIds);
            return extRankKingdoms;
        } else {
            throw new TopicTagNotFoundException();
        }
    }

    public List<ExtOnlineListRank> getAllOnlineListRanksByType(Integer type) {
        return extListRankMapper.getAllOnlineListRanksByType(type);
    }

    public void deleteOnlineListRankByIdAndType(Long id) {
        billboardDetailsMapper.deleteByPrimaryKey(id);
    }

    public void addOnlineListRank(BillboardDetails billboardDetail) {
        BillboardDetailsExample example = new BillboardDetailsExample();
        BillboardDetailsExample.Criteria criteria = example.createCriteria();
        criteria.andBidEqualTo(billboardDetail.getBid());
        criteria.andTypeEqualTo(billboardDetail.getType());
        List<BillboardDetails> billboardDetails = billboardDetailsMapper.selectByExample(example);
        if (billboardDetails.size() == 0) {
            billboardDetailsMapper.insertSelective(billboardDetail);
        }
    }

    public void setOnlineListRank(BillboardDetails billboardDetail) {
        billboardDetailsMapper.updateByPrimaryKeySelective(billboardDetail);
    }

    public void sortOnlineListRank(List<BillboardDetails> billboardDetailsList) {
        extListRankMapper.sortOnlineListRankByList(billboardDetailsList);
    }

    public void deleteEntry(Long id) {
        billboardRelationMapper.deleteByPrimaryKey(id);
    }

    public void addEntry(List<BillboardRelation> billboardRelations, Long sourceId, Integer type) throws SystemException {
        List<Long> newTargetIds = new ArrayList<Long>();
        for (BillboardRelation billboardRelation : billboardRelations) {
            newTargetIds.add(billboardRelation.getTargetId());
        }
        List<Long> rightTargetIds = getRightTargetId(newTargetIds, type);
        if (rightTargetIds.size() > 0) {
            List<BillboardRelation> rightBillboardRelations = new ArrayList<BillboardRelation>();
            for (Long targetId : rightTargetIds) {
                for (BillboardRelation billboardRelation : billboardRelations) {
                    if (billboardRelation.getTargetId().equals(targetId)) {
                        rightBillboardRelations.add(billboardRelation);
                    }
                }
            }
            List<Long> existTargetIds = extListRankMapper.getTargetId(sourceId, type);
            List<BillboardRelation> removeBillboardRelation = new ArrayList<BillboardRelation>();
            for (BillboardRelation billboardRelation : rightBillboardRelations) {
                billboardRelation.setSourceId(sourceId);
                billboardRelation.setType(type);
                for (Long targetId : existTargetIds) {
                    if (billboardRelation.getTargetId().equals(targetId)) {
                        removeBillboardRelation.add(billboardRelation);
                    }
                }
            }
            rightBillboardRelations.removeAll(removeBillboardRelation);
            if (rightBillboardRelations.size() > 0) {
                extListRankMapper.insertBillboardRelation(rightBillboardRelations);
            }
        } else {
            throw new TargetInformationNotExist();
        }
    }

    private List<Long> getRightTargetId(List<Long> targetIds, Integer type) {
        List<Long> rightTargetIds = new ArrayList<Long>();
        if (type == 1) {//判断王国是否存在
            rightTargetIds = extListRankMapper.getRightTopicId(targetIds);
        } else if (type == 2) {//判断用户是否存在
            rightTargetIds = extListRankMapper.getRightUid(targetIds);
        } else if (type == 3) {//判断榜单是否存在
            rightTargetIds = extListRankMapper.getRightBillBoardId(targetIds);
        }
        return rightTargetIds;
    }

    public void sortEntry(List<BillboardRelation> billboardRelations) {
        extListRankMapper.sortBillboardRelation(billboardRelations);
    }

    public ExtCmsListRankAppUser getAppUsers(Long pageIndex, Long pageSize) {
        ExtCmsListRankAppUser extCmsListRankAppUser = new ExtCmsListRankAppUser();
        Long total = extListRankMapper.getAppUsersCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtListRankAppUser> extListRankAppUsers = extListRankMapper.getAppUsersByPage(start, pageSize);
        extCmsListRankAppUser.setTotal(total);
        extCmsListRankAppUser.setData(extListRankAppUsers);
        return extCmsListRankAppUser;
    }

    public ExtCmsListRankAppUser getAppUserSearch(ExtListRankAppUserQuery extListRankAppUserQuery) {
        ExtCmsListRankAppUser extCmsListRankAppUser = new ExtCmsListRankAppUser();
        Long total = extListRankMapper.getAppUserCountByCondition(extListRankAppUserQuery);
        List<ExtListRankAppUser> extListRankAppUsers = extListRankMapper.getAppUsersByCondition(extListRankAppUserQuery);
        extCmsListRankAppUser.setTotal(total);
        extCmsListRankAppUser.setData(extListRankAppUsers);
        return extCmsListRankAppUser;
    }

    public BillBoard getListRankById(Long id) {
        return billBoardMapper.selectByPrimaryKey(id);
    }
}
