package com.briup.service;

import java.util.List;

import com.briup.bean.Comment;
import com.briup.bean.extend.CommentExtend;

public interface ICommentService {

// 分页展示所有评论
	List<Comment> findAll();
// 分页展示所有评论及回复
	List<CommentExtend> findAllWithReply();
// 显示某一评论的所有回复
	CommentExtend findById(long id);
// 回复某一评论
	void reply(Comment comment);
// 审核某一评论
	void examine(Comment comment, boolean isPass);

}
