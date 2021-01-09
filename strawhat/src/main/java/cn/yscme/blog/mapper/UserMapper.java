/**
 * 
 */
package cn.yscme.blog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.yscme.blog.entity.User;
import cn.yscme.blog.enums.UserRoleType;

/**
 * @author ysc
 *
 */
@Mapper
public interface UserMapper {
    /**用户名查询
     * @param name
     * @return
     */
    @Select("select id,username,type,about,state from user where username=#{username}")
    User findByUsername(String username);
    /**id查询
     * @param id
     * @return
     */
    @Select("select id,username,type,about,state from user where id=#{id}")
    User findById(int id);
    
    /**用户名获取id
     * @param username
     * @return
     */
    @Select("select id from user where username=#{username}")
    Long getIdIsName(String username);
    
    /**获取用户数量
     * @param username
     * @return
     */
    @Select("select count(*) from user where username=#{username}")
    Long countUsername(String username);
    /**注册
     * @param username
     * @param password
     * @param type
     * @param about
     * @return
     */
    @Insert("insert user(username,password,type,about,state) values(#{username},#{password},#{type},#{about},#{state})")
    int insertUser(@Param(value = "username") String username,
		    		@Param(value = "password") String password,
		    		@Param(value = "type") UserRoleType type,
		    		@Param(value = "about") String about,
		    		@Param(value = "state") Integer state);
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(@Param("username") String username,@Param("password")String password);
}
