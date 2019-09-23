package cn.sibetech.monitor.exception;

import cn.sibetech.monitor.enums.ApiErrorCode;

/**
 * @date 2019/9/28
 * @author hexl
 */
public class ApiErrorException extends RuntimeException{

    private int errCode;

    private String errMsg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ApiErrorException() {
    }

    public ApiErrorException(ApiErrorCode apiErrorCode) {
        this.errCode = apiErrorCode.getCode();
        this.errMsg = apiErrorCode.getDescription();
    }
}