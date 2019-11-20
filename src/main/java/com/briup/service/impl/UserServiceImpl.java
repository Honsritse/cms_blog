package com.briup.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.User;
import com.briup.bean.UserExample;
import com.briup.bean.UserRole;
import com.briup.bean.UserRoleExample;
import com.briup.bean.extend.UserExtend;
import com.briup.dao.UserRoleMapper;
import com.briup.dao.extend.UserExtendMapper;
import com.briup.exception.CustomException;
import com.briup.service.IUserService;
import com.briup.util.StatusNo;
import com.briup.vm.UserVM;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserExtendMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public User login(UserVM userVM) throws CustomException {

		System.out.println("DEBUG:" + userVM.getUsername() + ":" + userVM.getPassword());

		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(userVM.getUsername());

		List<User> list = userMapper.selectByExample(example);
		if (list.size() < 1) {
			throw new CustomException("用户不存在");
		}
		User user = list.get(0);

		System.out.println("DEBUG:" + user.getUsername() + ":" + user.getPassword());

		if (!user.getPassword().equals(userVM.getPassword())) {
			throw new CustomException("密码不正确");
		}
		return user;
	}

	@Override
	public UserExtend findById(long id) {
		return userMapper.selectById(id);
	}

	@Override
	public List<User> findAll() {
		return userMapper.selectByExample(new UserExample());
	}

	@Override
	public List<UserExtend> cascadeRoleFindAll() {
		return userMapper.selectAll();
	}

	@Override
	public void saveOrUpdate(User user) throws CustomException {
		if (user.getId() != null) {
			userMapper.updateByPrimaryKey(user);
		} else {
			// 判断用户名是否被占用
			UserExample example = new UserExample();
			example.createCriteria().andUsernameEqualTo(user.getUsername());
			List<User> list = userMapper.selectByExample(example);
			if (list.size() > 0) {
				throw new CustomException("该用户已经被占用");
			}
			// 初始化
			user.setRegisterTime(new Date().getTime());
			user.setStatus(StatusNo.STATUS_NORMAL);
			userMapper.insert(user);
		}

	}

	@Override
	public void changeStatus(long id, String status) throws CustomException {
		User user = userMapper.selectByPrimaryKey(id);
		if (user == null) {
			throw new CustomException("该用户不存在");
		}
		user.setStatus(status);
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteById(long id) throws CustomException {
		User user = userMapper.selectByPrimaryKey(id);
		if (user == null) {
			throw new CustomException("该用户不存在");
		}
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void setRoles(long id, List<Long> roles) {

		// 获取当前用户的所有角色
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);

		List<UserRole> list = userRoleMapper.selectByExample(example);
		List<Long> exist_userRoles = new ArrayList<Long>();

		// 已赋予角色存储在exist_userRole中
		ListIterator<UserRole> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			exist_userRoles.add(listIterator.next().getRoleId());
		}

		// 增加没有且不重合的
		for (Long add_role : roles) {
			if (!exist_userRoles.contains(add_role)) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(add_role);
				userRole.setUserId(id);
				userRoleMapper.insert(userRole);
			}
		}

		// 删除已有且不重合的
		for (UserRole exist_role : list) {
			if (!roles.contains(exist_role.getRoleId())) {
				userRoleMapper.deleteByPrimaryKey(exist_role.getId());
			}
		}
	}

}
