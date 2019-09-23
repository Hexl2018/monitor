package cn.sibetech.monitor.service.impl;

import cn.sibetech.monitor.config.ScheduledConfig;
import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.exception.WebException;
import cn.sibetech.monitor.mapper.TaskEntityMapper;
import cn.sibetech.monitor.service.TaskEntityService;
import cn.sibetech.monitor.util.CodeConstant;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hexl
 * @date 2019/9/17
 */
@Service
public class TaskEntityServiceImpl extends ServiceImpl<TaskEntityMapper, TaskEntity> implements TaskEntityService {

    @Resource
    private ScheduledConfig scheduledConfig;

    @Override
    public List<TaskEntity> findList() {
        Wrapper<TaskEntity> wrapper = new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void edit(TaskEntity taskEntity) {
        baseMapper.updateById(taskEntity);
        // 更新任务调度
        if (CodeConstant.SCHEDULED_TASK_START_STATUS.equals(taskEntity.getStatus())) {
            try{
                scheduledConfig.startNewTask(taskEntity);
            }catch (Exception e) {
                throw new WebException("开启定时任务失败！"+e.getMessage());
            }
        }
    }
}
