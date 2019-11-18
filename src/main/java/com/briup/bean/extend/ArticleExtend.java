package com.briup.bean.extend;

import java.util.List;

import com.briup.bean.Article;
import com.briup.bean.Category;
import com.briup.bean.Comment;

/**
 * @program: cms_jd1908
 * @description: 文章拓展类
 * @author: charles
 * @create: 2019-11-12 15:06
 **/

public class ArticleExtend extends Article {
	
	private Category category; 
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
