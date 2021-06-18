package com.univ.logback;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来演示logback如何写日志
 * @author univ
 * 2021/6/17 7:52 下午
 */
public class LogBackTest {

    private Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    @Test
    public void test() {
        logger.trace("trace级别---");
        logger.debug("debug级别---");
        logger.info("info 级别，today is {}", new Date());
        logger.warn("warn 级别---");
        logger.error("error 级别");
    }
}
