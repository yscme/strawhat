package cn.yscme.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cn.yscme.blog.entity.Blog;
import cn.yscme.blog.entity.User;
import cn.yscme.blog.mapper.BlogMapper;
import cn.yscme.blog.mapper.UserMapper;
import cn.yscme.blog.service.BlogService;
import cn.yscme.blog.util.TimeUtil;

@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public int insert(Blog blog,boolean state) {
		if(state) {
			blog.setState(1);
			blog.setTime(TimeUtil.getTime(4));
			blog.setUser(userMapper.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}else{
			blog.setState(0);
			blog.setTime(TimeUtil.getTime(4));
			blog.setUser(userMapper.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		return blogMapper.insert(blog);
	}

	@Override
	public List<Blog> list(int state){
		return blogMapper.list(userMapper.getIdIsName(SecurityContextHolder.getContext().getAuthentication().getName()),state);
	}

	@Override
	public List<Blog> listAll() {
		// TODO Auto-generated method stub
		return blogMapper.listAll(1);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return blogMapper.delete(userMapper.getIdIsName(SecurityContextHolder.getContext().getAuthentication().getName()),id);
	}

	@Override
	public int deleteAll(Long[] ids) {
		// TODO Auto-generated method stub
		return blogMapper.deleteAll(userMapper.getIdIsName(SecurityContextHolder.getContext().getAuthentication().getName()),ids);
	}

	@Override
	public int setStateAll(Integer state, Long[] ids) {
		// TODO Auto-generated method stub
		return blogMapper.setStateAll(userMapper.getIdIsName(SecurityContextHolder.getContext().getAuthentication().getName()),state, ids);
	}

	@Override
	public int update(Blog blog) {
		User user=new User();
		user.setId(userMapper.getIdIsName(SecurityContextHolder.getContext().getAuthentication().getName()));
		blog.setUser(user);
		// TODO Auto-generated method stub
		return blogMapper.update(blog);
	}
	
}
