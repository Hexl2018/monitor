package cn.sibetech.monitor.service;

import cn.sibetech.monitor.entity.TaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author hexl
 * @date 2019/9/23
 */
public interface TaskEntityService extends IService<TaskEntity> {
    /**
     * 更新 taskEntity
     * @param taskEntity taskEntity
     */
    void edit(TaskEntity taskEntity);

    /**
     * 任务列表
     * @return List
     */
    List<TaskEntity> findList();
}
