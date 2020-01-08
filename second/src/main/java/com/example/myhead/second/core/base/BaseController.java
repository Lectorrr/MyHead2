package com.example.myhead.second.core.base;

import com.example.myhead.second.common.entity.UUResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * @author lector
 */
public class BaseController<E extends BaseEntity, T extends Serializable> {

    @Autowired
    private BaseService<E> baseService;

    protected String PathPrefix;

    private String viewPrefix;

    protected final Class<E> entityClass;

    protected BaseController() {
        this.entityClass = null;
        setViewPrefix(defaultViewPrefix());
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);

        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }
        return currentViewPrefix;
    }

    public String viewName(String suffixName) {

        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }

    public void setViewPrefix(String viewPrefix) {

        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    public String getPathPrefix() {
        return PathPrefix;
    }

    public void setPathPrefix(String pathPrefix) {
        PathPrefix = pathPrefix;
    }


    /**
     * list 查方法
     */

    /**
     * 显示 create 创建页面
     */
    @RequestMapping("/showCreatePage")
    public String showCreatePage() {
        return this.getPathPrefix() + "create";
    }

    /**
     * 显示 list 界面
     */
    @RequestMapping("/showList")
    public String showList() {
        return this.viewName(this.getPathPrefix()) + "list";
    }

    /**
     * 显示 update 编辑界面
     */
    @RequestMapping("/updatePage/{id}")
    public String updatePage(@PathVariable String id, Model model) {
        Object object = baseService.get(id);
        model.addAttribute("e", object);
        return this.getPathPrefix() + "update";
    }

    /**
     * add 增加方法
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object save(E entity) {
        UUResult result = new UUResult();
        try {
            Object object = baseService.saveOrUpdate(entity);
            result.setData(object);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    /**
     * add 增加 json 数据格式方法
     */
    @RequestMapping(value = "/addJson", method = RequestMethod.POST)
    @ResponseBody
    public Object addJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        E entity = objectMapper.readValue(json, entityClass);
        UUResult result = new UUResult();
        try {
            Object object = baseService.saveOrUpdate(entity);
            result.setData(object);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            return result;
        }
        return result;
    }


//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    public Object update(E entity) {
//
//        UUResult result = new UUResult();
//        if (StringUtils.isEmpty(entity.getId())) {
//            result.setSuccess(false);
//            result.setMessage("操作失败，不存在ID，请检查更新数据！");
//            return result;
//        }
//
//        try {
////            E entity = (E) baseService.get(entity.getId());
//            t.setCreateDate(entity.getCreateDate());
//            t.setUpdateDate(new Date());
//            Object obj = baseService.save(t);
//            result.setData(obj);
//            result.setSuccess(true);
//        }catch (Exception e){
//            result.setSuccess(false);
//            return result;
//        }
//        return result;
//    }

    /**
     * 删除
     *
     * @param id 实体的id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable String id) {
        UUResult result = new UUResult();
        try {
            baseService.deleteById(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    /**
     * 批量删除
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody
    public Object deletes(String json) {
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        List<Object> objects = jsonParser.parseList(json);
        String[] temp = new String[objects.size()];
        UUResult result = new UUResult();
        try {
            baseService.deletes(objects.toArray(temp));
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    /**
     * 查看详细数据
     *
     * @param id 实体id
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object listData(@PathVariable String id) {
        Object object = baseService.get(id);
        UUResult result = new UUResult();
        result.setSuccess(true);
        result.setData(object);
        return result;
    }
}
