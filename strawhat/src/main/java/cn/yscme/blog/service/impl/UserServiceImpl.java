package cn.yscme.blog.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.yscme.blog.entity.User;
import cn.yscme.blog.enums.UserRoleType;
import cn.yscme.blog.mapper.UserMapper;
import cn.yscme.blog.service.UserService;
import cn.yscme.blog.util.JsonUtil;
import cn.yscme.blog.util.LogUtil;
import cn.yscme.blog.util.TimeUtil;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(String username, String password){	
		Map<String, Object> map=new HashMap<>();
		map.put("注册时间",TimeUtil.getTime(4));
		map.put("用户等级", 1);
		map.put("用户经验", 1000);
		map.put("个性标签", "还没有设置标签");
		int i=userMapper.insertUser(username, new BCryptPasswordEncoder().encode(password),UserRoleType.ROLE_USER,JsonUtil.objToJson(map),1);			
		Long count=userMapper.countUsername(username);
		System.out.println(count);
		if(count>1) {
			try {
				throw new Exception("用户名已存在! 发生回滚");					
			} catch (Exception e) {
				i=-1;
				LogUtil.warn(this.getClass(),e.getMessage());
				System.err.println(e.getMessage());
				//手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}			
		return i;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user=userMapper.login(username, password);
		return user;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return userMapper.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
