package cn.yscme.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ysc
 * 页面控制器
 */
@Controller
public class HomeController {
	//首页
	@RequestMapping({"/","index"})
	public String index() {
		
		return "index";
	}
	//登录页
	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}
	//注册页
	@RequestMapping("register")
	public String register() {
		return "register";
	}
}
