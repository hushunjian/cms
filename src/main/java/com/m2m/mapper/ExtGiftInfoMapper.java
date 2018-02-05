package com.m2m.mapper;

import com.m2m.entity.ExtGift;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtGiftInfoMapper {

    List<ExtGift> getAllGiftsByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

}
