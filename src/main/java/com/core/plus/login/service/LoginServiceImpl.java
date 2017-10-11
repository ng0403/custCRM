package com.core.plus.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.common.Commons;
import com.core.plus.login.dao.LoginDAO;
import com.core.plus.login.vo.LoginVO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDAO dao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public int searchUser(Object map) {
		int result = dao.selectOne("login.searchUser", map);
 		
		return result;
	}
	 
	//로그인처리
	@Override
	public String doLogin(String user_id, String pswd) {
 		String result = null;
 		// 로그인한 사용자 정보를 가져온다
		LoginVO userInfo = null;
		userInfo = getUserInfo(user_id);
 		if(userInfo != null){
			// 비밀번호 오류횟수
			int pwdErnm = userInfo.getPwd_err_cnt();
			
			// 비밀번호 오류횟수 확인
			if (pwdErnm < 3) {
				
				String pswdno = null;
				try{
					// 정상일 경우
					// 암호화된 비밀번호 취득
					pswdno = Commons.getCryptoMD5String(pswd);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				// 비밀번호가 일치하는 경우
				if (userInfo.getPwd().equals(pswdno)) {
 					// 세션에 사용자 정보를 저장한다
					setUserInfo(userInfo,user_id);
					
					// 비밀번호 오류 횟수가 0이 아닌경우 초기화 시킨다
					if (pwdErnm != 0) {
						setPwdErnmYn(userInfo.getUser_id(), 0);
					}
					
					result = "LOGIN_SUCCESS";
				} else {
					// 비밀번호가 일치하지 않는 경우, 비밀번호 오류 횟수를 1증가시킨다
					setPwdErnmYn(userInfo.getUser_id(), pwdErnm + 1);
					if (pwdErnm + 1 == 3) {
						result = "LOGIN_DENINED";
					} else {
						result = "LOGIN_FAIL";
					}
				}
			} else {
				// 3회 이상 틀렸을 경우, 에러메세지를 반환한다
				result = "LOGIN_DENINED";
			}
		} else {
			result = "LOGIN_FAIL2";
		}
		return result;
	}

	
	
	
	
	//사용자 비밀번호 오류 횟수를 설정한다
	private void setPwdErnmYn(String user_id, int num) {
		
		// 유저정보 설정
		LoginVO loginParam = new LoginVO();
		loginParam.setUser_id(user_id);
		loginParam.setPwd_err_cnt(num);
		
		// 사용자 비밀번호 오류 횟수 설정
		dao.updatePwdErnmYn(loginParam);
	 
	}

	//세션에 사용자 정보를 저장한다
	private void setUserInfo(LoginVO userInfo, String user_id) {
		
		httpSession.setAttribute("user", userInfo.getUser_id());
		
		httpSession.setAttribute("user_nm", userInfo.getUser_nm());
		
	}

	//아이디로 유저정보를 가져온다
	private LoginVO getUserInfo(String user_id) {
		
		LoginVO loginParam = new LoginVO();
		loginParam.setUser_id(user_id);
		
		LoginVO userInfo = dao.selectUser(loginParam);
		return userInfo;
	}
	
	
	
	
}
