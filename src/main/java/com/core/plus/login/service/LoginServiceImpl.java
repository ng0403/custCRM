package com.core.plus.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.login.dao.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDAO dao;
	
	@Override
	public int searchUser(Object map) {
		int result = dao.selectOne("login.searchUser", map);
		
		System.out.println("서비스" + result);
		
		return result;
	}
	
}
