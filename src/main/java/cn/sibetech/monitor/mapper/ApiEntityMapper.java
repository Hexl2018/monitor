package cn.sibetech.monitor.mapper;

import cn.sibetech.monitor.entity.ApiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hexl
 * @date 2019/9/18
 */
public interface ApiEntityMapper extends BaseMapper<ApiEntity> {

    /**
     * 分页查询
     * @param page page
     * @param apiEntity apiEntity
     * @return page
     */
    IPage<ApiEntity> queryPage(Page page, @Param("apiEntity") ApiEntity apiEntity);
}
