package com.briup.service;

import java.util.List;

import com.briup.bean.Article;
import com.briup.bean.extend.ArticleExtend;
import com.briup.exception.CustomException;

public interface IArticleService {

	List<ArticleExtend> findAll();
	
	ArticleExtend findById(long id);
	
	void saveOrUpdate(Article article);

	void deleteById(long id) throws CustomException;
}
