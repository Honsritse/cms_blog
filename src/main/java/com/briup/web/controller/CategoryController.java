package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Category;
import com.briup.service.ICategoryService;
import com.briup.util.Message;
import com.briup.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
@Api(tags = "栏目管理")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@ApiOperation("展示全部")
	@GetMapping("/findAll")
	public Message findAll() {
		List<Category> categories = categoryService.findAll();
		return MessageUtil.success("完成检索", categories);
	}

	@ApiOperation("新增栏目")
	@GetMapping("/insert")
	public Message insert(Category category) {
		categoryService.saveOrUpdate(category);
		return MessageUtil.success("提交成功");
	}

	@ApiOperation("移除栏目")
	@GetMapping("/remove")
	public Message remove(Long id) {
		categoryService.deleteById(id);
		return MessageUtil.success("移除成功");
	}

	@ApiOperation("批量删除")
	@PostMapping("/removeBatch")
	public Message removeBatch(@RequestBody long[] ids) {
//		System.out.println("Debug: ids="+ids);
		categoryService.deleteBatch(ids);
		return MessageUtil.success("移除成功");
	}

//	@ApiOperation("清空栏目")
//	@GetMapping("/clear")
//	public Message clear() {
//		categoryService.deleteAll();
//		return MessageUtil.success("清空成功");
//	}

	@ApiOperation(value = "更新栏目", notes = "保存时无需ID,更新时传递ID")
	@PostMapping("/saveOrUpdate")
	public Message update(Category category) {
		categoryService.saveOrUpdate(category);
		return MessageUtil.success("更新成功");
	}

//	@ApiOperation("检索栏目")
//	@GetMapping("/findById")
//	public Message findById(long id) {
//		Category category = categoryService.findById(id);
//		return MessageUtil.success("检索完成", category);
//	}

//	@ApiOperation("级联检索栏目")
//	@GetMapping("/findByIdCascade")
//	public Message findByIdCascade(long id) {
//		CategoryExtend categoryExtend = categoryService.findByIdCascade(id);
//		return MessageUtil.success("检索成功", categoryExtend);
//	}

}
