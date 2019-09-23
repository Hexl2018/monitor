package cn.sibetech.monitor.config;
import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.job.ScheduledTaskJob;
import cn.sibetech.monitor.util.CodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @author hexl
 * @date 2019/9/17
 */
@Configuration
public class ScheduleConfig {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private HashMap<String, ScheduledFuture<?>> scheduleMap = new HashMap<>();

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public void initTask(List<TaskEntity> tasks){
        try {
            // 停掉旧的定时任务
            for (TaskEntity taskEntity : tasks){
                stopTask(taskEntity.getId());
            }
            // 启动定时任务
            for (TaskEntity taskEntity : tasks){
                startNewTask(taskEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startNewTask(TaskEntity taskEntity) {
        if (CodeConstant.SCHEDULED_TASK_STOP_STATUS.equals(taskEntity.getStatus())) {
            return;
        }
        Runnable task = new ScheduledTaskJob(taskEntity.getType());
        CronTrigger cronTrigger = new CronTrigger(taskEntity.getCron());
        ScheduledFuture<?> future =  threadPoolTaskScheduler.schedule(task, cronTrigger);
        scheduleMap.put(taskEntity.getId(),future);
        logger.info("任务["+taskEntity.getId()+"]启动了");
    }

    public void stopTask(String scheduleKey) {
        ScheduledFuture<?> scheduledFuture = scheduleMap.get(scheduleKey);
        if (scheduledFuture==null) {
            return;
        }
        scheduledFuture.cancel(true);
        scheduleMap.remove(scheduleKey);
        logger.info("任务["+scheduleKey+"]停止了");
    }

    public void updateTask(TaskEntity taskEntity) {
        stopTask(taskEntity.getId());
        scheduleMap.remove(taskEntity.getId());
    }
}