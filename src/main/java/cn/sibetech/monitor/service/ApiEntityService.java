package cn.sibetech.monitor.service;

import cn.sibetech.monitor.entity.ApiEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author hexl
 * @date 2019/9/18
 */
public interface ApiEntityService extends IService<ApiEntity> {
    /**
     * 接口测试
     * @return result
     * @param apiEntity apiEntity
     */
    String testApi(ApiEntity apiEntity);

    /**
     * 保存接口
     * @param apiEntity apiEntity
     */
    void add(ApiEntity apiEntity);

    /**
     * 更新接口
     * @param apiEntity apiEntity
     */
    void edit(ApiEntity apiEntity);

    /**
     * 分页查询
     * @param apiEntity  apiEntity
     * @param current current
     * @param size size
     * @return Page
     */
    IPage<ApiEntity> page(ApiEntity apiEntity, int current, int size);

    /**
     * 移除api
     * @param id id
     */
    void remove(String id);

    /**
     * 变更监控状态
     * @param id id
     * @param status status
     */
    void changeStatus(String id,String status);
}
