package com.core.plus.code.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.plus.code.dao.CodeDAO;
import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;
import com.core.plus.task.vo.TaskVO;

@Service
public class CodeServiceImpl implements CodeService {
	
	@Resource
	CodeDAO codeDao;

	//페이징
	@Override
	public PagerVO getCodeListRow(Map<String, Object> map) {
		int codePageNum = (Integer)map.get("codePageNum");
		PagerVO page = new PagerVO(codePageNum, 0, 15, 100);
		
		int totalRowCount = codeDao.getCodeListRow(map);
		page = new PagerVO(codePageNum, totalRowCount, 15, 100);
		
		return page;
	}
	
	//전체 리스트
	@Override
	public List<CodeVO> codeList(Map<String, Object> codeMap) {
		return codeDao.codeList(codeMap);
	}

	//조회
	@Override
	public List<CodeVO> codeSchList(Map<String, Object> codeMap) {
		return codeDao.codeSchList(codeMap);
	}

	//상세보기
	@Override
	public List<CodeVO> codeDetail(Map<String, Object> codeMap) {
		return codeDao.recodeDetail(codeMap);
	}

	//추가
	@Override
	public int codeInsert(CodeVO codeVO) {
		return codeDao.codeInsert(codeVO);
	}
	
	//편집
	@Override
	public int codeEdit(CodeVO codeVO) {
		return codeDao.codeEdit(codeVO);
	}

	//삭제
	@Override
	public int codeDelete(CodeVO codeVO) {
		return codeDao.codeDelete(codeVO);
	}
	
	

}
