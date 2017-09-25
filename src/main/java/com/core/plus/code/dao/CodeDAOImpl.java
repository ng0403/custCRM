package com.core.plus.code.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.core.plus.code.vo.CodeVO;


@Repository
public class CodeDAOImpl implements CodeDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	//페이징
	@Override
	public int getCodeListRow(Map<String, Object> map) {
		
		int totalCount = 0;
		try {
			totalCount = sqlSession.selectOne("code.codeListTotalRow", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	//List
	@Override
	public List<CodeVO> codeList() {
		
		List<CodeVO> vo = sqlSession.selectList("code.codeList");
		return vo;
	}


	

}
