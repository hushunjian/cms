package com.m2m.mapper;

import com.m2m.domain.TopicTagDetail;
import com.m2m.entity.*;
import com.m2m.request.TestExcelRequest;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTagMapper {

    Long getAllTagsTotal();

    List<ExtTagTemp> getAllTagsByPage(@Param("start") Long start, @Param("pageSize") Long pageSize);

    List<ExtParentTag> getAllParentTags();

    Long getTagKingdomCountByTagId(Integer tagId);

    List<ExtTagKing> getAllTagKingdomByTagIdAndPage(@Param("tagId") Integer tagId, @Param("start") Long start, @Param("pageSize") Long pageSize);

    List<ExtUserHobby> getAllUserHobbies();

    Long getTagSearchCountByCondition(ExtTagSearchQuery extTagSearchQuery);

    List<ExtTagTemp> getTagSearchListByCondition(ExtTagSearchQuery extTagSearchQuery);

    List<TopicTagDetail> buildTopicTagDetailByTopicIdListAndTagId(@Param("topicIdList") List<Long> topicIdListNew, @Param("tagId") Long tagId);

    void addTopicTagDetailBatch(List<TopicTagDetail> topicTagDetailList);

	List<ExtTagTemp> testExcel(TestExcelRequest request);

}
