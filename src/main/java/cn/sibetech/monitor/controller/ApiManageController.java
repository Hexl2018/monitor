package cn.sibetech.monitor.controller;

import cn.sibetech.monitor.entity.ApiEntity;
import cn.sibetech.monitor.service.ApiEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author hexl
 * @date 2019/9/18
 */
@Controller
@RequestMapping("/api_manage")
public class ApiManageController extends BaseController{

    private static final String BASE_PATH = "/api/";

    @Resource
    private ApiEntityService baseService;

    @RequestMapping("/index")
    public String index() {
        return BASE_PATH +"index";
    }

    @RequestMapping("/to_add")
    public String add() {
        return BASE_PATH +"add";
    }

    @RequestMapping("/to_edit/{id}")
    public String edit(@PathVariable String id, ModelMap modelMap) {
        modelMap.put("id",id);
        return BASE_PATH +"edit";
    }

    @RequestMapping("/test_api")
    @ResponseBody
    public Map testApi(ApiEntity apiEntity) {
        try{
            return success(baseService.testApi(apiEntity));
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/page")
    @ResponseBody
    public Map page(ApiEntity apiEntity,int current,int size) {
        try{
            return success(baseService.page(apiEntity,current,size));
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map add(ApiEntity apiEntity) {
        try{
            baseService.add(apiEntity);
            return successMsg("添加成功！");
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map edit(ApiEntity apiEntity) {
        try{
            baseService.edit(apiEntity);
            return successMsg("更新成功！");
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/remove_api/{id}")
    @ResponseBody
    public Map remove(@PathVariable String id) {
        try{
            baseService.remove(id);
            return successMsg("删除成功！");
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/change_status")
    @ResponseBody
    public Map changeStatus(String id,String status) {
        try{
            baseService.changeStatus(id,status);
            return successMsg("更新成功！");
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public Map find(@PathVariable String id) {
        try{
            return success(baseService.getBaseMapper().selectById(id));
        }catch (Exception e) {
            return error(-1,e.getMessage());
        }
    }
}
