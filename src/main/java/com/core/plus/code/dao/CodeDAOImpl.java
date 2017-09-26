package com.core.plus.code.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.core.plus.code.vo.CodeVO;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.task.vo.TaskVO;


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
	public List<CodeVO> codeList(Map<String, Object> codeMap) {
		List<CodeVO> vo = sqlSession.selectList("code.codeList", codeMap);
		return vo;
	}

	//조회
	@Override
	public List<CodeVO> codeSchList(Map<String, Object> codeMap) {
		List<CodeVO> vo = sqlSession.selectList("code.codeList", codeMap);
		return vo;
	}

	//상세보기
	@Override
	public List<CodeVO> recodeDetail(Map<String, Object> codeMap) {
		List<CodeVO> vo = sqlSession.selectList("code.codeDetail",codeMap);
		return vo;
	}

	//코드추가
//	@Override
//	public int getCodeInsert(CodeVO codeVO) {
//		int result = 0;
//		try {
//			
//			result += sqlSession.insert("code.codeInsert" , codeVO); 
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	//코드수정
//	@Override
//	public int codeUpdateSave(Map<String, Object> map) {
//		int codeUpdateSave = 0;
//		try {
//			codeUpdateSave = sqlSession.update("code.codeUpdateSave", map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return codeUpdateSave;
//	}

	@Override
	public int codeInsert(CodeVO codeVO) {
		int result = sqlSession.insert("code.codeInsert", codeVO);
		return result;
	}

	@Override
	public int codeEdit(CodeVO codeVO) {
		int result = sqlSession.insert("code.codeEdit", codeVO);
		return result;
	}

	@Override
	public int codeDelete(CodeVO codeVO) {
		int result = sqlSession.insert("code.codeDelete", codeVO);
		return result;
	}


	

}
