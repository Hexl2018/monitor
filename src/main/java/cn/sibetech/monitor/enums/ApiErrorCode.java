package cn.sibetech.monitor.enums;

/**
 * @author hexl
 * @date 2019/9/19
 */
public enum ApiErrorCode {
    /**/
    Missing_Request_Type(10001, "Request-Type不能为空！"),
    Missing_Content_Type(10002, "Content-Type不能为空！"),
    Missing_Url(10003, "Url不能为空！"),
    none(-1,"");

    private int code;

    private String description;

    ApiErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
