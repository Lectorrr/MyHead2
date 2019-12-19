package com.example.myhead.second.dao.sys;

import com.example.myhead.second.core.base.BaseDao;
import com.example.myhead.second.entity.sys.SysUser;

public interface SysUserDao extends BaseDao<SysUser, String> {

    SysUser findByUsername(String username);

    SysUser getByUsernameAndPassword(String username, String password);
}
