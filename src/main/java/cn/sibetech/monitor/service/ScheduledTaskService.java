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
     * 添加定时任务
     * @param apiEntity apiEntity
     */
    void addScheduledTask(ApiEntity apiEntity);

    /**
     * 停止定时任务
     * @param apiEntity apiEntity
     */
    void stopScheduledTask(ApiEntity apiEntity);

    /**
     * 移除定时任务
     * @param apiEntity apiEntity
     */
    void removeScheduledTask(ApiEntity apiEntity);


    /**
     * 更新定时任务
     * @param apiEntity apiEntity
     */
    void updateScheduledTask(ApiEntity apiEntity);
}
