package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Privilege;
import com.briup.service.IPrivalegeService;
import com.briup.util.Message;
import com.briup.util.MessageUtil;
import com.briup.vm.PrivilegeTree;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "权限管理")
@RestController
@RequestMapping("/privilege")
public class PriviligeController {

	@Autowired
	private IPrivalegeService privalegeService;

	@ApiOperation("查询所有")
	@GetMapping("/findAll")
	public Message findAll() {
		List<Privilege> list = privalegeService.findAll();
		return MessageUtil.success("查询完成", list);
	}

	@ApiOperation(value = "通过parentId查询")
	@GetMapping(value = "/findByParentId")
	public Message findByParentId(Long id) {
		List<Privilege> list = privalegeService.findByParentId(id);
		return MessageUtil.success("查询完成",list);
	}

	@ApiOperation(value = "保存或更新")
	@PostMapping(value = "/saveOrUpdate")
	public Message saveOrUpdate(Privilege privilege) {
		privalegeService.saveOrUpdate(privilege);
		return MessageUtil.success("更新成功");
	}

	@ApiOperation(value = "查询树")
	@GetMapping(value = "/findPrivilegeTree")
	public Message findPrivilegeTree() {
		List<PrivilegeTree> list = privalegeService.findPrivilegeTree();
		return MessageUtil.success("查询成功",list);
	}
	
	@ApiOperation(value = "根据ID删除")
	@GetMapping("/deleteById")
	public Message deleteById(Long id) {
		privalegeService.removeById(id);
		return MessageUtil.success("删除成功");
	}

}
