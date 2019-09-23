package cn.sibetech.monitor.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hexl
 * @date 2019/9/18
 */
@TableName("api_entity")
public class ApiEntity implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 接口名称
     */
    @TableField("name")
    private String name;

    /**
     * 请求方式（get or post）
     */
    @TableField("request_type")
    private String requestType;

    /**
     * contentType
     */
    @TableField("content_type")
    private String contentType;

    /**
     * 请求地址
     */
    @TableField("url")
    private String url;

    /**
     * 参数
     */
    @TableField("params")
    private String params;

    /**
     * 请求头
     */
    @TableField("headers")
    private String headers;

    /**
     * 请求body
     */
    @TableField("raw")
    private String raw;

    /**
     * 请求间隔（s）
     */
    @TableField("time_interval")
    private int timeInterval;

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
     * 监控启用状态
     */
    @TableField(value = "status",fill = FieldFill.INSERT)
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
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

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
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

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
