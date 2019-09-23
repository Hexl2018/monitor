package cn.sibetech.monitor.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hexl
 * @date 2019/9/17
 */
@TableName("scheduled_record")
public class Record implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 任务id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 执行时间 单位毫秒
     */
    @TableField("time")
    private long time;

    /**
     * 创建时间
     */
    @TableField(value = "when_created", fill = FieldFill.INSERT)
    @JsonIgnore
    private Date whenCreated;

    /**
     * 更新时间
     */
    @TableField(value = "when_modified", fill = FieldFill.INSERT,update = "NOW()")
    @JsonIgnore
    private Date whenModified;

    /**
     * 错误码
     */
    @TableField("error_code")
    private String errorCode;

    /**
     * 错误信息
     */
    @TableField("error_message")
    private String errorMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Date getWhenModified() {
        return whenModified;
    }

    public void setWhenModified(Date whenModified) {
        this.whenModified = whenModified;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Record() {
    }

    public Record(String taskId, long time, String errorCode, String errorMessage) {
        this.taskId = taskId;
        this.time = time;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
