package com.example.myhead.second.dao.sys;

import com.example.myhead.second.core.base.BaseDao;
import com.example.myhead.second.entity.sys.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends BaseDao<SysUser, String> {

    SysUser findByUsername(String username);
    SysUser findByUsernameAndPassword(String username, String password);
    SysUser getByUsername(String username);
}
