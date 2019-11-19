package com.briup.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Role;
import com.briup.bean.extend.RoleExtend;
import com.briup.service.IRoleService;
import com.briup.util.Message;
import com.briup.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags ="角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@ApiOperation("查询所有")
	@GetMapping("/findAll")
	public Message findAll() {
		List<Role> list = roleService.findAll();
		return MessageUtil.success("查询完成",list);
	}
	
	@ApiOperation("级连查询所有")
	@GetMapping("/findAllCascade")
	public Message findAllCascade() {
		List<RoleExtend> list = roleService.cascadePrivilegeFindAll();
		return MessageUtil.success("查询完成",list);
	}
	
	@ApiOperation("以ID删除角色")
	@GetMapping("/deleteById")
	public Message deleteById(Long id){
		roleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation("新增或更新角色")
	@PostMapping("/saveOrUpdate")
	public Message saveOrUpdate(Role role){
		roleService.saveOrUpdate(role);
		return MessageUtil.success("更新完成");
	}
	
	@ApiOperation(value = "为角色授权")
	@PostMapping("/authorization")
	public Message authorization(long id,Long[] privileges) {
		List<Long> list = Arrays.asList(privileges);
		roleService.authorization(id, list);
		return MessageUtil.success("授权完成");
	}
	
}
