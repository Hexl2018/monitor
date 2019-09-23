package cn.sibetech.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hexl
 * @date 2019/9/23
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
