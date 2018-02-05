package com.m2m.service;

import com.m2m.domain.EmotionPack;
import com.m2m.domain.EmotionPackDetail;
import com.m2m.domain.EmotionPackDetailExample;
import com.m2m.entity.ExtCmsEmotion;
import com.m2m.entity.ExtCmsEmotionPack;
import com.m2m.entity.ExtEmotion;
import com.m2m.entity.ExtEmotionPack;
import com.m2m.mapper.EmotionPackDetailMapper;
import com.m2m.mapper.EmotionPackMapper;
import com.m2m.mapper.ExtEmotionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionService extends BaseService {

    @Autowired
    private ExtEmotionMapper extEmotionMapper;

    @Autowired
    private EmotionPackMapper emotionPackMapper;

    @Autowired
    private EmotionPackDetailMapper emotionPackDetailMapper;

    public ExtCmsEmotionPack getAllEmotionPacks(Long pageIndex, Long pageSize) {
        ExtCmsEmotionPack extCmsEmotionPack = new ExtCmsEmotionPack();
        Long total = extEmotionMapper.getAllEmotionPacksCount();
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtEmotionPack> extEmotionPacks = extEmotionMapper.getAllEmotionPacksByPage(start, pageSize);
        extCmsEmotionPack.setTotal(total);
        extCmsEmotionPack.setData(extEmotionPacks);
        return extCmsEmotionPack;
    }

    public ExtCmsEmotion getAllEmotions(Integer packId, Long pageIndex, Long pageSize) {
        ExtCmsEmotion extCmsEmotion = new ExtCmsEmotion();
        Long total = extEmotionMapper.getAllEmotionsCountByPackId(packId);
        Long start = getPageStart(total, pageSize, pageIndex);
        List<ExtEmotion> extEmotions = extEmotionMapper.getAllEmotionsByPackIdAndPage(packId, start, pageSize);
        extCmsEmotion.setTotal(total);
        extCmsEmotion.setData(extEmotions);
        return extCmsEmotion;
    }

    public void addEmotionPack(EmotionPack emotionPack) {
        emotionPackMapper.insertSelective(emotionPack);
    }

    public void addEmotion(EmotionPackDetail emotionPackDetail) {
        emotionPackDetailMapper.insertSelective(emotionPackDetail);
    }

    public void deleteEmotionPack(Integer id) {
        emotionPackMapper.deleteByPrimaryKey(id);
        deleteEmotionByPckaId(id);
    }

    private void deleteEmotionByPckaId(Integer id) {
        EmotionPackDetailExample example = new EmotionPackDetailExample();
        EmotionPackDetailExample.Criteria criteria = example.createCriteria();
        criteria.andPackIdEqualTo(id);
        emotionPackDetailMapper.deleteByExample(example);
    }

    public void deleteEmotion(Integer id) {
        emotionPackDetailMapper.deleteByPrimaryKey(id);
    }

    public void updateEmotionPack(EmotionPack emotionPack) {
        emotionPackMapper.updateByPrimaryKeySelective(emotionPack);
    }

    public void updateEmotion(EmotionPackDetail emotionPackDetail) {
        emotionPackDetailMapper.updateByPrimaryKeySelective(emotionPackDetail);
    }
}
