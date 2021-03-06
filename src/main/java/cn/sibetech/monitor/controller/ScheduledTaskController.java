package cn.sibetech.monitor.controller;

import cn.sibetech.monitor.config.ScheduledConfig;
import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.service.TaskEntityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author hexl
 * @date 2019/9/17
 */
@RestController
@RequestMapping("/scheduled_rest")
public class ScheduledTaskController extends BaseController{

    @Resource
    private ScheduledConfig scheduledConfig;

    @Resource
    private TaskEntityService baseService;

    @RequestMapping("/tasks")
    public Map findTasks() {
        try{
            return success(baseService.findList());
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/start_task")
    public Map startTask() {
        try{
            List<TaskEntity> tasks = baseService.findList();
            scheduledConfig.initTask(tasks);
            return successMsg("任务初始化成功啦！");
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/stop_task/{id}")
    public Map stopTask(@PathVariable String id) {
        try{
            scheduledConfig.stopTask(id);
            return successMsg("任务停止啦！");
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }
}

