package com.briup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.bean.Category;
import com.briup.bean.CategoryExample;
import com.briup.dao.extend.CategoryExtendMapper;
import com.briup.exception.CustomException;
import com.briup.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Resource
	private CategoryExtendMapper categoryEMapper;

	/*
	 * @Override public void deleteAll() { categoryEMapper.deleteByExample(new
	 * CategoryExample()); }
	 */

	@Override
	public void deleteById(long id) throws CustomException{
		if(categoryEMapper.selectByPrimaryKey(id) != null) {
			categoryEMapper.deleteByPrimaryKey(id);
		}else {
			throw new CustomException("没有该栏目:"+id);
		}
	}

	@Override
	public void deleteBatch(long[] ids) throws CustomException {
		for (long id : ids) {
			this.deleteById(id);
		}

	}

	@Override
	public void saveOrUpdate(Category category) throws CustomException {

		if (categoryEMapper.selectByPrimaryKey(category.getId()) != null) {
			categoryEMapper.updateByPrimaryKey(category);
		} else {
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categoryEMapper.selectByExample(categoryExample);
			if (list.size() > 0) {
				throw new CustomException("栏目名不可重复");
			}

			categoryEMapper.insert(category);
		}

	}

	/*
	 * @Override public Category findById(long id) throws CustomException { Category
	 * category = categoryEMapper.selectByPrimaryKey(id); if (category != null) {
	 * return category; } throw new CustomException("ID不存在"); }
	 */

	/*
	 * @Override public CategoryExtend findByIdCascade(long id) { return
	 * categoryEMapper.findByIdCascade(id); }
	 */


	@Override
	public List<Category> findAll() {
		List<Category> categories = categoryEMapper.selectByExample(new CategoryExample());
		return categories;
	}

}
