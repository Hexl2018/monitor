package cn.sibetech.monitor.job;

import cn.sibetech.monitor.util.OkHttpUtil;

import java.util.Map;

/**
 * http请求任务类
 * @author hexl
 * @date 2019/9/23
 */
public class HttpPostFormJob implements Runnable{
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

    public HttpPostFormJob() {}

    public HttpPostFormJob(String url, String contentType, Map<String, String> headers, Map<String, String> params, String raw) {
        this.url = url;
        this.contentType = contentType;
        this.headers = headers;
        this.params = params;
        this.raw = raw;
    }

    @Override
    public void run() {
        OkHttpUtil.httpPost(url,headers,params);
    }
}
