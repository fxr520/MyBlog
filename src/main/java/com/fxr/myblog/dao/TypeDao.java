package com.fxr.myblog.dao;

import com.fxr.myblog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanXiaoRui
 * @date 2020/10/9 18:25
 */
public interface TypeDao extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
