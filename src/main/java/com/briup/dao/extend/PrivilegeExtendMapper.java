package com.briup.dao.extend;

import java.util.List;

import com.briup.bean.Privilege;
import com.briup.dao.PrivilegeMapper;
import com.briup.vm.PrivilegeTree;

public interface PrivilegeExtendMapper extends PrivilegeMapper{

    List<PrivilegeTree> selectAll();

    List<Privilege> selectByParentId(long id);

    List<Privilege> selectByRoleId(long id);
	
}
