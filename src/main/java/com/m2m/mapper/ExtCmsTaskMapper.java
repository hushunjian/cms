package com.m2m.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface ExtCmsTaskMapper {

    void insertTmpUserDailyStatistics(@Param("yesterdayStart") Date yesterdayStart, @Param("todayStart") Date todayStart);

    void insertUserDailyStatisticsCount(@Param("yesterdayStart") Date yesterdayStart, @Param("todayStart") Date todayStart);
}

