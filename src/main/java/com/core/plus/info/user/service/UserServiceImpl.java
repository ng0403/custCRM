package com.core.plus.info.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.plus.common.PagerVO;
import com.core.plus.info.user.dao.UserDao;
import com.core.plus.info.user.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService{
	@Resource
	UserDao userDao;
	
	@Override
	public List<UserVO> userList(Map<String, Object> userMap) {
		List<UserVO> list = userDao.userList(userMap);
		return list;
	}

	@Override
	public UserVO userDetail(String user_id) {		
		UserVO userVO = userDao.userDetail(user_id);		
		return userVO;
	}

	@Override
	public List<UserVO> userType() {
		List<UserVO> list = userDao.userType();
		return list;
	}

	@Override
	public List<UserVO> userOrgList(Map<String, Object> userMap) {
		List<UserVO> list = userDao.userOrgList(userMap);	
		return list;
	}

	@Override
	public List<UserVO> authList() {
		List<UserVO> list = userDao.authList();	
		return list;
	}

	@Override
	public List<UserVO> userAuthList(String user_id) {
		List<UserVO> list = userDao.userAuthList(user_id);	
		return list;
	}

	@Override
	public int userInsert(UserVO userVO) {
		int result = userDao.userInsert(userVO);
		return result;
	}

	@Override
	public int userUpdate(UserVO userVO) {
		int result = userDao.userUpdate(userVO);
		return result;
	}

	@Override
	public int userAuthInsert(String auth_id, String user_id) {
		int result = userDao.userAuthInsert(auth_id,user_id);
		return result;
	}

	//사용자리스트 삭제(여러개)
	@Override
	public int getUserChkDelete(String[] user_id) {
		int userChkDel_result = userDao.getUserChkDelete(user_id);
		return userChkDel_result;
	}	

	@Override
	public int userDelete(String[] user_id) {
		int result = userDao.userDelete(user_id);
		return result;
	}

	@Override
	public int userAuthDelete(String user_id) {
		int result = userDao.userAuthDelete(user_id);
		return result;
	}
	
	@Override
	public PagerVO getUserListCount(Map<String, Object> map) {
		int PageNum = (Integer) map.get("pageNum");
		// 전체 행의 갯수 얻기
		int totalRowCount = userDao.getUserListCount(map);
		// 현재 페이지의 정보 세팅
		PagerVO page = new PagerVO(PageNum, totalRowCount, 10, 9999);
		
		return page;
	}

	@Override
	public List<Map<String, Object>> getOrgModalList(Map<String, Object> map) {
		return userDao.getOrgModalList(map);
	}

	@Override
	public PagerVO getOrgModalListCount(Map<String, Object> map) {
		int PageNum = (Integer) map.get("pageNum");
		// 전체 행의 갯수 얻기
		int totalRowCount = userDao.getOrgModalListCount(map);
		// 현재 페이지의 정보 세팅
		PagerVO page = new PagerVO(PageNum, totalRowCount, 5, 99999);
		
		return page;
	}

	@Override
	public int getUserIdCount(String user_id) {
		// TODO Auto-generated method stub
		return userDao.getUserIdCount(user_id);
	}
}
