package cn.sibetech.monitor.service.impl;

import cn.sibetech.monitor.entity.ApiEntity;
import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.mapper.ScheduledTaskMapper;
import cn.sibetech.monitor.service.ScheduledTaskService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hexl
 * @date 2019/9/17
 */
@Service
public class ScheduledTaskServiceImpl extends ServiceImpl<ScheduledTaskMapper, TaskEntity> implements ScheduledTaskService {
    @Override
    public List<TaskEntity> findList() {
        Wrapper<TaskEntity> wrapper = new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void addScheduledTask(ApiEntity apiEntity) {
        TaskEntity taskEntity = new TaskEntity();

    }

    @Override
    public void stopScheduledTask(ApiEntity apiEntity) {

    }

    @Override
    public void removeScheduledTask(ApiEntity apiEntity) {

    }

    @Override
    public void updateScheduledTask(ApiEntity apiEntity) {

    }
}
