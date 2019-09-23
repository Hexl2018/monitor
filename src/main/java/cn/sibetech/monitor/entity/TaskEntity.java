package cn.sibetech.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author hexl
 * @date 2019/9/17
 */
@TableName("scheduled_task")
public class TaskEntity implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 表达式
     */
    @TableField("cron")
    private String cron;
    /**
     * 任务类型
     */
    @TableField("type")
    private int type;
    /**
     * 启动状态
     */
    @TableField("status")
    private String status;

    /**
     * apiEntity id
     */
    @TableField("api_id")
    private String apiId;

    /**
     * 任务类
     */
    @TableField("job_class")
    private String jobClass;

    /**
     * 任务类型
     */
    @TableField(exist = false)
    private Integer taskType;

    /**
     * raw
     */
    @TableField(exist = false)
    private String raw;

    /**
     * url
     */
    @TableField(exist = false)
    private String url;

    /**
     * 参数
     */
    @TableField(exist = false)
    private String params;

    /**
     * contentType
     */
    @TableField(exist = false)
    private String contentType;

    /**
     * 请求头
     */
    @TableField(exist = false)
    private String headers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }
}
