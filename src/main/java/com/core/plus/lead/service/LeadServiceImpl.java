package com.core.plus.lead.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.lead.dao.LeadDao;
import com.core.plus.lead.vo.InterestItemVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.task.vo.TaskVO;

@Service
public class LeadServiceImpl implements LeadService {

	@Resource
	LeadDao leadDao;

	@Override
	public List<LeadVO> lead_list(Map<String, Object> map) {
		List<LeadVO> vo = leadDao.lead_list(map);
		return vo;
	}

	@Override
	public PagerVO getLeadListRow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int PageNum = (Integer) map.get("PageNum");
		PagerVO page = new PagerVO(PageNum, 0, 10, 10);

		int totalRowCount = leadDao.getLeadListRow(map);

		page = new PagerVO(PageNum, totalRowCount, 10, 10);

		return page;
	}

	@Override
	public LeadVO lead_detail(String lead_no) {
		return leadDao.lead_detail(lead_no);
	}

	@Override
	public LeadVO lead_detail(String lead_no, String cust_lead_no) {
		return leadDao.lead_detail(lead_no, cust_lead_no);
	}

	@Override
	public void lead_insert(LeadVO vo) {
		leadDao.lead_insert(vo);
	}

	@Override
	public void lead_update(LeadVO vo) {
		leadDao.lead_update(vo);
	}

	@Override
	public void lead_delete(String lead_no) {
		leadDao.lead_delete(lead_no);
	}

	// 인덱스번호
	@Override
	public LeadVO leadNoIndex() {
		return leadDao.leadNoIndex();
	}

	@Override
	public List<LeadVO> leadSearch(Map<String, Object> leadMap) {
		return leadDao.leadSearch(leadMap);
	}

	@Override
	public List<LeadVO> leadStatusCode() {
		return leadDao.leadStatusCode();
	}

	@Override
	public List<LeadVO> leadOpptyCode() {
		return leadDao.leadOpptyCode();
	}

	// 페이징
	@Override
	public PagerVO getTaskListRow(Map<String, Object> map) {
		System.out.println("Service map ? " + map.toString());
		int taskPageNum = (Integer) map.get("taskPageNum");
		PagerVO page = new PagerVO(taskPageNum, 0, 10, 10);

		int totalRowCount = leadDao.getTaskListRow(map);
		page = new PagerVO(taskPageNum, totalRowCount, 10, 10);

		return page;
	}

	// List
	@Override
	public List<TaskVO> taskList() {
		return leadDao.taskList();
	}

	// List 페이징
	@Override
	public List<TaskVO> taskList(Map<String, Object> taskMap) {
		return leadDao.taskList(taskMap);
	}

	// 조회
	@Override
	public List<TaskVO> taskSchList(Map<String, Object> taskMap) {
		return leadDao.taskSchList(taskMap);
	}

	// Item
	@Override
	public List<InterestItemVO> leadItemList(String lead_no) {
		// TODO Auto-generated method stub
		return leadDao.leadItemList(lead_no);
	}

	@Override
	public int leadItemDelete(String lead_no) {
		// TODO Auto-generated method stub
		return leadDao.leadItemDelete(lead_no);
	}

	@Override
	public int leadItemInsert(List<InterestItemVO> interVo) {
		return leadDao.leadItemInsert(interVo);
	}

	@Override
	public List<CustVO> custPopupList() {
		// TODO Auto-generated method stub
		return leadDao.custPopupList();
	}

	@Override
	public List<CustVO> custPopupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return leadDao.custPopupList(map);
	}

	@Override
	public List<EmpVO> empPopupList() {
		// TODO Auto-generated method stub
		return leadDao.empPopupList();
	}

	@Override
	public List<EmpVO> empPopupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return leadDao.empPopupList(map);
	}

	// 엑셀 출력
	@Override
	public List<LeadVO> leadExcelExport(Map<String, Object> leadMap) {

		List<LeadVO> leadExcelExport = leadDao.leadExcelExport(leadMap);
		return leadExcelExport;
	}

	@Override
	public int excelUpload(MultipartFile excelFile) {
		// TODO Auto-generated method stub
		int result = leadDao.leadUploadExcel(excelFile);

		return result;
	}

}
