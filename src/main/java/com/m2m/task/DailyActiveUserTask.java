package com.m2m.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.m2m.mail.ExMailService;
import com.m2m.mail.Mailer;
import com.m2m.mapper.ExtCmsTaskMapper;
import com.m2m.util.DateUtil;

@Transactional
@Component
public class DailyActiveUserTask {
	private static Logger logger = LoggerFactory.getLogger(DailyActiveUserTask.class);
	
    @Autowired
    private ExtCmsTaskMapper extCmsTaskMapper;
    
    @Autowired
    private ExMailService exMailService;

    @Scheduled(cron = "${task.DailyActiveUserTask.execute}")
    public void execute() {
        Date yesterdayStart = DateUtil.getStartOfYesterday();
        Date todayStart = DateUtil.getStartOfToday();
        long startTime = System.currentTimeMillis();
        logger.info("开始统计日活");
        //从log表中获取数据，计算时间间隔，并插入临时表中
        extCmsTaskMapper.insertTmpUserDailyStatistics(yesterdayStart, todayStart);
        //计算统计数据并新增至日活统计表
        extCmsTaskMapper.insertUserDailyStatisticsCount(yesterdayStart, todayStart);
        long endTime = System.currentTimeMillis();
        if((endTime - startTime)>5*60*1000){
        	String subject = "日志表数据量庞大,日活统计耗时过长,请及时处理!";
        	String message = String.format("日活表数据新增共耗时[%d]毫秒", (endTime - startTime));
        	Mailer.send(exMailService,subject, message);
        }
        logger.info(String.format("日活表数据新增完成，共耗时[%d]毫秒", (endTime - startTime)));
    }
}
