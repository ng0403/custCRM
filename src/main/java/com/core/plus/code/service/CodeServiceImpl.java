package com.core.plus.code.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.plus.code.dao.CodeDAO;
import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;

@Service
public class CodeServiceImpl implements CodeService {
	
	@Resource
	CodeDAO codeDao;

	//페이징
	@Override
	public PagerVO getCodeListRow(Map<String, Object> map) {
		int codePageNum = (Integer)map.get("codePageNum");
		PagerVO page = new PagerVO(codePageNum, 0, 10, 10);
		
		int totalRowCount = codeDao.getCodeListRow(map);
		page = new PagerVO(codePageNum, totalRowCount, 10, 10);
		
		return page;
	}
	
	@Override
	public List<CodeVO> codeList(Map<String, Object> codeMap) {
		return codeDao.codeList();
	}
	
	

}
