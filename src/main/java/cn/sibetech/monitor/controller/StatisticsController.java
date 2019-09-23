package cn.sibetech.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hexl
 * @date 2019/9/18
 */
@Controller
@RequestMapping("/data_statistics")
public class StatisticsController extends BaseController{

    private static final String BASE_PATH = "/statistics/";

    @RequestMapping("/index")
    public String index() {
        return BASE_PATH +"index";
    }
}
