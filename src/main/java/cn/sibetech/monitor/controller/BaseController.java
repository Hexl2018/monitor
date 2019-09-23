package cn.sibetech.monitor.controller;

import cn.sibetech.monitor.util.JsonUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author hexl
 * @date 2019/9/17
 */
@SuppressWarnings("SameParameterValue")
public class BaseController {
    Map success(Object data) {
        return JsonUtils.success("ok", "data", data);
    }

    Map successMsg(String msg) {
        return JsonUtils.success(msg);
    }

    Map error(int errCode, String errMsg) {
        return JsonUtils.error(errCode, errMsg);
    }
}
