package com.core.plus.login.service;

public interface LoginService {
	public int searchUser(Object map);
	
	
	//로그인처리
		String doLogin(String user_id, String pswd);
}
