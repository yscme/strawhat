package cn.yscme.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cn.yscme.blog.entity.User;
import cn.yscme.blog.enums.UserRoleType;
import cn.yscme.blog.mapper.UserMapper;
import cn.yscme.blog.service.UserService;
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
	public int insert(String username, String password) {
		Long count=userMapper.countUsername(username);
		if(count==0) {		
			return userMapper.insertUser(username, new BCryptPasswordEncoder().encode(password),UserRoleType.ROLE_USER,"注册时间@"+TimeUtil.getTime(4)+"[$]",1);			
		} else if(count==1) {
			return -1;
		}
		return 0;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user=userMapper.login(username, password);
		return user;
	}
}
