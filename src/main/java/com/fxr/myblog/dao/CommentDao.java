package com.fxr.myblog.dao;

import com.fxr.myblog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fanXiaoRui
 * @date 2020/10/22 13:33
 */
public interface CommentDao extends JpaRepository<Comment,Long> {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
