package com.example.myhead.second.core.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

/**
 * BaseService 类：
 *  是对通用的增删改查方法的封装，
 *  统一定义了包含保存、删除、批量删除、根据ID查询和分页查询的方法，
 *  一般的 Service 类会集成这个类。
 */
@Transactional
public class BaseService<E extends BaseEntity> {

    @Autowired
    private BaseDao<E, String> baseDao;

    /**
     * 增加/改 方法
     * save() 方法：当主键存在时更新数据，当主键不存在时插入数据。
     */
    public void saveOrUpdate(E entity) {
        baseDao.save(entity);
    }

    /**
     * 删除 方法
     *
     * @param id 传入实体的id
     */
    public void deleteById(String id) {
        baseDao.deleteById(id);
    }

    /**
     * 查找方法
     */
    public List<E> showList() {
        return baseDao.findAll();
    }
}
