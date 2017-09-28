package com.core.plus.login.dao;

import java.util.Map;

import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.vo.LoginVO;

public interface LoginDAO {
	
	public int selectOne(String root, Object obj);
	
	// 사용자 비밀번호 오류 횟수 설정
	void updatePwdErnmYn(LoginVO loginParam);

	//사용자 정보조회
	LoginVO selectUser(LoginVO loginParam);
	
	MenuVo getMenuAuthInfo(Map<String, String> menuAuthMap);

}
