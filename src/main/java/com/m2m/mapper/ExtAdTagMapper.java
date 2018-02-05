package com.m2m.mapper;

import com.m2m.entity.ExtAdInfo;
import com.m2m.entity.ExtAdTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtAdTagMapper {

    List<ExtAdTag> findByBannerId(Long id);

    List<ExtAdInfo> getAdInfoList(@Param("bannerId") long bannerId, @Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getAllAdvertisementsCount();

    List<ExtAdInfo> getAllAdvertisements(@Param("start") Long start, @Param("pageSize") Long pageSize);

}