package com.briup.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.User;
import com.briup.bean.extend.UserExtend;
import com.briup.service.IUserService;
import com.briup.util.JwtTokenUtil;
import com.briup.util.Message;
import com.briup.util.MessageUtil;
import com.briup.vm.UserVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户登录管理")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	  @ApiOperation(value = "通过token获取用户的基本信息")
	    @GetMapping("/info")
	    public Message info(String token){
	        // 1. 通过token获取用户信息  {id,use,gender,roles:[]}
	        long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
	        UserExtend baseUserExtend = userService.findById(id);
	        return MessageUtil.success("检索完成",baseUserExtend);
	    }

	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public Message login(@RequestBody UserVM userVM) {
		// 1. 认证用户的用户名和密码
        User user = userService.login(userVM);
        // 2. 如果登录成功产生token,将token缓存起来，返回
        String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
		return MessageUtil.success("认证成功", map);
		// 3. 如果登录失败抛出异常
	}

	@ApiOperation(value = "用户登出")
    @PostMapping("logout")
    public Message logout(){
        return MessageUtil.success("退出成功");
    }
	
	
	
}
