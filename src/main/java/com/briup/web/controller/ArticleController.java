package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Article;
import com.briup.bean.extend.ArticleExtend;
import com.briup.service.IArticleService;
import com.briup.util.Message;
import com.briup.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@ApiOperation(value = "预览全部")
	@GetMapping("/findAll")
	public Message findAll() {
		List<ArticleExtend> articles = articleService.findAll();
		return MessageUtil.success("检索完成", articles);
	}

	@ApiOperation(value = "检索文章")
	@GetMapping("/findById")
	public Message findByid(long id) {
		ArticleExtend articleExtend = articleService.findById(id);
		return MessageUtil.success("检索完成", articleExtend);
	}

	@ApiOperation(value = "保存或修改")
	@PostMapping("/saveOrUpdate")
	public Message saveOrUpdate(
			@ApiParam(value = "编号") @RequestParam("id") Long id,
			@ApiParam(value = "标题", required = true) @RequestParam("title") String title,
			@ApiParam(value = "内容") @RequestParam("content") String content,
			@ApiParam(value = "源内容") @RequestParam("source") String source,
			@ApiParam(value = "栏目") @RequestParam("categoryId") long categoryId,
			@ApiParam(value = "作者") @RequestParam("authorId") long authorId) {

		Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setContent(content);
		article.setSource(source);
		article.setCategoryId(categoryId);
		article.setAuthorId(authorId);

		articleService.saveOrUpdate(article);
		return MessageUtil.success("更新成功");
	}

	@ApiOperation(value = "删除文章")
	@GetMapping("/remove")
	public Message remove(Long id) {
		articleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}

}
