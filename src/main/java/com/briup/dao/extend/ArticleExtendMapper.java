package com.briup.dao.extend;

import java.util.List;

import com.briup.bean.Article;
import com.briup.bean.extend.ArticleExtend;
import com.briup.dao.ArticleMapper;

public interface ArticleExtendMapper extends ArticleMapper{
	List<ArticleExtend> selectAll();
	
	Article selectById(long id);
	
	ArticleExtend selectByIdDescade(long id);
}
