package com.core.plus.lead.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.lead.vo.InterestItemVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.oppty.vo.OpptyItemVO;
import com.core.plus.task.vo.TaskVO;

public interface LeadService {
	
	List<LeadVO> lead_list(Map<String, Object> map); //가망 고객 리스트
	PagerVO getLeadListRow(Map<String, Object> map); // Paging

	LeadVO lead_detail(String lead_no); // 가망 고객 디테일
	LeadVO lead_detail(String lead_no, String cust_lead_no); // 가망 고객 디테일 

	void lead_insert(LeadVO vo); //가망 고객 추가
	void lead_update(LeadVO vo); // 가망 고객 수정
	void lead_delete(String lead_no); // 가망 고객 삭제
	
	LeadVO leadNoIndex();			
	public List<LeadVO> leadSearch(Map<String,Object> leadMap); //가망 고객 조건 검색 
	
	//코드 관련
	List<LeadVO> leadStatusCode();
	List<LeadVO> leadOpptyCode();
	
 	//상담이력
	PagerVO getTaskListRow(Map<String, Object> map);					// 페이징
	List<TaskVO> taskList();											// List
	List<TaskVO> taskList(Map<String, Object> taskMap);					// List 페이징
	List<TaskVO> taskSchList(Map<String, Object> taskMap);				// 조회

	//Item
	List<InterestItemVO> leadItemList(String lead_no);
	int leadItemDelete(String lead_no);
	int leadItemInsert(List<InterestItemVO> interVo);
	
	//popup
	List<CustVO> custPopupList();
	List<CustVO> custPopupList(Map<String, Object> map);
	
	List<EmpVO> empPopupList();
	List<EmpVO> empPopupList(Map<String, Object> map);
	
 	//엑셀 출력
	List<LeadVO> leadExcelExport(Map<String, Object> leadMap); 
	int excelUpload(MultipartFile excelFile);
}
