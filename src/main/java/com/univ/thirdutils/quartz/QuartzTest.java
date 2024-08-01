package com.univ.thirdutils.quartz;

import com.univ.thirdutils.quartz.job.UnivJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.CronCalendar;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.listeners.TriggerListenerSupport;

import java.text.ParseException;
import java.util.Date;
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
    public void executeOnce() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("univTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                // 看这里
                .startAt(new Date(System.currentTimeMillis() + 2000))
                .build();

        System.out.println("now : " + System.currentTimeMillis() / 1000);
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        // 避免主线程结束
        while (true) {}
    }


    @Test
    public void executeJobWithoutTrigger() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob2")
                // 1处：不被Trigger关联的Job必须是durable的
                // Jobs added with no trigger must be durable
                .storeDurably()
                .build();

        System.out.println("now : " + System.currentTimeMillis() / 1000);
        // 2处：一般要先通过addJob方法交由Scheduler调度
        scheduler.addJob(jobDetail, false);
        scheduler.triggerJob(jobDetail.getKey());
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

    @Test
    public void jobListener() throws SchedulerException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("univTrigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2)
                                .withRepeatCount(0)
                )
                .build();


        scheduler.scheduleJob(jobDetail, trigger);
        // 看这里：定义并注册JobListener
        scheduler.getListenerManager().addJobListener(new JobListenerSupport() {
            @Override
            public String getName() {
                return "univ_job_name";
            }

            @Override
            public void jobToBeExecuted(JobExecutionContext context) {
                System.out.println("job准备执行");
            }

            @Override
            public void jobExecutionVetoed(JobExecutionContext context) {
                System.out.println("job被否决了，这里不会出现");
            }

            @Override
            public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
                System.out.println("job被执行了");
            }
        });
        // 不可少
        scheduler.start();
        // 避免主线程结束
        while (true) {}
    }

    @Test
    public void triggerListener() throws SchedulerException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class)
                .withIdentity("univJob")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("univTrigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2)
                                .withRepeatCount(0)
                )
                .build();


        scheduler.scheduleJob(jobDetail, trigger);
        // 看这里：定义并注册JobListener
        scheduler.getListenerManager().addTriggerListener(new TriggerListenerSupport() {
            @Override
            public String getName() {
                return "univ_trigger_listener";
            }

            @Override
            public void triggerFired(Trigger trigger, JobExecutionContext context) {
                System.out.println("trigger被触发了");
            }

            @Override
            public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
                System.out.println("trigger不否决job");
                return false;
            }

            @Override
            public void triggerMisfired(Trigger trigger) {
                System.out.println("trigger没有触发到");
            }

            @Override
            public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
                System.out.println("trigger触发执行完成了");
            }
        }, KeyMatcher.keyEquals(TriggerKey.triggerKey("univTrigger"))); // 只监听名为univTrigger
        // 不可少
        scheduler.start();
        // 避免主线程结束
        while (true) {}
    }

}


