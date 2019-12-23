package com.example.myhead.second.service.sys;

import com.example.myhead.second.core.base.BaseService;
import com.example.myhead.second.dao.sys.SysUserDao;
import com.example.myhead.second.entity.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends BaseService<SysUser> {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     */
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    /**
     * 根据用户名和密码获取用户信息
     *
     * @param username 用户名
     * @param password 密码
     */
    public SysUser getByUsernameAndPassword(String username, String password) {
        return sysUserDao.getByUsernameAndPassword(username, password);
    }
}
