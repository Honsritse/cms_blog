package com.briup.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.ArticleExample;
import com.briup.bean.extend.ArticleExtend;
import com.briup.dao.extend.ArticleExtendMapper;
import com.briup.exception.CustomException;
import com.briup.service.IArticleService;
import com.briup.util.StatusNo;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleExtendMapper articleEMapper;

	@Override
	public List<ArticleExtend> findAll() {

		return articleEMapper.selectAll();
	}

	@Override
	public ArticleExtend findById(long id) {
		return articleEMapper.selectByIdDescade(id);
	}

	@Override
	public void saveOrUpdate(Article article) throws CustomException {
		Long id = article.getId();
		if (id != null && articleEMapper.selectById(id) != null) {
			articleEMapper.updateByPrimaryKey(article);
		} else if (id != null && articleEMapper.selectById(id) == null) {
			ArticleExample articleExample = new ArticleExample();
			articleExample.createCriteria().andTitleEqualTo(article.getTitle());
			List<Article> list = articleEMapper.selectByExample(articleExample);
			
			if (list.size() > 0) {
				throw new CustomException("标题重复使用");
			}

			// 初始化
			article.setStatus(StatusNo.STATUS_UNCHECKE);
			article.setThumpUp(0L);
			article.setThumpDown(0L);
			article.setPublishTime(new Date().getTime());
			article.setReadTimes(0L);
			articleEMapper.insert(article);
		}
	}

	@Override
	public void deleteById(long id) throws CustomException{
		if(articleEMapper.selectById(id) == null) {
			throw new CustomException("对应文章不存在");
		}
		articleEMapper.deleteByPrimaryKey(id);
	}

}
