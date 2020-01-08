package com.example.myhead.second.entity.bookInfo;

import com.example.myhead.second.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntity<String> {

    /**
     * 书籍类型
     */
    @Column(name = "type_code")
    private Integer typeCode = 0;

    /**
     * 类型名称
     */
    @Column(name = "type")
    private String type;

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
