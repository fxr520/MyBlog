package com.fxr.myblog.dao;

import com.fxr.myblog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanXiaoRui
 * @date 2020/10/12 8:28
 */
public interface TagDao  extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
