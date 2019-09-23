package cn.sibetech.monitor.service.impl;

import cn.sibetech.monitor.entity.Record;
import cn.sibetech.monitor.mapper.RecordMapper;
import cn.sibetech.monitor.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * @author hexl
 * @date 2019/9/17
 */
@Service("recordService")
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

}
