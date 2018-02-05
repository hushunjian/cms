package com.m2m.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.m2m.domain.UserProfile;
import com.m2m.entity.ExtAnchor;
import com.m2m.entity.ExtAnchorInvite;
import com.m2m.entity.ExtAnchorInviteDetail;
import com.m2m.entity.ExtAnchorInviteDetailQuery;
import com.m2m.entity.ExtAnchorQuery;
import com.m2m.entity.ExtGetAnchorInviteQuery;
import com.m2m.entity.ExtUserRegisterChannel;

public interface ExtAppAnchorMapper {

	Long getAppAnchorCount();

	List<ExtAnchor> getAppAnchorByPage(@Param("start")Long start, @Param("pageSize")Long pageSize);

	List<ExtAnchorInvite> getAnchorInviteByPage(@Param("start")Long start, @Param("pageSize")Long pageSize, @Param("uids")List<Long> uids, @Param("timeBegin")Date timeBegin, @Param("timeEnd")Date timeEnd);

	List<Long> getValidUids();

	Long getAppAnchorCountByCondition(@Param("extGetAnchorInviteQuery")ExtGetAnchorInviteQuery extGetAnchorInviteQuery, @Param("uids")List<Long> uids);

	List<ExtAnchorInvite> getAnchorInviteByCondition(@Param("extGetAnchorInviteQuery")ExtGetAnchorInviteQuery extGetAnchorInviteQuery, @Param("uids")List<Long> uids);

	UserProfile isValidUid(long uid);

	Long getAnchorInviteDetailCount(@Param("timeBegin")Date timeBegin, @Param("timeEnd")Date timeEnd, @Param("uids")List<Long> uids);

	List<ExtAnchorInviteDetail> getAnchorInviteDetailByPage(@Param("timeBegin")Date timeBegin, @Param("timeEnd")Date timeEnd, @Param("start")Long start,@Param("pageSize")Long pageSize, @Param("uids")List<Long> uids);

	Long getAnchorInviteDetailCountByCondition(@Param("extAnchorInviteDetailQuery")ExtAnchorInviteDetailQuery extAnchorInviteDetailQuery, @Param("uids")List<Long> uids);

	List<ExtAnchorInviteDetail> getAnchorInviteDetailByCondition(@Param("extAnchorInviteDetailQuery")ExtAnchorInviteDetailQuery extAnchorInviteDetailQuery, @Param("uids")List<Long> uids);

	Long getAnchorsCountByCondition(ExtAnchorQuery extAnchorQuery);

	List<ExtAnchor> getAnchorsByCondition(ExtAnchorQuery extAnchorQuery);

	Long getAnchorInviteCount(@Param("uids")List<Long> uids, @Param("timeBegin")Date timeBegin,@Param("timeEnd")Date timeEnd);

	List<ExtUserRegisterChannel> getUsersRegisterChannel();

}
