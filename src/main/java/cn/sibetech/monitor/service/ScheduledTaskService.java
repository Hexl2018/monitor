package cn.sibetech.monitor.service;

import cn.sibetech.monitor.entity.ApiEntity;
import cn.sibetech.monitor.entity.TaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.config.ScheduledTask;

import java.util.List;

/**
 * @author hexl
 * @date 2019/9/17
 */
public interface ScheduledTaskService extends IService<TaskEntity> {
    /**
     * 获取任务列表
     * @return list
     */
    List<TaskEntity> findList();

    /**
     * 开启定时任务
     * @param taskEntity taskEntity
     */
    void startScheduledTask(TaskEntity taskEntity);

    /**
     * 停止定时任务
     * @param taskEntity taskEntity
     */
    void stopScheduledTask(TaskEntity taskEntity);
}
