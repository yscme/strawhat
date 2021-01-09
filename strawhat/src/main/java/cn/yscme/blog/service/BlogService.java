package cn.yscme.blog.service;

import java.util.List;

import cn.yscme.blog.entity.Blog;

public interface BlogService {
	//发布
	int insert(Blog blog, boolean state);
	//获取用户博客
	List<Blog> list(int state);
	//获取所有博客
	List<Blog> listAll();
	//删除博客
	int delete(Long id);
	//删除多个
	int deleteAll(Long[] ids);
	//更新状态
	int setStateAll(Integer state,Long[] ids);
}
