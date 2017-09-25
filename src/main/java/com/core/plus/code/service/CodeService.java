package com.core.plus.code.service;

import java.util.List;
import java.util.Map;

import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;

public interface CodeService {

	PagerVO getCodeListRow(Map<String, Object> map);								// 페이징					
	List<CodeVO> codeList(Map<String, Object> codeMap);								// 전체 리스트

}
