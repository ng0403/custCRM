package com.core.plus.info.user.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.info.user.vo.UserVO;

public interface UserDao {
	public List<UserVO> userList(Map<String, Object> userMap);
	UserVO userDetail(String user_id);
	List<UserVO> userType();
	List<UserVO> userOrgList(Map<String, Object> userMap);
	int userAuthDelete(String user_id);
	int userAuthInsert(String auth_id,String user_id);
	int userDelete(String[] user_id);
	int userInsert(UserVO userVO);
	int userUpdate(UserVO userVO);
	public List<UserVO> authList();
	public List<UserVO> userAuthList(String user_id);
	//사용자리스트 삭제(여러개)
	public int getUserChkDelete(String[] user_id);
	public int getUserListCount(Map<String, Object> map);
	

	List<Map<String,Object>> getOrgModalList(Map<String,Object> map);
	int getOrgModalListCount(Map<String,Object> map);
	public int getUserIdCount(String user_id);	
}