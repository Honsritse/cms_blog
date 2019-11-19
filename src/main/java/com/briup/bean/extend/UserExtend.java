package com.briup.bean.extend;

import java.util.List;

import com.briup.bean.Role;
import com.briup.bean.User;

/**
 * 
    * @ClassName: UserExtend
    * @Description: 用户拓展
    * @author y2312
    * @date Nov 18, 2019
    *
 */


public class UserExtend extends User{

    private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
