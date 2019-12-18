package com.example.myhead.second.core.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public class BaseService <E extends BaseEntity>{

    @Autowired
    private BaseDao<E, String> baseDao;
}
