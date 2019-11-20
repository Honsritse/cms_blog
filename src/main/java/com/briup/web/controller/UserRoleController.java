package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.User;
import com.briup.bean.extend.UserExtend;
import com.briup.service.IUserService;
import com.briup.util.Message;
import com.briup.util.MessageUtil;
import com.briup.vm.UserRoleVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@Validated
@RestController
@RequestMapping("/baseUser")
public class UserRoleController {

	@Autowired
	private IUserService userService;
	
	@ApiOperation("查询所有")
	@GetMapping("/findAll")
	public Message findAll() {
		List<User> users = userService.findAll();
		return MessageUtil.success("查询完成",users);
	}

	@ApiOperation("级连查询所有")
	@GetMapping("/findAllCascade")
	public Message findAllCascade() {
		List<UserExtend> users = userService.cascadeRoleFindAll();
		return MessageUtil.success("查询完成",users);
	}
	
	@ApiOperation("保存或更新")
	@PostMapping("/saveOrUpdate")
	public Message saveOrUpdate(User user) {
		userService.saveOrUpdate(user);
		return MessageUtil.success("更新完成");
	}

	@ApiOperation("通过ID删除用户")
	@GetMapping("/deleteById")
	public Message deleteById(Long id) {
		userService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation("设置用户角色")
	@PostMapping("/setRoles")
	public Message setRoles(UserRoleVM userRole) {
		userService.setRoles(userRole.getId(),userRole.getRoles());
		return MessageUtil.success("设置完成");
	}
	
	
}
