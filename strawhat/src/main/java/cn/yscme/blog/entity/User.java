package cn.yscme.blog.entity;

import java.io.Serializable;

import cn.yscme.blog.enums.UserRoleType;

/**
 * @author ysc
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 5582341427613414366L;
	private Long id;
	private String username;
	private String password;
	private UserRoleType type;
	private String about;
	private Integer state;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
