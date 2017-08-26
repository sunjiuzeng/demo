package com.demo.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TimedTask {
    private static final Logger logger= LoggerFactory.getLogger(TimedTask.class);
    /**
     * 业务逻辑处理
     */
    public void execute() {
        // 执行业务逻辑
        // ........
        logger.info(new Date().toString());
        System.out.println("定时任务.......");
    }
}
