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

    /**
     * 为方便任务调度，此处id 与 api id 保持一致
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 表达式
     */
    @TableField("cron")
    private String cron;
    /**
     * 启动状态
     */
    @TableField("status")
    private String status;

    /**
     * 任务类
     */
    @TableField("job_class")
    private String jobClass;

    /**
     * url
     */
    @TableField(exist = false)
    private String url;

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

    /**
     * 参数
     */
    @TableField(exist = false)
    private String params;

    /**
     * raw
     */
    @TableField(exist = false)
    private String raw;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public TaskEntity() {
    }

    public TaskEntity(String id, String cron, String status, String jobClass) {
        this.id = id;
        this.cron = cron;
        this.status = status;
        this.jobClass = jobClass;
    }

    public TaskEntity(String id, String cron, String status, String jobClass, String url, String contentType, String headers, String params, String raw) {
        this.id = id;
        this.cron = cron;
        this.status = status;
        this.jobClass = jobClass;
        this.url = url;
        this.contentType = contentType;
        this.headers = headers;
        this.params = params;
        this.raw = raw;
    }
}
