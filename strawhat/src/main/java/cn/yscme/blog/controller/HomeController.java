package cn.yscme.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.yscme.blog.service.BlogService;

/**
 * @author ysc
 * 页面控制器
 */
@Controller
public class HomeController {
	@Autowired
	private BlogService blogService;
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
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	//vue路由页
	@RequestMapping({"/app","/app/path/**"})
	public String vueRouter() {
		return "redirect:/";
	}
	//博客详细页
	@RequestMapping("/app/blog/{id}")
	public ModelAndView getBlog(@PathVariable Long id) {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("blog");
        mv.addObject("blog", blogService.getBlogById(id));
		return mv;
	}
	
}
