package com.core.plus.info.org.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.core.plus.info.org.vo.OrgVO;

@Repository
public class OrgDaoImpl implements OrgDao{

	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	public void setSqlSessionTemplate(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<OrgVO> orgList(Map<String, Object> orgMap) {
		List<OrgVO> list = null;
		try {
			list = sqlSession.selectList("org.orgList", orgMap);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	@Override
	public List<OrgVO> repOrgList(Map<String, Object> orgMap) {
		List<OrgVO> list = 	null;
		try {
			list = sqlSession.selectList("org.repOrgList", orgMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public OrgVO orgDetail(String org_id) {
		OrgVO orgVO = null;
		try {
			orgVO = sqlSession.selectOne("org.orgDetail", org_id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return orgVO;
	}

	@Override
	public int orgInsert(OrgVO orgVO) {
		int result=0;
		try {
			 result = sqlSession.insert("org.orgInsert", orgVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int orgUpdate(OrgVO orgVO) {
		int result=0;
		try {
			 result = sqlSession.update("org.orgUpdate", orgVO);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public int getOrgChkDelete(String[] org_id) {
		int orgChkDel_result = 0;
		int resultTemp = 0;
		try {
			for(int i=0; i<org_id.length; i++){
				resultTemp += sqlSession.update("orgChkDelete", org_id[i]);
				orgChkDel_result += resultTemp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgChkDel_result;
	}

	@Override
	public int getOrgListCount(Map<String, Object> map) {
		
		int orgListCount = sqlSession.selectOne("org.orgListCount", map);
		
		return orgListCount;
	}

	@Override
	public int getRepUserListCount(Map<String, Object> orgMap) {
		int repListCount = sqlSession.selectOne("org.repListCount", orgMap);
		
		return repListCount;
	}

}
