package com.core.plus.info.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.core.plus.info.user.vo.UserVO;

@Repository
public class UserDaoImpl implements UserDao{
	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	public void setSqlSessionTemplate(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	@Override
	public List<UserVO> userList(Map<String, Object> userMap) {
		List<UserVO> list = null;
		try {
			list = sqlSession.selectList("user.userList", userMap);
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		return list;
	}

	@Override
	public UserVO userDetail(String user_id) {
		UserVO userVO = null;
		try {
			userVO = sqlSession.selectOne("user.userDetail", user_id);
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		return userVO;
	}

	@Override
	public List<UserVO> userType() {
		List<UserVO> list = null;
		try {
			list = sqlSession.selectList("user.userType");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<UserVO> userOrgList(Map<String, Object> userMap) {
		List<UserVO> list = null;
		try {
			list = sqlSession.selectList("user.userOrgList",userMap);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	@Override
	public List<UserVO> authList() {
		List<UserVO> list = null;
		try {
			list = sqlSession.selectList("user.authList");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}@Override
	public List<UserVO> userAuthList(String user_id) {
		List<UserVO> list = null;
		try {
			list = sqlSession.selectList("user.userAuthList",user_id);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int userInsert(UserVO userVO) {
		int result = 0;
		try {
			result = sqlSession.insert("user.userInsert", userVO);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int userUpdate(UserVO userVO){
		int result = 0;
		try {
			result = sqlSession.insert("user.userUpdate", userVO);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int userAuthInsert(String auth_id, String user_id) {
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("auth_id", auth_id);
		map.put("user_id", user_id);
		try {
			result = sqlSession.delete("user.userAuthInsert", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	//사용자리스트 삭제(여러개)
	@Override
	public int getUserChkDelete(String[] user_id) {
		int userChkDel_result = 0;
		int resultTemp = 0;
		try {
			for(int i=0; i<user_id.length; i++){
				resultTemp += sqlSession.update("userChkDelete1", user_id[i]);
				resultTemp += sqlSession.update("userChkDelete", user_id[i]);
				userChkDel_result += resultTemp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userChkDel_result;
	}

	@Override
	public int userDelete(String[] user_id) {
		int resultTemp = 0;
		try {
			for(int i=0; i<user_id.length; i++){
				resultTemp = sqlSession.delete("user.userDelete", user_id[i]);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return resultTemp;
	}
	
	@Override
	public int userAuthDelete(String user_id) {
		int result = 0;
		try {
			result = sqlSession.delete("user.userAuthDelete", user_id);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int getUserListCount(Map<String, Object> map) {
		int totalCount = 0;
		try {
			totalCount = sqlSession.selectOne("user.selectUserListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> getOrgModalList(Map<String, Object> map) {
		List<Map<String, Object>> list = null;
		try {
			list = sqlSession.selectList("user.userOrgList",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int getOrgModalListCount(Map<String, Object> map) {
		int totalCount = 0;
		try {
			totalCount = sqlSession.selectOne("user.userOrgListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}

	@Override
	public int getUserIdCount(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.userIdCount", user_id);
	}
	
}
