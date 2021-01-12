package cn.yscme.blog.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.yscme.blog.enums.UserRoleType;

/**
 * @author ysc
 *
 */
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable{

	private static final long serialVersionUID = -8023503235494604542L;
	private Long id;
	private String username;
	private String password;
	private UserRoleType type;
	private String about;
	private Integer state;
	private Set<Blog> blogs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRoleType getType() {
		return type;
	}
	public void setType(UserRoleType type) {
		this.type = type;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Set<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
