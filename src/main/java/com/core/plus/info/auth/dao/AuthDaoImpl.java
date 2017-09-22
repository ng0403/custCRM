package com.core.plus.info.auth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.core.plus.info.auth.vo.AuthVO;

@Repository
public class AuthDaoImpl implements AuthDao {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSessionTemplate(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//권한 리스트
	@Override
	public List<AuthVO> getAuthList(Map<String, Object> map) {
		List<AuthVO> auth = null;
		try{
			auth = sqlSession.selectList("Auth_List", map); //"" 안은 mapper select id 이름
		} catch(Exception e){
			e.printStackTrace();
		}
		return auth;
	}

	//권한 상세정보
	@Override
	public AuthVO getAuthDetail(String auth_id) {
		AuthVO vo = null;
		try {
			vo = sqlSession.selectOne("authDetail", auth_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	//권한 추가
	@Override
	public int getAuthInsert(AuthVO authVo) {
		int result = 0;
		try {
			result = sqlSession.insert("authInsert", authVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//권한 수정
	@Override
	public int getAuthUpdate(AuthVO authVo) {
		int authMdf_result = 0;
		try {
			authMdf_result = sqlSession.update("authUpdate", authVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authMdf_result;
	}
	
	//권한 삭제
	@Override
	public int getAuthDelete(AuthVO authVo) {
		int authDel_result = 0;
		try {
			authDel_result = sqlSession.update("authDelete", authVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authDel_result;
	}

	//사용자 권한 리스트
	@Override
	public List<AuthVO> getAuthUserList(Map<String, Object> map) {
		List<AuthVO> authuser = null;
		try {
			authuser = sqlSession.selectList("authuserList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authuser;
	}

	//권한 체크 삭제 (여러개 삭제)
	@Override
	public int getAuthChkDelete(String[] auth_id) {
		int authChkDel_result = 0;
		int resultTemp = 0;
		try {
			for(int i=0; i<auth_id.length; i++){
				resultTemp += sqlSession.update("authChkDelete", auth_id[i]);
				authChkDel_result += resultTemp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authChkDel_result;
	}
	
	//사용자 권한 체크 삭제 (여러개 삭제)
	@Override
	public int getAuthUserChkDelete(String[] user_id, String auth_id) {
		int authuserChkDel_result = 0;
		int resultTemp = 0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("auth_id", auth_id);
		
		user_id = (String[]) map.get("user_id"); //map으로 넣은 user_id 배열로 선언
		try {
			for(int i=0; i<user_id.length; i++){
				map.put("user_id", user_id[i]);
				resultTemp += sqlSession.update("authuserChkDelete1",  map);
				authuserChkDel_result += resultTemp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authuserChkDel_result;
	}
}
