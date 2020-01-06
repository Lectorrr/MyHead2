package com.example.myhead.second.entity.sys;

import com.example.myhead.second.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class SysUser extends BaseEntity<String> {

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 加密盐
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 用户状态：0-锁定；1-正常
     */
    @Column(name = "status")
    private Integer status = 1;

    /**
     * 是否删除:-1-已删除；0-正常
     */
    @Column(name = "del_flag")
    private Integer delFlag = 0;

    /**
     * 用户和角色关联
     * 多对多的关系
     */

    @ManyToOne
    @JoinTable(
            name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "UUID")},             //sys_user表中的id在这个表中喂USER_ID
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "UUID")}       //sys_role表中的id在sys_user_role表中喂ROLE_ID
    )
    private SysRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    /**
     * 密码盐
     * 重新对盐进行定义，salt = 用户名 + salt
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", role=" + role +
                '}';
    }
}
