package com.briup.dao.extend;

import java.util.List;

import com.briup.bean.Role;
import com.briup.bean.extend.RoleExtend;
import com.briup.dao.RoleMapper;

public interface RoleExtendMapper extends RoleMapper{

    List<Role> selectByUserId(long id);

    List<RoleExtend> selectAll();
	
}
