package com.example.myhead.second.entity.sys;

import com.example.myhead.second.core.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity<String> {

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 是否删除:-1-已删除；0-正常
     */
    @Column(name = "del_flag")
    private Integer delFlag = 0;


    /**
     * 角色和权限关联
     * 多对多的关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "UUID")},
            inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "UUID")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"ROLE_ID", "PERMISSION_ID"})}
    )
    private SysPermission permission;

    /**
     * 角色和用户关联
     * 多对多的关系
     */
    @ManyToMany
    @JoinTable(
            name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "UUID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "UUID")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "ROLE_ID"})}
    )
    private SysUser users;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public SysPermission getPermission() {
        return permission;
    }

    public void setPermission(SysPermission permission) {
        this.permission = permission;
    }

    public SysUser getUsers() {
        return users;
    }

    public void setUsers(SysUser users) {
        this.users = users;
    }
}
