package com.briup.bean.extend;

import java.util.List;

import com.briup.bean.Category;

public class CategoryExtend extends Category {

	private List<Category> childCategorys;

	public List<Category> getChildCategorys() {
		return childCategorys;
	}

	public void setChildCategorys(List<Category> childCategorys) {
		this.childCategorys = childCategorys;
	}
}
