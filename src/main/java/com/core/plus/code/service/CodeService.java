package com.core.plus.code.service;

import java.util.List;
import java.util.Map;

import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;
import com.core.plus.task.vo.TaskVO;

public interface CodeService {

	PagerVO getCodeListRow(Map<String, Object> map);								// 페이징					
	List<CodeVO> codeList(Map<String, Object> codeMap);								// 전체 리스트
	List<CodeVO> codeSchList(Map<String, Object> codeMap);							// 조회
	List<CodeVO> codeDetail(Map<String, Object> codeMap);							// 상세보기
//	int getCodeInsert(CodeVO codeVO);												// 코드 추가
//	int codeUpdateSave(Map<String, Object> map);									// 코드 수정
	int codeInsert(CodeVO codeVO);
	int codeEdit(CodeVO codeVO);
	int codeDelete(CodeVO codeVO);

}
