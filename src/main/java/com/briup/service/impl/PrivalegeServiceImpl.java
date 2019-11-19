package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Privilege;
import com.briup.bean.PrivilegeExample;
import com.briup.dao.extend.PrivilegeExtendMapper;
import com.briup.exception.CustomException;
import com.briup.service.IPrivalegeService;
import com.briup.vm.PrivilegeTree;

@Service
public class PrivalegeServiceImpl implements IPrivalegeService {

	@Autowired
	private PrivilegeExtendMapper privalegeMapper;

	@Override
	public List<Privilege> findAll() {
		return privalegeMapper.selectByExample(new PrivilegeExample());
	}

	@Override
	public List<Privilege> findByParentId(Long parentId) {
		PrivilegeExample example = new PrivilegeExample();
		if (parentId == null) {
			example.createCriteria().andParentIdIsNull();
		} else {
			example.createCriteria().andParentIdEqualTo(parentId);
		}
		return privalegeMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Privilege privilege) throws CustomException {
		if (privilege.getId() != null) {
			privalegeMapper.updateByPrimaryKey(privilege);
		} else {
			privalegeMapper.insert(privilege);
		}
	}

	@Override
	public List<PrivilegeTree> findPrivilegeTree() {
		return privalegeMapper.selectAll();
	}

}
