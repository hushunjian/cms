package com.m2m.task;

import com.m2m.Constant;
import com.m2m.mapper.ExtRecentTagMapper;
import com.m2m.mapper.ExtTagInHomeMapper;
import com.m2m.mapper.ExtTagTopicCountMapper;
import com.m2m.mapper.ExtTagUserCountMapper;
import com.m2m.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Transactional
@Component
public class TagInHomeTask {
	private static Logger logger = LoggerFactory.getLogger(TagInHomeTask.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private ExtRecentTagMapper extRecentTagMapper;
    @Autowired
    private ExtTagTopicCountMapper extTagTopicCountMapper;
    @Autowired
    private ExtTagUserCountMapper extTagUserCountMapper;
    @Autowired
    private ExtTagInHomeMapper extTagInHomeMapper;

    @Transactional
    @Scheduled(cron = "${task.TagInHomeTask.execute}")
    public void execute() {
        long startTime = System.currentTimeMillis();
        logger.info("开始准备标签");
        extRecentTagMapper.reset();

        int minKingdomCount = 0;
        int minKingdomUpdateDays = 0;
        String key = String.format("%s%s", Constant.APP_CONFIG_KEY_PRE, "NO_RECOMMEND_KINGDOM_COUNT");
        String value = redisService.get(key);
        if (value != null) {
            minKingdomCount = Integer.valueOf(value);
        }
        key = String.format("%s%s", Constant.APP_CONFIG_KEY_PRE, "NO_RECOMMEND_KINGDOM_UPDATE_DAYS");
        value = redisService.get(key);
        if (value != null) {
            minKingdomUpdateDays = Integer.valueOf(value);
        }

        logger.info(String.format("minKingdomCount:%d,", minKingdomCount));
        logger.info(String.format("minKingdomUpdateDays:%d", minKingdomUpdateDays));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0 - minKingdomUpdateDays);
        extRecentTagMapper.init(calendar.getTime());
        extTagTopicCountMapper.reset();
        extTagTopicCountMapper.init(minKingdomCount);
        extTagUserCountMapper.reset();
        extTagUserCountMapper.init();

        extTagInHomeMapper.append();
        Date latestCreatedAt = extTagInHomeMapper.getMaxCreatedAt();
        extTagInHomeMapper.clear(latestCreatedAt);
        long endTime = System.currentTimeMillis();
        logger.info(String.format("结束准备标签:%dms", endTime - startTime));
    }
}
