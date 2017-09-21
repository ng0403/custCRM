package com.core.plus.lead.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.task.vo.TaskVO;

public interface LeadService {
	
	List<LeadVO> lead_list(Map<String, Object> map); //가망 고객 리스트
	void lead_insert(LeadVO vo); //가망 고객 추가
	void lead_update(LeadVO vo); // 가망 고객 수정
	LeadVO lead_detail(String lead_no); // 가망 고객 디테일
	void lead_delete(String lead_no); // 가망 고객 삭제
	public List<LeadVO> leadSearch(Map<String,Object> leadMap); //가망 고객 조건 검색
	
	
	//popup
	List<CustVO> custPopupList();
	List<CustVO> custPopupList(Map<String, Object> map);
	
	List<EmpVO> empPopupList();
	List<EmpVO> empPopupList(Map<String, Object> map);
	
	// Paging
	PagerVO getLeadListRow(Map<String, Object> map);
	
	//엑셀 출력
	List<LeadVO> leadExcelExport(Map<String, Object> leadMap);
	
	int excelUpload(MultipartFile excelFile);
	LeadVO leadNoIndex();			
}
