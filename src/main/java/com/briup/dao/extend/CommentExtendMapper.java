package com.briup.dao.extend;
	
import java.util.List;

import com.briup.bean.extend.CommentExtend;
import com.briup.dao.CommentMapper;

public interface CommentExtendMapper extends CommentMapper{

	List<CommentExtend> findAllCascade();
	
	CommentExtend findById(long id);
	
}
