package cn.sibetech.monitor.util;

import cn.sibetech.monitor.enums.HttpErrorCode;
import cn.sibetech.monitor.exception.WebException;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author hexl
 * @date 2019/9/18
 */
public class OkHttpUtil {

    public static String httpGet(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            url = makeGetParams(url, params);
            Request.Builder builder = new Request.Builder()
                    .url(url).get();
            return executeRequest(builder, headers);
        } catch (Exception e) {
            throw new WebException(e.getMessage());
        }
    }

    public static String httpPost(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            FormBody.Builder formBuilder = new FormBody.Builder();
            //添加参数
            if (params.keySet().size() > 0) {
                for (String key : params.keySet()) {
                    formBuilder.add(key, params.get(key));
                }
            }
            Request.Builder builder = new Request.Builder()
                    .url(url)
                    .post(formBuilder.build());
            return executeRequest(builder, headers);
        } catch (Exception e) {
            throw new WebException(e.getMessage());
        }
    }

    public static String httpPostRaw(String url, String contentType, Map<String, String> headers, String raw) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(contentType), raw);
            Request.Builder builder = new Request.Builder()
                    .url(url)
                    .post(requestBody);
            return executeRequest(builder, headers);
        } catch (Exception e) {
            throw new WebException(e.getMessage());
        }
    }

    private static String executeRequest(Request.Builder builder, Map<String, String> headers) throws IOException {
        String result;
        OkHttpClient client = new OkHttpClient();
        Request request = makeRequest(builder, headers);
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            result = Objects.requireNonNull(response.body()).string();
        } else {
            String errMsg = HttpErrorCode.toEnum(response.code()).getDescription();
            throw new WebException("请求失败！错误码："+response.code()+" "+errMsg);
        }
        return result;
    }

    private static Request makeRequest(Request.Builder builder, Map<String, String> headers) {
        makeHeaders(builder, headers);
        return builder.build();
    }

    private static void makeHeaders(Request.Builder builder, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    private static String makeGetParams(String url, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (urlBuilder.indexOf("?") < 0) {
                urlBuilder.append("?");
            } else {
                urlBuilder.append("&");
            }
            urlBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return urlBuilder.toString();
    }
}
