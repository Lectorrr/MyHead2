package com.example.myhead.second.core.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

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
     * add 增加方法
     */

    /**
     * delete 删除方法
     */

    /**
     * list 查方法
     */

    /**
     * update 改方法
     */
}
