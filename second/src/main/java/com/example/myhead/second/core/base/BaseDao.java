package com.example.myhead.second.core.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseDao <E extends BaseEntity, T extends Serializable> extends JpaRepository<E, T> {

}
