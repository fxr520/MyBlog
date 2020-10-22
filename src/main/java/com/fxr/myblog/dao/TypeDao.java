package com.fxr.myblog.dao;

import com.fxr.myblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author fanXiaoRui
 * @date 2020/10/9 18:25
 */
public interface TypeDao extends JpaRepository<Type,Long> {

    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTypeTop(Pageable pageable);

}
