package com.briup.service;

import java.util.List;

import com.briup.bean.User;
import com.briup.bean.extend.UserExtend;
import com.briup.exception.CustomException;
import com.briup.vm.UserVM;

/**
 * 
 * @ClassName: IUserService
 * @Description: 用户管理
 * @author y2312
 * @date Nov 18, 2019
 *
 */

public interface IUserService {

	 User login(UserVM userVM) throws CustomException;
	
	UserExtend findById(long id);

	List<User> findAll();

	List<UserExtend> cascadeRoleFindAll();

	void saveOrUpdate(User user) throws CustomException;

	void changeStatus(long id, String status) throws CustomException;

	void deleteById(long id) throws CustomException;

	void setRoles(long id, List<Long> roles);

}
