package cn.yscme.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yscme.blog.entity.Blog;
import cn.yscme.blog.entity.Result;
import cn.yscme.blog.service.BlogService;
import cn.yscme.blog.util.LogUtil;
import cn.yscme.blog.util.StringUtil;

@RestController
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@PostMapping("/insert")
	public Result insert(@RequestBody Blog blog) {
		if(StringUtil.isNull(blog.getTitle())||StringUtil.isNull(blog.getContent())||StringUtil.isNull(blog.getLabel())) return new Result(false, "标题&内容 不能为空!");
		if(blogService.insert(blog,true)==1) {
			LogUtil.info(this.getClass(),blog.getUser().getUsername()+" 发布博客 "+blog.getId());
			return new Result(true, "博客发布成功");
		}
		LogUtil.warn(this.getClass(),blog.getUser().getUsername()+" 发布博客失败 "+blog.getId());
		return new Result(false, "博客发布失败");
	}
	//
	@PostMapping("/insertbak")
	public Result insertbak(@RequestBody Blog blog) {
		if(StringUtil.isNull(blog.getTitle())||StringUtil.isNull(blog.getContent())||StringUtil.isNull(blog.getLabel())) return new Result(false, "标题&内容&标签 不能为空!");
		if(blogService.insert(blog,false)==1) {
			LogUtil.info(this.getClass(),blog.getUser().getUsername()+" 保存到草稿 "+blog.getId());
			return new Result(true, "保存到草稿成功");
		}
		LogUtil.warn(this.getClass(),blog.getUser().getUsername()+" 保存到草稿失败 "+blog.getId());
		return new Result(false, "保存到草稿失败");
	}
	//删除一个
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable Long id) {
		if(blogService.delete(id)==1) {
			LogUtil.info(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客删除成功 "+id);
			return new Result(true, "博客删除成功");
		}
		LogUtil.warn(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客删除失败 "+id);
		return new Result(false, "博客删除失败");
	}
	//删除多个
	@DeleteMapping("/deleteall/{ids}")
	public Result deleteAll(@PathVariable Long[] ids) {
		if(blogService.deleteAll(ids)>0) {
			LogUtil.info(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客删除成功 "+ids);
			return new Result(true, "博客删除成功");
		}
		LogUtil.warn(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客删除失败 "+ids);
		return new Result(false, "博客删除失败");
	}
	//更新状态
	@PutMapping("/setState/{state}/{ids}")
	public Result setState(@PathVariable Integer state,@PathVariable Long[] ids) {
		if(blogService.setStateAll(state,ids)>0) {
			LogUtil.info(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客移动成功 "+ids);
			return new Result(true, "博客移动成功");
		}
		LogUtil.warn(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客移动失败 "+ids);
		return new Result(false, "博客移动失败");
	}
	//根据状态获取个人博客列表
	@GetMapping("/list/{state}")
	public List<Blog> list(@PathVariable Integer state){
		return blogService.list(state);
	}
	//获取所有状态为1的博客
	@GetMapping("/listall")
	public List<Blog> listAll(){
		return blogService.listAll();
	}
	//更新博客内容
	@PutMapping("/update")
	public Result update(String title,String label,String text,String content,Long id) {
		if(id==null) return new Result(false, "博客未选中");
		if(StringUtil.isNull(title)||StringUtil.isNull(content)||StringUtil.isNull(label)) return new Result(false, "标题&内容 不能为空!");
		Blog blog=new Blog();
		blog.setId(id);
		blog.setText(text);
		blog.setContent(content);
		blog.setTitle(title);
		blog.setLabel(label);
		if(blogService.update(blog)==1) {
			LogUtil.info(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客更新成功 "+id);
			return new Result(true, "博客更新成功");
		}
		LogUtil.warn(this.getClass(),SecurityContextHolder.getContext().getAuthentication().getName()+" 博客更新失败 "+id);
		return new Result(false, "博客更新失败");
	}
}
