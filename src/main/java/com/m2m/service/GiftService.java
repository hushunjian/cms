package com.m2m.service;

import com.m2m.domain.GiftInfo;
import com.m2m.domain.GiftInfoExample;
import com.m2m.entity.ExtCmsGift4GetAllGift;
import com.m2m.entity.ExtGift;
import com.m2m.mapper.ExtGiftInfoMapper;
import com.m2m.mapper.GiftInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService extends BaseService {

    @Autowired
    private GiftInfoMapper giftInfoMapper;

    @Autowired
    private ExtGiftInfoMapper extGiftInfoMapper;

    public ExtCmsGift4GetAllGift getAllGifts(Long pageIndex, Long pageSize) {
        ExtCmsGift4GetAllGift extCmsGift4GetAllGift = new ExtCmsGift4GetAllGift();
        GiftInfoExample example = new GiftInfoExample();
        Long total = giftInfoMapper.countByExample(example);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtGift> extGifts = extGiftInfoMapper.getAllGiftsByPage(start, pageSize);
        extCmsGift4GetAllGift.setTotal(total);
        extCmsGift4GetAllGift.setData(extGifts);
        return extCmsGift4GetAllGift;
    }

    public void addGift(GiftInfo giftInfo) {
        giftInfoMapper.insertSelective(giftInfo);
    }

    public void deleteGift(Long giftId) {
        giftInfoMapper.deleteByPrimaryKey(giftId);
    }

    public void updateGift(GiftInfo giftInfo) {
        giftInfoMapper.updateByPrimaryKeySelective(giftInfo);
    }
}
