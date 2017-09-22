package com.core.plus.info.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.info.auth.dao.AuthDao;
import com.core.plus.info.auth.vo.AuthVO;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthDao dao;
	
	//권한 리스트
	@Override
	public List<AuthVO> getAuthList(Map<String, Object> map) {
		List<AuthVO> auth = dao.getAuthList(map);
		return auth;
	}

	//권한 상세정보
	@Override
	public AuthVO getAuthDetail(String auth_id) {
		AuthVO vo = dao.getAuthDetail(auth_id);
		return vo;
	}

	//권한 추가
	@Override
	public int getAuthInsert(AuthVO authVo) {
		int result = dao.getAuthInsert(authVo);
		return result;
	}

	//권한 수정
	@Override
	public int getAuthUpdate(AuthVO authVo) {
		int authMdf_result = dao.getAuthUpdate(authVo);
		return authMdf_result;
	}

	//권한 삭제
	@Override
	public int getAuthDelete(AuthVO authVo) {
		int authDel_result = dao.getAuthDelete(authVo);
		return authDel_result;
	}

	//사용자권한 리스트
	@Override
	public List<AuthVO> getAuthUserList(Map<String, Object> map) {
		List<AuthVO> authuser = dao.getAuthUserList(map);
		return authuser;
	}

	//권한 체크 삭제 (여러개 삭제)
	@Override
	public int getAuthChkDelete(String[] auth_id) {
		int authChkDel_result = dao.getAuthChkDelete(auth_id);
		return authChkDel_result;
	}
	
	//사용자 권한 체크 삭제 (여러개 삭제)
	@Override
	public int getAuthUserChkDelete(String[] user_id, String auth_id) {
		int authuserChkDel_result = dao.getAuthUserChkDelete(user_id, auth_id);
		return authuserChkDel_result;
	}
}
