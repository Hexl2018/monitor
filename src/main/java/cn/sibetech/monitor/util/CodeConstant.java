package cn.sibetech.monitor.util;

/**
 * @author hexl
 * @date 2019/9/18
 */
public class CodeConstant {
    /**
     * 定时任务停止标识
     */
    public static final String SCHEDULED_TASK_STOP_STATUS = "0";
    /**
     * 定时任务启用标识
     */
    public static final String SCHEDULED_TASK_START_STATUS = "1";
    /**
     * get
     */
    public static final String REQUEST_TYPE_GET="get";
    /**
     * post
     */
    public static final String REQUEST_TYPE_POST="post";
    /**
     * headers
     */
    public static final String REQUEST_ARGS_HEADERS="headers";
    /**
     * params
     */
    public static final String REQUEST_ARGS_PARAMS="params";
    /**
     * Content_Type:application/x-www-form-urlencoded
     */
    public static final String CONTENT_TYPE_FORM_URLENCODED="application/x-www-form-urlencoded";
    /**
     * Content_Type:text/xml
     */
    public static final String CONTENT_TYPE_XML = "text/xml";
    /**
     * api 监控开启状态
     */
    public static final String API_MONITOR_OPEN_STATUS = "1";
    /**
     * api 监控关闭状态
     */
    public static final String API_MONITOR_CLOSE_STATUS = "1";
}
