package cn.sibetech.monitor.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hexl
 * @date 2019/9/20
 */
@Component
public class MetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName("whenCreated", now, metaObject);
        setFieldValByName("whenModified", now, metaObject);
        setFieldValByName("status", "0", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName("whenModified", now, metaObject);
    }
}
