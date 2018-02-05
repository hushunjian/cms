package com.m2m.task;

import com.m2m.mapper.ExtLatestTagImageMapper;
import com.m2m.mapper.ExtTagExhibitionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Component
public class TopicExhibitionTask {
	private static  Logger logger = LoggerFactory.getLogger(TopicExhibitionTask.class);

    @Autowired
    private ExtTagExhibitionMapper extTopicExhibitionMapper;

    @Autowired
    private ExtLatestTagImageMapper extLatestTagImageMapper;

    @Scheduled(cron = "${task.TopicExhibitionTask.execute}")
    public void execute() {
        long startTime = System.currentTimeMillis();
        logger.info("开始准备王国外露信息");
        extLatestTagImageMapper.reset();
        extLatestTagImageMapper.init();
        extLatestTagImageMapper.append();
        extLatestTagImageMapper.append();

        extTopicExhibitionMapper.append();
        Date date = extTopicExhibitionMapper.getMaxCreatedAt();
        extTopicExhibitionMapper.clear(date);
        long endTime = System.currentTimeMillis();
        logger.info(String.format("结束准备王国外露信息:%dms", endTime - startTime));
    }
}
