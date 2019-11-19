package com.briup.service;

import java.util.List;

import com.briup.bean.Role;
import com.briup.bean.extend.RoleExtend;
import com.briup.exception.CustomException;

/**
 * 
 * @ClassName: IRoleService
 * @Description: 角色管理
 * @author y2312
 * @date Nov 18, 2019
 *
 */

public interface IRoleService {

	void authorization(long roleId, List<Long> privilegeIds);

	List<Role> findAll();

	List<RoleExtend> cascadePrivilegeFindAll();

	void saveOrUpdate(Role Role) throws CustomException;

	void deleteById(long id) throws CustomException;

}
