package com.core.plus.code.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;
import com.core.plus.task.vo.TaskVO;

public interface CodeDAO {

	int getCodeListRow(Map<String, Object> map);								// 페이징
	List<CodeVO> codeList(Map<String, Object> codeMap);							// 전체 리스트
	List<CodeVO> codeSchList(Map<String, Object> codeMap);						// 조회
	List<CodeVO> recodeDetail(Map<String, Object> codeMap);						// 상세보기
	int codeInsert(CodeVO codeVO);												// 추가
	int codeEdit(CodeVO codeVO);												// 편집
	int codeDelete(CodeVO codeVO);												// 삭제

 
}
