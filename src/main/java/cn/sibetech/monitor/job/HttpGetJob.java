package cn.sibetech.monitor.job;

import cn.sibetech.monitor.entity.Record;
import cn.sibetech.monitor.service.RecordService;
import cn.sibetech.monitor.util.ApplicationContextUtil;
import cn.sibetech.monitor.util.OkHttpUtil;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Map;

/**
 * http请求任务类
 * @author hexl
 * @date 2019/9/23
 */
public class HttpGetJob implements Runnable{

    private String taskId;
    /** 请求地址 */
    private String url;
    /** contentType */
    private String contentType;
    /** 请求头 */
    private Map<String, String> headers;
    /** 参数 */
    private Map<String, String> params;
    /** raw */
    private String raw;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public HttpGetJob(String taskId,String url, String contentType, Map<String, String> headers, Map<String, String> params, String raw) {
        this.taskId = taskId;
        this.url = url;
        this.contentType = contentType;
        this.headers = headers;
        this.params = params;
        this.raw = raw;
    }

    public HttpGetJob() {
    }

    @Override
    public void run() {
        String errorCode = "0";
        String errorMessage = "";
        StopWatch clock = new StopWatch();
        try{
            OkHttpUtil.httpGet(url,headers,params);
        }catch (Exception e) {
            errorCode = "-1";
            errorMessage = e.getMessage();
        }
        clock.stop();
        long time = clock.getTime();
        // 保存记录
        saveRecord(taskId,time,errorCode,errorMessage);
    }

    private void saveRecord(String taskId, long time, String errorCode, String errorMessage) {
        Record record = new Record(taskId,time,errorCode,errorMessage);
        RecordService recordService = (RecordService) ApplicationContextUtil.getBean("recordService");
        recordService.getBaseMapper().insert(record);
    }
}
