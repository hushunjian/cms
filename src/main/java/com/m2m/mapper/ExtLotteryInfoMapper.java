package com.m2m.mapper;

import com.m2m.entity.ExtInvitationNum;
import com.m2m.entity.ExtLottery;
import com.m2m.entity.ExtLotteryUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtLotteryInfoMapper {

    List<ExtLottery> getAllLotteriesByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getAllLotteriesCount();

    Long getLotteriesCountByCondition(@Param("topicName") String topicName, @Param("lotteryName") String lotteryName);

    List<ExtLottery> getLotteriesByCondition(@Param("start") Long start, @Param("pageSize") Long pageSize, @Param("topicName") String topicName, @Param("lotteryName") String lotteryName);

    Long getLotteryUsersCountByLotteryId(Integer lotteryId);

    List<ExtLotteryUser> getLotteryUsersInfoByLotteryIdAndPage(@Param("start") Long start, @Param("pageSize") Long pageSize, @Param("lotteryId") Integer lotteryId);

    Long getLotteryUsersSearchCountByCondition(@Param("lotteryId") Integer lotteryId, @Param("userNick") String userNick, @Param("isAppoint") Integer isAppoint);

    List<ExtLotteryUser> getLotteryUsersSearchInfoByCondition(@Param("start") Long start, @Param("pageSize") Long pageSize, @Param("lotteryId") Integer lotteryId, @Param("userNick") String userNick, @Param("isAppoint") Integer isAppoint);

    List<ExtInvitationNum> getInvitationNumByUids(List<Long> uids);

}
