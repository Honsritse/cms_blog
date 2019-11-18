package com.briup.service;

import java.util.List;

import com.briup.bean.Category;
import com.briup.exception.CustomException;

public interface ICategoryService {

//	void deleteAll();

	void deleteById(long id) throws CustomException;

	void deleteBatch(long[] ids) throws CustomException;

	void saveOrUpdate(Category category) throws CustomException;

//	Category findById(long id) throws CustomException;

	List<Category> findAll();

//	CategoryExtend findByIdCascade(long id);

}
