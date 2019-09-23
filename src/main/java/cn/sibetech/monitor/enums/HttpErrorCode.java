package cn.sibetech.monitor.enums;

/**
 * @author hexl
 */

public enum HttpErrorCode {
    /**/
    Http_400(400, "请求出现语法错误！"),
    Http_401(401, "未授权！"),
    Http_403(403, "禁止访问！"),
    Http_404(404, "接口不存在！"),
    Http_405(405, "资源被禁止！ "),
    Http_406(406, "指定的资源已经找到，但是mime类型和客户在accpet头中所指定的不兼容！"),
    Http_407(407, "要求代理身份验证！"),
    Http_410(410, "永远不可用！"),
    Http_412(412, "先决条件失败！"),
    Http_414(414, "请求 - URI 过长 ！"),
    Http_500(500, "服务器内部错误！"),
    None(-1,"");

    private int code;
    
    private String description;
    
    HttpErrorCode(int code, String description) {
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

    public static HttpErrorCode toEnum(int code){
        try {
            return valueOf("Http_"+code);
        } catch (Exception e){
            return None;
        }
    }
    
}
