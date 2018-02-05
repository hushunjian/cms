package com.m2m.service;

import com.m2m.domain.TopicTag;
import com.m2m.domain.TopicTagDetail;
import com.m2m.domain.TopicTagDetailExample;
import com.m2m.domain.TopicTagExample;
import com.m2m.entity.*;
import com.m2m.mapper.ExtTagMapper;
import com.m2m.mapper.TopicTagDetailMapper;
import com.m2m.mapper.TopicTagMapper;
import com.m2m.request.TestExcelRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService extends BaseService {

    @Autowired
    private ExtTagMapper extTagMapper;

    @Autowired
    private TopicTagMapper topicTagMapper;

    @Autowired
    private TopicTagDetailMapper topicTagDetailMapper;

    @Autowired
    private LocalConfigService localConfigService;

    public List<ExtTagKing> setTagKingdomLinkByList(List<ExtTagKing> extTagKings) {
        for (ExtTagKing extTagKing : extTagKings) {
            extTagKing.setLink(String.format("%s%s%s", localConfigService.getWebappUrl(), "/", extTagKing.getTopicId()));
        }
        return extTagKings;
    }

    public ExtCmsTag getAllTags(Long pageIndex, Long pageSize) {
        ExtCmsTag extCmsTag = new ExtCmsTag();
        Long total = extTagMapper.getAllTagsTotal();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtTagTemp> extTagTemps = extTagMapper.getAllTagsByPage(start, pageSize);
        extCmsTag.setTotal(total);
        extCmsTag.setData(extTagTemps);
        return extCmsTag;
    }

    public List<ExtParentTag> getAllParentTags() {
        List<ExtParentTag> extParentTags = extTagMapper.getAllParentTags();
        return extParentTags;
    }

    public ExtCmsAllTagKingdom getAllTagKingdom(Integer tagId, Long pageIndex, Long pageSize) {
        ExtCmsAllTagKingdom extCmsAllTagKingdom = new ExtCmsAllTagKingdom();
        Long total = extTagMapper.getTagKingdomCountByTagId(tagId);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtTagKing> extTagKings = extTagMapper.getAllTagKingdomByTagIdAndPage(tagId, start, pageSize);
        extTagKings = setTagKingdomLinkByList(extTagKings);
        extCmsAllTagKingdom.setTotal(total);
        extCmsAllTagKingdom.setData(extTagKings);
        return extCmsAllTagKingdom;
    }

    public List<ExtUserHobby> getAllUserHobbies() {
        List<ExtUserHobby> extUserHobbys = extTagMapper.getAllUserHobbies();
        return extUserHobbys;
    }

    public List<TopicTag> checkTagExist(TopicTag topicTag) {
        TopicTagExample example = new TopicTagExample();
        TopicTagExample.Criteria criteria = example.createCriteria();
        criteria.andTagEqualTo(topicTag.getTag());
        List<TopicTag> topicTags = topicTagMapper.selectByExample(example);
        return topicTags;
    }

    public void addTag(TopicTag topicTag) {
        topicTagMapper.insertSelective(topicTag);
    }

    public void deleteTag(Long tagId) {
        topicTagMapper.deleteByPrimaryKey(tagId);
        TopicTagDetailExample example = new TopicTagDetailExample();
        TopicTagDetailExample.Criteria criteria = example.createCriteria();
        criteria.andTagIdEqualTo(tagId);
        topicTagDetailMapper.deleteByExample(example);
    }

    public ExtCmsTag getTagSearch(ExtTagSearchQuery extTagSearchQuery) {
        ExtCmsTag extCmsTag = new ExtCmsTag();
        Long total = extTagMapper.getTagSearchCountByCondition(extTagSearchQuery);
        List<ExtTagTemp> extTagTemps = extTagMapper.getTagSearchListByCondition(extTagSearchQuery);
        extCmsTag.setData(extTagTemps);
        extCmsTag.setTotal(total);
        return extCmsTag;
    }

    public void removeTagTopic(Long tagId, Long topicId) {
        TopicTagDetail topicTagDetail = new TopicTagDetail();
        topicTagDetail.setStatus(1);
        TopicTagDetailExample example = new TopicTagDetailExample();
        TopicTagDetailExample.Criteria criteria = example.createCriteria();
        criteria.andTagIdEqualTo(tagId);
        criteria.andTopicIdEqualTo(topicId);
        topicTagDetailMapper.updateByExampleSelective(topicTagDetail, example);
    }

    public void updateTag(TopicTag topicTag) {
        topicTagMapper.updateByPrimaryKeySelective(topicTag);
    }

    public void addTagTopic(Long[] topicIds, Long tagId) {
        TopicTagDetailExample example = new TopicTagDetailExample();
        TopicTagDetailExample.Criteria criteria = example.createCriteria();
        criteria.andTagIdEqualTo(tagId);
        criteria.andStatusEqualTo(0);
        List<TopicTagDetail> topicTagDetails = topicTagDetailMapper.selectByExample(example);
        List<Long> topicIdListOld = new ArrayList<Long>();
        for (TopicTagDetail topicTagDetail : topicTagDetails) {
            topicIdListOld.add(topicTagDetail.getTopicId());
        }
        List<Long> topicIdListNew = new ArrayList<Long>();
        for (Long topicId : topicIds) {
            topicIdListNew.add(topicId);
        }
        topicIdListNew.removeAll(topicIdListOld);
        if (topicIdListNew.size() > 0) {
            List<TopicTagDetail> TopicTagDetailList = extTagMapper.buildTopicTagDetailByTopicIdListAndTagId(topicIdListNew, tagId);
            extTagMapper.addTopicTagDetailBatch(TopicTagDetailList);
        }
    }

	public List<ExtTagTemp> testExcel(TestExcelRequest request) {
		return extTagMapper.testExcel(request);
	}
}
