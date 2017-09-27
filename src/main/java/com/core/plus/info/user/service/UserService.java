package com.core.plus.info.user.service;

import java.util.List;
import java.util.Map;

import com.core.plus.common.PagerVO;
import com.core.plus.info.user.vo.UserVO;

public interface UserService {
	List<UserVO> userList(Map<String, Object> userMap);
	UserVO userDetail(String user_id);
	List<UserVO> userType();
	List<UserVO> userOrgList(Map<String, Object> userMap);
	int userAuthInsert(String auth_id,String user_id);
	int userInsert(UserVO userVO);
	int userUpdate(UserVO userVO);
	public List<UserVO> authList();
	public List<UserVO> userAuthList(String user_id);
	PagerVO getUserListCount(Map<String, Object> map);
	//사용자리스트 삭제(여러개)
	int getUserChkDelete(String[] user_id); //체크유저 삭제

	int userAuthDelete(String user_id);	// 유저 권한삭제
	int userDelete(String[] user_id);	// 유저 삭제
	
	List<Map<String,Object>> getOrgModalList(Map<String,Object> map);
	PagerVO getOrgModalListCount(Map<String,Object> map);
	int getUserIdCount(String user_id);

}