package cn.sibetech.monitor.component;

import cn.sibetech.monitor.config.ScheduledConfig;
import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.service.ScheduledTaskService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hexl
 * @date 2019/9/17
 */
@Component
@Order(1)
public class ApplicationStartRunner implements ApplicationRunner {

    @Resource
    private ScheduledConfig scheduleConfig;

    @Resource
    private ScheduledTaskService baseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<TaskEntity> tasks = baseService.findList();
        scheduleConfig.initTask(tasks);
    }
}
