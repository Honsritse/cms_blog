package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Comment;
import com.briup.bean.extend.CommentExtend;
import com.briup.service.ICommentService;
import com.briup.util.Message;
import com.briup.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "评论管理")
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@ApiOperation("检索全部")
	@GetMapping("/findAll")
	public Message findAll() {
		List<Comment> comments = commentService.findAll();
		return MessageUtil.success("检索完成", comments);
	}

	@ApiOperation("级连检索全部")
	@GetMapping("/findAllByCascade")
	public Message findAllcascade() {
		List<CommentExtend> comments = commentService.findAllWithReply();
		return MessageUtil.success("检索完成", comments);
	}

	@ApiOperation("ID检索评论")
	@GetMapping("/findByid")
	public Message findById(Long id) {
		CommentExtend comment = commentService.findById(id);
		return MessageUtil.success("检索完成", comment);
	}

	@ApiOperation("回复评论")
	@GetMapping("/reply")
	public Message reply(@ApiParam(value = "编号") @RequestParam("id") Long id,
			@ApiParam(value = "内容") @RequestParam("content") String content,
			@ApiParam(value = "文章") @RequestParam("articleId") Long articleId,
			@ApiParam(value = "用户") @RequestParam("userId") Long userId,
			@ApiParam(value = "回复", allowEmptyValue = true) @RequestParam(value = "parentId", required = false) Long parentId) {

		Comment comment = new Comment();
		comment.setId(id);
		comment.setContent(content);
		comment.setArticleId(articleId);
		comment.setUserId(userId);
		comment.setParentId(parentId);

		commentService.reply(comment);

		return MessageUtil.success("回复成功");
	}
	
	@ApiOperation("审核评论")
	@PostMapping("/examine")
	public Message examine(Long id, boolean status) {
		
		Comment comment = commentService.findById(id);
		commentService.examine(comment, status);
		return MessageUtil.success("审核完成");
	}

}
