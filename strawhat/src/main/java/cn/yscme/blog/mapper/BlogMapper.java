/**
 * 
 */
package cn.yscme.blog.mapper;

import java.util.List;
import java.util.Set;

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
	//id获取内容
	@Select("select * from blog where id=#{id}")
	Blog findById(Long id);
	//用户id获取所有博客
	@Select("select * from blog where userId=#{userId}")
	Set<Blog> findByUserId(Long userId);
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
	@Delete("delete from blog where userId=#{userId} and id=#{id}")
	int delete(@Param("userId")Long userId,Long id);
	//删除多个
	@Delete("<script>delete from blog where userId=#{userId} and id in <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
	int deleteAll(@Param("userId")Long userId,Long[] ids);
	//更新状态
	@Update("<script>update blog set state=#{state} where userId=#{userId} and id in <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
	int setStateAll(@Param("userId") Long userId,@Param("state") Integer state,@Param("ids") Long[] ids);
	//更新内容
	@Update("update blog set title=#{title},label=#{label},text=#{text},content=#{content} where userId=#{user.id} and id=#{id}")
	int update(Blog blog);
}
