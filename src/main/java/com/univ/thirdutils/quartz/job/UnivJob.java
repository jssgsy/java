package com.univ.thirdutils.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author univ
 * date 2024/2/4
 */
public class UnivJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        System.out.println("此任务的业务数据如下：" );
        mergedJobDataMap.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
    }
}
