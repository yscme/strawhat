/**
 * 
 */
package cn.yscme.blog.entity;

import java.io.Serializable;

/**
 * @author ysc
 *
 */
public class Result implements Serializable{
	private static final long serialVersionUID = 7489187519874273494L;
	private boolean state;
	private Object data;
	private String msg;
	public Result(boolean state, Object data, String msg) {
		super();
		this.state = state;
		this.data = data;
		this.msg = msg;
	}
	public Result(boolean state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
