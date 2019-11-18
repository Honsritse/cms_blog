package com.briup.dao.extend;

import com.briup.bean.extend.CategoryExtend;
import com.briup.dao.CategoryMapper;

public interface CategoryExtendMapper extends CategoryMapper{

	CategoryExtend findByIdCascade(long id);
	
}
