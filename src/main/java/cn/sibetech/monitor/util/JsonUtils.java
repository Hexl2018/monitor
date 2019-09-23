package cn.sibetech.monitor.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liwj on 16/7/29.
 */
public class JsonUtils {
    private final static String ERR_CODE = "errCode";
    private final static String ERR_MSG = "errMsg";
    private final static String ERR_MSG_OK = "ok";
    private final static int ERR_CODE_OK = 0;
    private final static int SEP_LENGTH = 2;

    public static Map error(int errorCode, String errorMessage) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put(ERR_CODE, errorCode);
        map.put(ERR_MSG, errorMessage);
        return map;
    }

    public static Map success(String errMsg, Object... objects) {
        Map<Object,Object> map = new LinkedHashMap<>();
        map.put(ERR_CODE, ERR_CODE_OK);
        map.put(ERR_MSG, StringUtils.isBlank(errMsg) ? ERR_MSG_OK : errMsg);
        if (objects != null && objects.length % SEP_LENGTH == 0) {
            for (int i = 0; i < objects.length; i = i + SEP_LENGTH) {
                map.put(objects[i], objects[i + 1]);
            }
        }
        return map;
    }

    public static Map<String,String> makeParamsMap(String jsonString) {
        Map<String,String> map = new HashMap<>();
        JSONArray jsonArray= JSONArray.parseArray(jsonString);
        if (jsonArray!=null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map.put(jsonObject.getString("key"), jsonObject.getString("value"));
            }
        }
        return map;
    }
}
