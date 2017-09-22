package com.core.plus.info.auth.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.info.auth.vo.AuthVO;

public interface AuthDao {

	//권한 리스트
	List<AuthVO> getAuthList(Map<String, Object> map);

	//권한 상세정보
	AuthVO getAuthDetail(String auth_id);

	//권한 추가
	int getAuthInsert(AuthVO authVo);

	//권한 수정
	int getAuthUpdate(AuthVO authVo);

	//권한 삭제
	int getAuthDelete(AuthVO authVo);

	//사용자 권한 리스트
	List<AuthVO> getAuthUserList(Map<String, Object> map);

	//권한 체크 삭제 (여러개 삭제)
	int getAuthChkDelete(String[] auth_id);

	//사용자 권한 체크 삭제 (여러개 삭제)
	int getAuthUserChkDelete(String[] user_id, String auth_id);
}
