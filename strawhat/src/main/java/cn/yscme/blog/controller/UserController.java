package cn.yscme.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yscme.blog.entity.Result;
import cn.yscme.blog.entity.User;
import cn.yscme.blog.service.UserService;
import cn.yscme.blog.util.LogUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	/**注册
	 * @param map
	 * @return
	 */
	@PostMapping("/insert")
	public Result insert(@RequestBody Map<String, String> map) {
		if(!map.containsKey("username")||!map.containsKey("password")||map.get("username").equals("")||map.get("password").equals("")) return new Result(false,"参数不全!");
		int i=userService.insert(map.get("username"), map.get("password"));
		if(i==1) {
			LogUtil.info(this.getClass(), "注册成功："+map.get("username"));
			return new Result(true, "注册成功!");
		}else if(i==-1) {
			LogUtil.info(this.getClass(), "用户已存在："+map.get("username"));
			return new Result(false,"用户已存在!");			
		}
		LogUtil.info(this.getClass(), "注册失败："+map.get("username"));
		return new Result(false, "注册失败!");
	}
	/**获取登录用户信息
	 * @return
	 */
	@GetMapping("/getuser")
	public User getUser(){
		return userService.getUser();
	}
}
