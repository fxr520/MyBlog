package com.fxr.myblog.dao;

import com.fxr.myblog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author fanXiaoRui
 * @date 2020/10/12 8:28
 */
public interface TagDao  extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTagTop(Pageable pageable);
}
