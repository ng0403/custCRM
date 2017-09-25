package com.core.plus.code.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;

public interface CodeDAO {

	int getCodeListRow(Map<String, Object> map);								// 페이징
	List<CodeVO> codeList();													// 전체 리스트


}
