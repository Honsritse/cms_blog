package com.briup.dao.extend;

import java.util.List;

import com.briup.bean.extend.UserExtend;
import com.briup.dao.UserMapper;

public interface UserExtendMapper extends UserMapper {

	UserExtend selectById(long id);

	List<UserExtend> selectAll();
}
