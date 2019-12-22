package com.example.myhead.second.core.config.page;

import java.util.HashMap;
import java.util.Map;

/**
 * 对分页请求的参数进行统一的封装，
 * 传入分页查询的页码和数量即可
 *
 * @author lector
 */
public class PageRequest {
    /**
     * 当前请求
     */
    private int PageNum = 1;

    /**
     * 每页数量
     */
    private int pageSize = 10;

    /**
     * 查询参数
     */
    private Map<String, Object> params = new HashMap<>();

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
