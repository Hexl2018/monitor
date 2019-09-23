package cn.sibetech.monitor.config;

import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.util.CodeConstant;
import cn.sibetech.monitor.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void initTask(List<TaskEntity> tasks) {
        try {
            // 停掉旧的定时任务
            for (TaskEntity taskEntity : tasks) {
                stopTask(taskEntity.getId());
            }
            // 启动定时任务
            for (TaskEntity taskEntity : tasks) {
                startNewTask(taskEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startNewTask(TaskEntity taskEntity) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (CodeConstant.SCHEDULED_TASK_STOP_STATUS.equals(taskEntity.getStatus())) {
            return;
        }
        Class<?> clazz = Class.forName(taskEntity.getJobClass());
        if (clazz==null) {
            logger.error("任务初始化失败，未找到对应class["+taskEntity.getJobClass()+"]");
            return;
        }
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class, Map.class, Map.class, String.class);
        Object task = constructor.newInstance(taskEntity.getUrl()
                , taskEntity.getContentType()
                , JsonUtils.makeParamsMap(taskEntity.getHeaders())
                , JsonUtils.makeParamsMap(taskEntity.getParams())
                , taskEntity.getRaw()
        );
        CronTrigger cronTrigger = new CronTrigger(taskEntity.getCron());
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule((Runnable) task, cronTrigger);
        scheduleMap.put(taskEntity.getId(), future);
        logger.info("任务[" + taskEntity.getId() + "]启动了");
    }

    public void stopTask(String scheduleKey) {
        ScheduledFuture<?> scheduledFuture = scheduleMap.get(scheduleKey);
        if (scheduledFuture == null) {
            return;
        }
        scheduledFuture.cancel(true);
        scheduleMap.remove(scheduleKey);
        logger.info("任务[" + scheduleKey + "]停止了");
    }

    public void updateTask(TaskEntity taskEntity) {
        stopTask(taskEntity.getId());
        scheduleMap.remove(taskEntity.getId());
    }
}