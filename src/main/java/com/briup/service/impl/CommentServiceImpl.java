package com.briup.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Comment;
import com.briup.bean.CommentExample;
import com.briup.bean.extend.CommentExtend;
import com.briup.dao.extend.CommentExtendMapper;
import com.briup.service.ICommentService;
import com.briup.util.StatusNo;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentExtendMapper commentMapper;
	
	@Override
	public List<Comment> findAll() {
		return commentMapper.selectByExample(new CommentExample());
	}

	@Override
	public List<CommentExtend> findAllWithReply() {
		return commentMapper.findAllCascade();
	}

	@Override
	public CommentExtend findById(long id) {
		return commentMapper.findById(id);
	}

	@Override
	public void reply(Comment comment) {
		
		//初始化
		comment.setCommentTime(new Date().getTime());
		comment.setStatus(StatusNo.STATUS_UNCHECKE);
		
		commentMapper.insert(comment);
	}

	@Override
	public void examine(Comment comment, boolean isPass) {
		
		if(isPass) {
			comment.setStatus(StatusNo.STATUS_CHECKE_PASS);
		} else {
			comment.setStatus(StatusNo.STATUS_CHECKE_NOPASS);
		}
		
		commentMapper.updateByPrimaryKey(comment);
	}

}
