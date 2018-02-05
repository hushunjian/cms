package com.m2m.mapper;

import com.m2m.domain.User;
import com.m2m.domain.UserToken;
import com.m2m.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtAppUserMapper {

    Long getAllAppUserCount();

    List<ExtUserProfile> getAllAppUserListByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getAllAppUserCountByCondition(ExtAppUserQuery extAppUserQuery);

    List<ExtUserProfile> getAllAppUserListByCondition(ExtAppUserQuery extAppUserQuery);

    void updateUserLevel(@Param("uid") Long uid, @Param("newLevel") Integer newLevel);

    void setUserSilentStatus(Long uid);

    void deleteUserSilentStatus(Long uid);

    void setUserV(@Param("uid") Long uid, @Param("isV") Integer isV);

    void setUserStatus(@Param("uid") Long uid, @Param("status") Integer status);

    void setUserInviteStatus(@Param("uid") Long uid, @Param("isInvited") Integer isInvited);

    void createUserByList(List<User> userList);

    List<User> checkUserExistByPhoneList(List<String> phoneList);

    void createImConfigByList(List<ExtImUser> extImUserList);

    void createUserProfileByList(List<User> userList);

    void createUserTokeByList(List<UserToken> userTokenList);

    Long getUserKingdomContentCountByUid(Long uid);

    List<ExtUserKingdomContent> getUserKingdomContentByPageAndUid(@Param("uid") Long uid, @Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getUserFragmentContentCountByUid(Long uid);

    List<ExtUserFragmentContent> getUserFragmentContentByUidAndPage(@Param("uid") Long uid, @Param("start") Long start, @Param("pageSize") Long pageSize);

    void deleteTopicFragmentById(Long id);

    void deleteTopicImageByFid(Long id);

    void deleteTopicBarrageByFid(Long id);

    void insertDeleteLog(@Param("type") int type, @Param("oid") Long oid, @Param("uid") int uid);

    void deleteTopicById(Long forwardCid);

    void updateAkingdomByTopicId(Long forwardCid);

    void deleteAkingdomListByTopicId(Long forwardCid);

    void deleteAggregationTopic(Long forwardCid);

    void updateTopicAggregationApply(Long forwardCid);

    void deleteBannerTopic(Long forwardCid);

    void deleteTopicTagByTopicId(Long forwardCid);

    void deleteTopicBillboard(Long forwardCid);

    void deleteTopicListed(Long forwardCid);

	List<ExtUserRegisterChannel> getUsersRegisterChannel();

}
