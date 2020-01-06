package com.example.myhead.second.service.sys;

import com.example.myhead.second.core.base.BaseService;
import com.example.myhead.second.dao.sys.SysUserDao;
import com.example.myhead.second.entity.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.List;

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

    public SysUser findUserByUser(String account, String password) {

        Specification<SysUser> specification = new Specification<SysUser>() {

            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<String> tAccount = root.get("account");
                Predicate predicateAccount = criteriaBuilder.equal(tAccount, account);

                Path<String> tPassword = root.get("password");
                Predicate predicatePassword = criteriaBuilder.equal(tPassword, password);

                return criteriaBuilder.and(predicateAccount, predicatePassword);
            }
        };
        List<SysUser> all = this.sysUserDao.findAll(specification);

        return CollectionUtils.isEmpty(all) ? null : all.get(0);
    }
}
