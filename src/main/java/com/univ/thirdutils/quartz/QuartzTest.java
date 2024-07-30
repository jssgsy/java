package com.univ.thirdutils.quartz;

import com.univ.thirdutils.quartz.job.UnivJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.CronCalendar;

import java.text.ParseException;
import java.util.concurrent.*;

/**
 * @author univ
 * date 2024/2/4
 */
public class QuartzTest {

    // jdk自带的定时任务
    @Test
    public void jdkScheduledDemo() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> future = scheduledExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("hhhh");
        }, 2, 3, TimeUnit.SECONDS);
        // 模拟让任务执行完
        future.get();
    }

    @Test
    public void basic() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("univTrigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(1)
                                .withRepeatCount(3)
                )
                .build();

        System.out.println("now : " + System.currentTimeMillis() / 1000);
        scheduler.scheduleJob(jobDetail, trigger);
        // 不可少
        scheduler.start();
        // 避免主线程结束
        while (true) {}
    }

    @Test
    public void basicWithJobData() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // 构建任务时提供任务所需的业务数据
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "univ");
        jobDataMap.put("age", 10);
        jobDataMap.put("full", true);
        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob")
                .setJobData(jobDataMap)
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("univTrigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(1)
                                .withRepeatCount(3)
                )
                .build();

        System.out.println("now : " + System.currentTimeMillis() / 1000);
        scheduler.scheduleJob(jobDetail, trigger);
        // 不可少
        scheduler.start();
        // 避免主线程结束
        while (true) {}
    }

    /**
     * 使用calendar在某些特殊时期不执行任务
     */
    @Test
    public void calendar() throws SchedulerException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("univTrigger")
                // 关联calendar
                .modifiedByCalendar("my_calendar")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(1)
                                .withRepeatCount(50)
                )
                .build();
        // 声明Calendar，这里用CronCalendar为例
        CronCalendar cronCalendar = new CronCalendar("1,5,10,15,20,30,40,50 * * * * ?");

        // Calendar要生效必须交由scheduler管理
        scheduler.addCalendar("my_calendar", cronCalendar, true, true);

        scheduler.scheduleJob(jobDetail, trigger);
        // 不可少
        scheduler.start();
        // 避免主线程结束
        while (true) {}
    }
}


