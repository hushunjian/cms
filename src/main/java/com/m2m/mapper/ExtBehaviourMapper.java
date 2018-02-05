package com.m2m.mapper;

import com.m2m.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtBehaviourMapper {

    Long getRegisteredUserCount();

    List<ExtRegisteredUser> getRegisteredUserByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getRegisteredUserCountByCondition(ExtRegisteredUserQuery extRegisteredUserQuery);

    List<ExtRegisteredUser> getRegisteredUserByCondition(ExtRegisteredUserQuery extRegisteredUserQuery);

    Long getUserBehaviorsCount();

    List<ExtUserBehaviour> getUserBehaviorsByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getUserBehaviorsCountByCondition(ExtCmsUserBehaviourQuery extCmsUserBehaviourQuery);

    List<ExtUserBehaviour> getUserBehaviorsByCondition(ExtCmsUserBehaviourQuery extCmsUserBehaviourQuery);

    ExtCmsUserBehaviourTotal getUserBehaviorsTotal();

    ExtCmsUserBehaviourTotal getUserBehaviorsTotalByCondition(ExtCmsUserBehaviourTotalQuery extCmsUserBehaviourTotalQuery);

    List<ExtAppPage> getAppAllPages();
    
    List<ExtUserRegisterChannel> getUsersRegisterChannel();

}