package com.m2m.mapper;

import com.m2m.entity.ExtEmotion;
import com.m2m.entity.ExtEmotionPack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtEmotionMapper {

    Long getAllEmotionPacksCount();

    List<ExtEmotionPack> getAllEmotionPacksByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    Long getAllEmotionsCountByPackId(Integer packId);

    List<ExtEmotion> getAllEmotionsByPackIdAndPage(@Param("packId") Integer packId, @Param("start") Long start, @Param("pageSize") Long pageSize);

}
