package com.briup.bean.extend;

import java.util.List;

import com.briup.bean.Comment;

public class CommentExtend extends Comment{

	private List<CommentExtend> replyComments;

	public List<CommentExtend> getReplyComments() {
		return replyComments;
	}

	public void setReplayComments(List<CommentExtend> replyComments) {
		this.replyComments = replyComments;
	}
	
}
