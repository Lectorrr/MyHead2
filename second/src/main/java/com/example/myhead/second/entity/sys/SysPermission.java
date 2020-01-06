package com.example.myhead.second.entity.sys;

import com.example.myhead.second.core.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "sys_Permission")
public class SysPermission extends BaseEntity<String> {

    /**
     * 权限
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 资源路径
     */
    @Column(name = "resource_url")
    private String resourceUrl;

    /**
     * 资源类型，[menu | button]
     */
    @Column(name = "resource_type", columnDefinition = "enum('menu', 'button')")
    private String resourceType;

    /**
     * 权限和角色的关系
     * 多对一的关系
     */
    @ManyToOne
    @JoinTable(
            name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "UUID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "UUID")}
    )
    private SysRole role;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}
