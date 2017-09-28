package com.core.plus.login.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.vo.LoginVO;

@Repository
public class LoginDaoImpl implements LoginDAO {

	@Autowired
	private SqlSession sqlSession;

	public void setSqlSessionTemplate(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int selectOne(String root, Object obj) {
		try{
			int result = sqlSession.selectOne(root, obj);
			return result;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	//사용자 정보조회
	@Override
	public LoginVO selectUser(LoginVO loginParam) {
		return sqlSession.selectOne("login.selectUser", loginParam);
	} 
	

	// 사용자 비밀번호 오류 횟수 설정
	@Override
	public void updatePwdErnmYn(LoginVO loginParam) {
		sqlSession.update("login.updatePwdErnmYn", loginParam);
	}

	@Override
	public MenuVo getMenuAuthInfo(Map<String, String> menuAuthMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("login.menuAuth", menuAuthMap);
	}
	
}
