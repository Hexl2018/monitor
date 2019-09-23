package cn.sibetech.monitor.service.impl;

import cn.sibetech.monitor.entity.ApiEntity;
import cn.sibetech.monitor.entity.TaskEntity;
import cn.sibetech.monitor.enums.ApiErrorCode;
import cn.sibetech.monitor.exception.ApiErrorException;
import cn.sibetech.monitor.exception.WebException;
import cn.sibetech.monitor.mapper.ApiEntityMapper;
import cn.sibetech.monitor.service.ApiEntityService;
import cn.sibetech.monitor.service.TaskEntityService;
import cn.sibetech.monitor.util.CodeConstant;
import cn.sibetech.monitor.util.JsonUtils;
import cn.sibetech.monitor.util.OkHttpUtil;
import cn.sibetech.monitor.util.ScheduledCronUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author hexl
 * @date 2019/9/18
 */
@Service
public class ApiEntityServiceImpl extends ServiceImpl<ApiEntityMapper, ApiEntity> implements ApiEntityService {

    @Resource
    private TaskEntityService taskEntityService;

    @Override
    public String testApi(ApiEntity apiEntity){
        String resultString = "";
        String requestType = apiEntity.getRequestType();
        String url = apiEntity.getUrl();
        Map<String,String> params = JsonUtils.makeParamsMap(apiEntity.getParams());
        Map<String,String> headers = JsonUtils.makeParamsMap(apiEntity.getHeaders());
        if (StringUtils.isEmpty(requestType)) {
            throw new ApiErrorException(ApiErrorCode.Missing_Request_Type);
        }
        if (StringUtils.isEmpty(url)) {
            throw new ApiErrorException(ApiErrorCode.Missing_Url);
        }
        if (CodeConstant.REQUEST_TYPE_GET.equals(apiEntity.getRequestType())) {
            resultString = OkHttpUtil.httpGet(url,headers,params);
        }else if (CodeConstant.REQUEST_TYPE_POST.equals(apiEntity.getRequestType())) {
            String contentType = apiEntity.getContentType();
            if (StringUtils.isEmpty(contentType)) {
                throw new ApiErrorException(ApiErrorCode.Missing_Content_Type);
            }
            resultString = doPost(url,contentType,params,headers,apiEntity.getRaw());
        }
        return resultString;
    }

    @Override
    public void add(ApiEntity apiEntity) {
        validateApi(apiEntity);
        baseMapper.insert(apiEntity);
        // 添加定时任务
        TaskEntity taskEntity = makeTaskEntity(apiEntity);
        taskEntityService.getBaseMapper().insert(taskEntity);
    }

    @Override
    public void edit(ApiEntity apiEntity) {
        validateApi(apiEntity);
        baseMapper.updateById(apiEntity);
        // 更新定时任务
        TaskEntity taskEntity = makeTaskEntity(apiEntity);
        taskEntityService.edit(taskEntity);
    }

    @Override
    public IPage<ApiEntity> page(ApiEntity apiEntity, int current, int size) {
        Page<ApiEntity> page = new Page<>(current, size);
        return baseMapper.queryPage(page,apiEntity);
    }

    @Override
    public void remove(@NotNull String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void changeStatus(@NotNull String id, String status) {
        ApiEntity apiEntity = baseMapper.selectById(id);
        apiEntity.setStatus(status);
        baseMapper.updateById(apiEntity);
        // 根据status添加或新增定时任务
        if (CodeConstant.API_MONITOR_OPEN_STATUS.equals(status)) {

        }else if (CodeConstant.API_MONITOR_CLOSE_STATUS.equals(status)) {

        }
    }

    private TaskEntity makeTaskEntity(ApiEntity apiEntity) {
        String jobClass;
        if (CodeConstant.REQUEST_TYPE_GET.equals(apiEntity.getRequestType())) {
            jobClass = CodeConstant.HTTP_GET_JOB_CLASS;
        }else if (CodeConstant.CONTENT_TYPE_FORM_URLENCODED.equals(apiEntity.getContentType())&&CodeConstant.REQUEST_TYPE_POST.equals(apiEntity.getRequestType())) {
            jobClass = CodeConstant.HTTP_POST_FORM_JOB_CLASS;
        }else {
            jobClass = CodeConstant.HTTP_POST_RAW_JOB_CLASS;
        }
        // 间隔 (s)
        String cron = ScheduledCronUtil.makeCronPerSeconds(apiEntity.getTimeInterval());
        return new TaskEntity(apiEntity.getId(),cron,apiEntity.getStatus(),jobClass);
    }

    private void validateApi(ApiEntity apiEntity) {
        try{
            testApi(apiEntity);
        }catch (Exception e) {
            throw new WebException("接口校验不通过，无法添加！"+e.getMessage());
        }
    }

    private String doPost(String url, String contentType, Map<String,String> params, Map<String,String> headers, String raw) {
        String result;
        if (CodeConstant.CONTENT_TYPE_FORM_URLENCODED.equals(contentType)) {
            result = OkHttpUtil.httpPost(url,headers,params);
        }else {
            result = OkHttpUtil.httpPostRaw(url,contentType,headers,raw);
        }
        return result;
    }
}
