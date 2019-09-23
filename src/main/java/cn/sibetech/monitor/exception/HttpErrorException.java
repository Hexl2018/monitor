package cn.sibetech.monitor.exception;

import cn.sibetech.monitor.enums.HttpErrorCode;

/**
 * @date 2019/9/28
 * @author hexl
 */
public class HttpErrorException extends RuntimeException{

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

    public HttpErrorException() {
    }

    public HttpErrorException(HttpErrorCode openErrorCode) {
        this.errCode = openErrorCode.getCode();
        this.errMsg = openErrorCode.getDescription();
    }
}