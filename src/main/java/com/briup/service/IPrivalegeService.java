package com.briup.service;

import java.util.List;

import com.briup.bean.Privilege;
import com.briup.exception.CustomException;
import com.briup.vm.PrivilegeTree;

/**
 * 
 * @ClassName: IPrivallegeService
 * @Description: 查询所有权限
 * @author y2312
 * @date Nov 18, 2019
 *
 */
public interface IPrivalegeService {
	List<Privilege> findAll();

	List<Privilege> findByParentId(Long parentId);

	void saveOrUpdate(Privilege privilege) throws CustomException;

	List<PrivilegeTree> findPrivilegeTree();
}
