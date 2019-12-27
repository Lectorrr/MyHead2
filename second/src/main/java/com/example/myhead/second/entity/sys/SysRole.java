package com.example.myhead.second.entity.sys;

import com.example.myhead.second.core.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity<String> {

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String rolename;

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
    private List<SysPermission> permissions;

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
    private List<SysUser> users;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }
}
