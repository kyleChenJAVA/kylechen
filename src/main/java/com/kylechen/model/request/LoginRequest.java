package com.kylechen.model.request;

/**
 * @author chenzhiwei
 * LoginRequest.java
 *	2018年8月1日
 *登陆请求
 */
public class LoginRequest {
	private String username;
	private String userpass;
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", userpass=" + userpass + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	
}