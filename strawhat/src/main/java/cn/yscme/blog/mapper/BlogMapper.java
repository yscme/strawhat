/**
 * 
 */
package cn.yscme.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.yscme.blog.entity.Blog;
import cn.yscme.blog.entity.User;

/**
 * @author ysc
 *
 */
@Mapper
public interface BlogMapper {
	//发布
	@Insert("insert blog(userId,title,label,text,content,time,state) values(#{user.id},#{title},#{label},#{text},#{content},#{time},#{state})")
	int insert(Blog blog);
	//获取用户博客列表、
	@Select("select * from blog where userId=#{userId} and state=#{state}")
	@ResultMap("blog")
	List<Blog> list(@Param("userId") Long userId,@Param("state") Integer state);
	//获取所有博客
	@Select("select * from blog where state=#{state}")
	@Results(id = "blog",value = {
		@Result(column = "userId",property = "user",javaType = User.class,one = @One(select = "cn.yscme.blog.mapper.UserMapper.findById"))
	})
	List<Blog> listAll(Integer state);
	//删除博客
	@Delete("delete from blog where id=#{id}")
	int delete(Long id);
	//删除多个
	@Delete("<script>delete from blog where id in <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
	int deleteAll(Long[] ids);
	//更新状态
	@Update("<script>update blog set state=#{state} where id in <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
	int setStateAll(@Param("state") Integer state,@Param("ids") Long[] ids);
}
