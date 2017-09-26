package com.core.plus.lead.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.lead.vo.InterestItemVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.oppty.vo.OpptyItemVO;
import com.core.plus.task.vo.TaskVO;

public interface LeadDao {
	
	List<LeadVO> lead_list(Map<String, Object> map); //가망고객 리스트 출력
	List<LeadVO> lead_status_list(Map<String, Object> map); //가망고객 상태 리스트 출력
	void lead_insert(LeadVO vo); //가망고객 추가
	void lead_update(LeadVO vo); // 가망고객 수정
	LeadVO lead_detail(String lead_no); // 가망 고객 상세정보
	void lead_delete(String lead_no); // 가망 고객 삭제
	
	public List<LeadVO> leadSearch(Map<String, Object> leadMap); // 가망 고객 조건 검색
	public List<LeadVO> leadStatusSearch(Map<String, Object> leadMap); // 가망 고객 조건 검색
	
	//코드 관련
	List<LeadVO> leadStatusCode(); 
	List<LeadVO> leadOpptyCode();
	
	//상담이력
	List<TaskVO> taskList();											// List
	List<TaskVO> taskList(Map<String, Object> taskMap);					// List 페이징
	int getTaskListRow(Map<String, Object> map);						// 고객 팝업
	List<TaskVO> taskSchList(Map<String, Object> taskMap);				// 조회
	
	//Item
	List<InterestItemVO> leadItemList(String lead_no);
	int leadItemDelete(String lead_no);
	int leadItemInsert(List<InterestItemVO> opptyVo);
	
	//popup
	List<CustVO> custPopupList();
	List<CustVO> custPopupList(Map<String, Object> map);
	
	List<EmpVO> empPopupList();
	List<EmpVO> empPopupList(Map<String, Object> map);
	
	int getLeadListRow(Map<String, Object> map);
	int getLeadStatusListRow(Map<String, Object> map);
	// 엑셀 출력
	List<LeadVO> leadExcelExport(Map<String, Object> leadMap);
	
	int leadUploadExcel(MultipartFile excelFile);
	
	LeadVO leadNoIndex();	
}
