package com.m2m.mapper;

import com.m2m.domain.BillboardDetails;
import com.m2m.domain.BillboardRelation;
import com.m2m.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtListRankMapper {

    List<ExtBillBoardMode> getAllListRankModes();

    Long getAllListRankModeCount();

    List<ExtOnlineListRank> getAllOnlineListRanksByType(Integer type);

    void sortOnlineListRankByList(List<BillboardDetails> billboardDetailsList);

    List<ExtListRank> getAllListRanks(@Param("pageIndex") Long pageIndex, @Param("pageSize") Long pageSize, @Param("type") Integer type);

    List<ExtRankKingdom> getExtRankKingdomsByBillBoardId(Long billBoardId);

    List<ExtRankKingdom> getHotInteractionTopics();

    List<ExtRankKingdom> getTopicByConfig(String listKey);

    List<ExtRankKingdom> getTopicByUpdateTime();

    List<ExtRankKingdom> getTopicByPrice();

    List<ExtRankKingdom> getFastIncrPriceTopic();

    List<Long> getTagIdByTagName(String tagName);

    List<ExtRankKingdom> getExtRankKingdomsMostRiceByTagIds(List<Long> tagIds);

    List<ExtRankKingdom> getExtRankKingdomsFastIncrByTagIds(List<Long> tagIds);

    List<ExtRankKingdom> getOutReadKingdoms();

    List<ExtRankKingdom> getLotteryKingdoms();

    List<ExtRankKingdom> getNewExcellentKingdoms();

    List<ExtRankUser> getExtRankUsersByBillBoardId(Long billBoardId);

    List<ExtRankUser> getActiveUser();

    List<ExtRankUser> getUserByConfig(String string);

    List<ExtRankUser> getUserByGender(int gender);

    List<ExtRankUser> getNewRegisterUsers();

    List<ExtRankUser> getHotRiceUsers();

    List<ExtRankUser> getUserByCoins();

    List<ExtRankUser> getUserByShareCount();

    String getTagNameByMode(Integer mode);

    List<ExtRankSet> getRankSet(Long billBoardId);

    List<Long> getTargetId(@Param("sourceId") Long sourceId, @Param("type") Integer type);

    void insertBillboardRelation(List<BillboardRelation> billboardRelations);

    void sortBillboardRelation(List<BillboardRelation> billboardRelations);

    List<Long> getRightTopicId(List<Long> targetIds);

    List<Long> getRightUid(List<Long> targetIds);

    List<Long> getRightBillBoardId(List<Long> targetIds);

    Long getAppUsersCount();

    List<ExtListRankAppUser> getAppUsersByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getAppUserCountByCondition(ExtListRankAppUserQuery extListRankAppUserQuery);

    List<ExtListRankAppUser> getAppUsersByCondition(ExtListRankAppUserQuery extListRankAppUserQuery);

}
