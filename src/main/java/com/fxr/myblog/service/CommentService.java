package com.fxr.myblog.service;

import com.fxr.myblog.po.Comment;

import java.util.List;

/**
 * @author fanXiaoRui
 * @date 2020/10/22 13:31
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
