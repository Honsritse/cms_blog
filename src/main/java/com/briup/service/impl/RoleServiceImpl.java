package com.briup.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.bean.RoleExample;
import com.briup.bean.RolePrivalege;
import com.briup.bean.RolePrivalegeExample;
import com.briup.bean.extend.RoleExtend;
import com.briup.dao.RolePrivalegeMapper;
import com.briup.dao.extend.RoleExtendMapper;
import com.briup.exception.CustomException;
import com.briup.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleExtendMapper roleMapper;
	@Resource
	private RolePrivalegeMapper rolePrivalegeMapper;

	@Override
	public void authorization(long roleId, List<Long> privilegeIds) {
		RolePrivalegeExample example = new RolePrivalegeExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePrivalege> rolePrivaleges = rolePrivalegeMapper.selectByExample(example);
		List<Long> exist_privilegeIds = new ArrayList<>();
		rolePrivaleges.forEach(t->{
			exist_privilegeIds.add(t.getPrivilegeId());
		});
				
        // 依次判断privilegeIds 是否存在old_privilegeIds，如果不在则插入
		// 即 添加原来未有的权限
		for(long privilegeId : privilegeIds){
            if (!exist_privilegeIds.contains(privilegeId)) {
                RolePrivalege rp = new RolePrivalege();
                rp.setRoleId(roleId);
                rp.setPrivilegeId(privilegeId);
                rolePrivalegeMapper.insert(rp);
            }
        }
        // 依次判断 是否存在exist_privilegeIds 是否存在privilegeIds，如果不存在删除
		// 即 删除现在不要的权限
        for(long privilegeId: exist_privilegeIds){
            if(!privilegeIds.contains(privilegeId)){
                // 根据privilegeId 从桥表中删除
                example.clear();
                example.createCriteria()
                        .andRoleIdEqualTo(roleId)
                        .andPrivilegeIdEqualTo(privilegeId);
                rolePrivalegeMapper.deleteByExample(example);
            }
        }
		
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.selectByExample(new RoleExample());
	}

	@Override
	public List<RoleExtend> cascadePrivilegeFindAll() {
		return roleMapper.selectAll();
	}

	@Override
	public void saveOrUpdate(Role Role) throws CustomException {
		if (Role.getId() != null) {
			roleMapper.updateByPrimaryKey(Role);
		} else {
			roleMapper.insert(Role);
		}
	}

	@Override
	public void deleteById(long id) throws CustomException {
		Role role = roleMapper.selectByPrimaryKey(id);
		if (role == null) {
			throw new CustomException("要删除的角色不存在");
		}
		roleMapper.deleteByPrimaryKey(id);
	}

}
