package com.m2m.service;

import com.m2m.domain.*;
import com.m2m.entity.ExtAdInfo;
import com.m2m.entity.ExtAdTag;
import com.m2m.entity.ExtCmsAdBanner;
import com.m2m.entity.ExtCmsAdInfo;
import com.m2m.exception.SystemException;
import com.m2m.exception.TopicTagNotFoundException;
import com.m2m.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService extends BaseService {
    @Autowired
    private AdBannerMapper adBannerMapper;
    @Autowired
    private AdInfoMapper adInfoMapper;
    @Autowired
    private TopicTagMapper topicTagMapper;
    @Autowired
    private AdTagMapper adTagMapper;
    @Autowired
    private ExtAdTagMapper extAdTagMapper;

    public void addAdBanner(AdBanner adBanner) throws SystemException {
        adBannerMapper.insertSelective(adBanner);
    }

    public void addAdInfo(AdInfo adInfo) throws SystemException {
        adInfoMapper.insertSelective(adInfo);
    }

    public void addAdTag(long id, String name, long positionId, int position) throws SystemException {
        AdTag adTag = new AdTag();
        adTag.setBannerId(positionId);
        adTag.setPosition(position);
        if (id == 0) {
            TopicTagExample topicTagExample = new TopicTagExample();
            TopicTagExample.Criteria criteria = topicTagExample.createCriteria();
            criteria.andTagEqualTo(name);
            List<TopicTag> list = topicTagMapper.selectByExample(topicTagExample);
            if (list.size() == 0) {
                throw new TopicTagNotFoundException();
            } else {
                TopicTag topicTag = list.get(0);
                adTag.setTagId(topicTag.getId());
            }
        } else {
            adTag.setTagId(id);
        }
        AdTagExample topicTagExample = new AdTagExample();
        AdTagExample.Criteria criteria = topicTagExample.createCriteria();
        criteria.andBannerIdEqualTo(positionId);
        criteria.andTagIdEqualTo(adTag.getTagId());
        List<AdTag> adTags = adTagMapper.selectByExample(topicTagExample);
        if (adTags.size() == 0) {
            adTagMapper.insertSelective(adTag);
        }
    }

    public void updateAdBanner(AdBanner adBanner) throws SystemException {
        adBannerMapper.updateByPrimaryKeySelective(adBanner);
    }

    public void delAdInfo(long id) throws SystemException {
        adInfoMapper.deleteByPrimaryKey(id);
    }

    public void delAdBanner(long id) throws SystemException {
        adBannerMapper.deleteByPrimaryKey(id);
    }

    public List<ExtAdTag> getAllLinkTags(long id) throws SystemException {
        return extAdTagMapper.findByBannerId(id);
    }

    public void unlinkLinkTag(long id, long positionId) throws SystemException {
        AdTagExample topicTagExample = new AdTagExample();
        AdTagExample.Criteria criteria = topicTagExample.createCriteria();
        criteria.andTagIdEqualTo(id);
        criteria.andBannerIdEqualTo(positionId);
        adTagMapper.deleteByExample(topicTagExample);
    }

    public Long getAdBannerCount() {
        AdBannerExample example = new AdBannerExample();
        example.setOrderByClause(" id desc");
        Long count = adBannerMapper.countByExample(example);
        return count;
    }

    public List<AdBanner> getAdBannerList(Long start, Long pageSize) {
        AdBannerExample example = new AdBannerExample();
        example.setOrderByClause(" id desc limit " + start + "," + pageSize);
        return adBannerMapper.selectByExample(example);
    }

    public ExtCmsAdBanner searchAdBannerListPage(Long pageIndex, Long pageSize) {
        ExtCmsAdBanner extCmsAdBanner = new ExtCmsAdBanner();
        Long totalRecord = getAdBannerCount();
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<AdBanner> adBanners = getAdBannerList(start, pageSize);
        extCmsAdBanner.setTotal(totalRecord);
        extCmsAdBanner.setData(adBanners);
        return extCmsAdBanner;
    }

    public void updateAdInfo(AdInfo adInfo) throws SystemException {
        adInfoMapper.updateByPrimaryKey(adInfo);
    }

    public List<AdBanner> getAllAdBannerList() {
        AdBannerExample example = new AdBannerExample();
        example.setOrderByClause(" id desc ");
        return adBannerMapper.selectByExample(example);
    }

    public Long getAdInfoCount(long adBannerId) {
        AdInfoExample example = new AdInfoExample();
        AdInfoExample.Criteria criteria = example.createCriteria();
        if (adBannerId != 0) {
            criteria.andBannerIdEqualTo(adBannerId);
        }
        example.setOrderByClause(" effective_time desc");
        Long count = adInfoMapper.countByExample(example);
        return count;
    }

    public ExtCmsAdInfo searchAdInfoListPage(long bannerId, Long pageIndex, Long pageSize) {
        Long totalRecord = getAdInfoCount(bannerId);
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtAdInfo> extAdInfos = extAdTagMapper.getAdInfoList(bannerId, start, pageSize);
        ExtCmsAdInfo extCmsAdInfo = new ExtCmsAdInfo();
        extCmsAdInfo.setTotal(totalRecord);
        extCmsAdInfo.setData(extAdInfos);
        return extCmsAdInfo;
    }

    public ExtCmsAdInfo getAllAdvertisements(Long pageIndex, Long pageSize) {
        Long totalRecord = extAdTagMapper.getAllAdvertisementsCount();
        Long start = getPageStart(totalRecord, pageSize, pageIndex);
        List<ExtAdInfo> extAdInfos = extAdTagMapper.getAllAdvertisements(start, pageSize);
        ExtCmsAdInfo extCmsAdInfo = new ExtCmsAdInfo();
        extCmsAdInfo.setTotal(totalRecord);
        extCmsAdInfo.setData(extAdInfos);
        return extCmsAdInfo;
    }
}
