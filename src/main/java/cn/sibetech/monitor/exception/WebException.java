package cn.sibetech.monitor.exception;


/**
 * @author hexl
 */
public class WebException extends RuntimeException {
    public WebException() {
        super();
    }

    public WebException(String msg) {
        super(msg);
    }
}