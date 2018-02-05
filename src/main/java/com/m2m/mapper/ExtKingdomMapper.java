package com.m2m.mapper;

import com.m2m.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExtKingdomMapper {
    List<ExtKingdom> getAllKingdomsInfoByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getKingdomCountByCondition(ExtKingdomQuery asExtKingdomInfoQuery);

    List<ExtKingdom> getKingdomInfoByCondition(ExtKingdomQuery asExtKingdomInfoQuery);

    void truncateKingdomCountDay();

    void statKingdomCountDay();

    Long getKingdomValueCount();

    List<ExtKingdomValue> getKingdomValuesByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getKingdomValueCountByCondition(String topicName);

    List<ExtKingdomValue> getKingdomValuesByConditionAndByPage(@Param("start") Long start, @Param("pageSize") Long pageSize,
                                                               @Param("topicName") String topicName, @Param("orderParam") String orderParam,
                                                               @Param("order") String order);

    Long getKingdomUserCountByTopicId(@Param("topicId")Long topicId, @Param("uids")List<Long> uids, @Param("createTimeBegin")Date createTimeBegin, @Param("createTimeEnd")Date createTimeEnd);

    List<ExtKingdomReview> getKingdomUserByTopicAndPage(@Param("start") Long start, @Param("pageSize") Long pageSize, @Param("topicId") Long topicId, @Param("uids")List<Long> uids, @Param("createTimeBegin")Date createTimeBegin, @Param("createTimeEnd")Date createTimeEnd);

    Long getKingdomUserCountByCondition(@Param("extKingdomUserQuery")ExtKingdomUserQuery extKingdomUserQuery, @Param("uids")List<Long> uids);

    List<ExtKingdomReview> getKingdomUserByCondition(@Param("extKingdomUserQuery")ExtKingdomUserQuery extKingdomUserQuery, @Param("uids")List<Long> uids);

    Long getListedKingdomCount();

    List<ExtListedKingdom> getListedKingdomsByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getListedKingdomCountByCondition(@Param("topicName") String topicName, @Param("status") Integer status);

    List<ExtListedKingdom> getListedKingdomsByConditionAndPage(@Param("topicName") String topicName, @Param("status") Integer status,
                                                               @Param("start") Long start, @Param("pageSize") Long pageSize);

    void insertTopicChangeHis(@Param("topicId") Long topicId, @Param("optUid") Long optUid, @Param("targetUid") Long targetUid, @Param("sourceUid") Long sourceUid);

    void setListedKingdomStatus(@Param("id") Long id, @Param("status") Integer status);

    void updateContentUid(@Param("uid") Long uid, @Param("topicId") Long topicId);

    void insertTransferRecord(@Param("newUid") Long newUid, @Param("oldUid") Long oldUid, @Param("price") Integer price,
                              @Param("priceRMB") Double priceRmb, @Param("topicId") Long topicId);

    void deleteBlockTopicByTopicId(Long topicId);

    Long getPendingListedKingdomsCount();

    List<ExtListedKingdom> getPendingListedKingdomsByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getPendingListedKingdomCountByCondition(String topicName);

    List<ExtListedKingdom> getPendingListedKingdomByPageAndCondition(@Param("topicName") String topicName, @Param("start") Long start, @Param("pageSize") Long pageSize);

	List<Long> getUidsByTopicIdAndTime(@Param("topicId")Long topicId, @Param("createTimeBegin")Date createTimeBegin, @Param("createTimeEnd")Date createTimeEnd, @Param("fragment")String fragment);

	List<ExtUserRegisterChannel> getUsersRegisterChannel();

}
