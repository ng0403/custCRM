package com.core.plus.lead.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.lead.vo.InterestItemVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.oppty.vo.OpptyItemVO;
import com.core.plus.task.vo.TaskVO;

@Repository
public class LeadDaoImpl implements LeadDao {
	 
	@Autowired SqlSession sqlSession;

	@Override
	public List<LeadVO> lead_list(Map<String, Object> map) {
		List<LeadVO> vo = sqlSession.selectList("leadList", map);
		
		return vo;
	} 

	@Override
	public void lead_insert(LeadVO vo) {
		System.out.println("insert vo " + vo.toString());
		sqlSession.insert("lead_single_add", vo);
		
	}

	@Override
	public void lead_update(LeadVO vo) {
		
		sqlSession.update("lead_update", vo);
		
	}

	@Override
	public LeadVO lead_detail(String lead_no) {
		 System.out.println("Dao : " + lead_no);
		return sqlSession.selectOne("lead_detail", lead_no);
	}
	
	@Override
	public LeadVO lead_detail(String lead_no, String cust_lead_no) {
		// TODO Auto-generated method stub
		Map<String, Object> custLeadDetail = new HashMap<String, Object>();
		custLeadDetail.put("lead_no", lead_no);
		custLeadDetail.put("cust_no", cust_lead_no);
		
		System.out.println("DAO : " + custLeadDetail);
		
		return sqlSession.selectOne("cust_lead_detail", custLeadDetail);
	}

	@Override
	public void lead_delete(String lead_no) {
		
		sqlSession.update("lead_delete", lead_no);
		
	}

	@Override
	public List<LeadVO> leadSearch(Map<String, Object> leadMap) {
		
		List<LeadVO> obj = sqlSession.selectList("leadList", leadMap);
		return obj;
	}
	
	@Override
	public List<LeadVO> leadStatusSearch(Map<String, Object> leadMap) {
		
		List<LeadVO> obj = sqlSession.selectList("leadStatusList", leadMap);
		return obj;
	}

	@Override
	public List<CustVO> custPopupList() {
		// TODO Auto-generated method stub
		List<CustVO> custPopList = sqlSession.selectList("lead.custPopupList");
		
		return custPopList;
	}

	@Override
	public List<CustVO> custPopupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<CustVO> custPopList = sqlSession.selectList("lead.custPopupList", map);
		
		return custPopList;
	}

	@Override
	public List<EmpVO> empPopupList() {
		// TODO Auto-generated method stub
		List<EmpVO> empPopList = sqlSession.selectList("lead.empPopupList");
		
		return empPopList;
	}

	@Override
	public List<EmpVO> empPopupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<EmpVO> empPopList = sqlSession.selectList("lead.empPopupList", map);
		
		return empPopList;
	}
	
	
	@Override
	public int getLeadListRow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("lead.leadListTotalRow", map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	@Override
	public int getLeadStatusListRow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("lead.leadStatusListTotalRow", map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	//인덱스번호
		@Override
		public LeadVO leadNoIndex() {
			
			return sqlSession.selectOne("lead.leadNoIndex");
		}

	// 엑셀 출력
	@Override
	public List<LeadVO> leadExcelExport(Map<String, Object> leadMap) {
		
		List<LeadVO> leadExcelExport = null;
		try {
			leadExcelExport = sqlSession.selectList("lead.leadExcelExport", leadMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leadExcelExport;
	}

	@Override
	public int leadUploadExcel(MultipartFile excelFile) {
		// TODO Auto-generated method stub
		System.out.println("Excel Upload Dao");
		int result = 0;
		
		try {
			Workbook workBook = WorkbookFactory.create(excelFile.getInputStream());
			Sheet sheet = workBook.getSheetAt(0);
			Row row = null;
			Cell cell = null;
 			
 			String lead_name = null;
			String cust_no = null;
			String cust_name= null;
			String emp_no  = null;
			String user_nm = null;
			String contact_day = null;
			String lead_status_cd  = null;
			String reason_cd = null;
			String remark_cn = null;
			
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("lead rows ? " + rows);
			 
			for(int i=1; i<rows; i++) {
				System.out.println("for enter " + i);
				row = sheet.getRow(i);
			    
				/*cell = row.getCell(0);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					cell.setCellType(Cell.CELL_TYPE_STRING);
					lead_no = cell.getStringCellValue();
					
					System.out.println("lead_no");
				}*/
				
				cell = row.getCell(0);
				lead_name = cell.getStringCellValue().trim();
				
				System.out.println(i + " 번째lead_name ? " + lead_name);

				cell = row.getCell(1);
			
/*			  	System.out.println("cell? " + cell.toString());
				cust_no = cell.getStringCellValue().trim();
				System.out.println( i + "번째 cust_no ? " + cust_no);
*/				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					cust_no = cell.getStringCellValue().trim();
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cust_no = cell.getStringCellValue();
 					System.out.println("cust_no" + cust_no);
				} 
				else{
					cust_no = cell.getStringCellValue();
					System.out.println("cust_no???" + cust_no);
				} 
				  cell = row.getCell(2);
				if(cust_name != null){
				cust_name = cell.getStringCellValue().trim();
				}else{
					cust_name = " ";
				}
				System.out.println(i + " 번쩨 cust name ? " + cust_name); 
				/*if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cust_name = cell.getStringCellValue();
					
					System.out.println(i + "cust_name" + cust_name);
				}*/
				
				cell = row.getCell(3);
				System.out.println("cell? " + cell.toString());
				emp_no = cell.getStringCellValue().trim();
				if(emp_no == null)
				{
					emp_no = " ";
				}
				System.out.println(i + "번째 emp_no ? " + emp_no);
				
				/*cell = row.getCell(3);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					emp_no = String.valueOf(tmp);
					System.out.println("emp no?" + emp_no);
				}*/
				
				cell = row.getCell(4);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					user_nm = String.valueOf(tmp);
					if(user_nm == null)
					{
						user_nm = " ";
					}
					System.out.println(i + "번째 user_name? " + user_nm);
				}
				
				cell = row.getCell(5);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					contact_day = String.valueOf(tmp);
					System.out.println(i + "번째 contact_day ? " + contact_day);
				}
				 
				
				cell = row.getCell(6);
				lead_status_cd = cell.getStringCellValue().trim();
				System.out.println("lead_status_cd ? " + lead_status_cd);
				/*if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.println("setLead_status_cd enter");
					int tmp = (int) cell.getNumericCellValue();
					lead_status_cd = String.format("%03d", tmp);
					
					System.out.println(i + "번째 rank_cd ? " + lead_status_cd);
				}*/
				
				cell = row.getCell(7);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.println("reason_ce enter");
					int tmp = (int) cell.getNumericCellValue();
					System.out.println("tmp ? " + tmp);
					reason_cd = String.format("%03d", tmp);
					System.out.println(i + "번째 reason_Cd? "+ reason_cd);
					
				}
				else{
					reason_cd = cell.getStringCellValue();
					System.out.println(i + "번째 reason_cd?" + reason_cd);
				}
				
				cell = row.getCell(8);
				remark_cn = cell.getStringCellValue();
				System.out.println(i + "번째 remark_cn ? " + remark_cn);
				
				
				
				LeadVO leadVo = new LeadVO();
				/*leadVo.setLead_no(lead_no);*/
				leadVo.setLead_name(lead_name);
				leadVo.setCust_no(cust_no);
				leadVo.setCust_name(cust_name);
				leadVo.setEmp_no(emp_no);
				leadVo.setUser_nm(user_nm); 
				leadVo.setContact_day(contact_day);
				leadVo.setLead_status_cd(lead_status_cd);
				leadVo.setReason_cd(reason_cd);
				leadVo.setRemark_cn(remark_cn);
				
				System.out.println("VO : " + leadVo);
				result += sqlSession.insert("lead.lead_multi_insert", leadVo);
				/*if(lead_no != null || leadVo.getLead_no() != null)
				{
					
				}	*/
			}
	  
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
		return result;
	}
	
	
	//Item
	@Override
	public List<InterestItemVO> leadItemList(String lead_no) {
		
		List<InterestItemVO> vo = sqlSession.selectList("lead.leadItemList", lead_no);
		
		
		return vo;
	} 
	@Override
	public int leadItemDelete(String lead_no) {
		int result = sqlSession.delete("lead.leadItemDelete", lead_no);
		
		return result;
	}

	@Override
	public int leadItemInsert(List<InterestItemVO> itemList) {
		// TODO Auto-generated method stub
				int result = 0;
				System.out.println("DAO : " + itemList);
				for(int i=0; i<itemList.size(); i++)
				{
					result = sqlSession.insert("lead.leadItemInsert", itemList.get(i));
				}
				
				return result;
	}

	//List
		@Override
		public List<TaskVO> taskList() {
			
			List<TaskVO> vo = sqlSession.selectList("lead.taskList");
			return vo;
		}

		//List 페이지
		@Override
		public List<TaskVO> taskList(Map<String, Object> taskMap) {
 			List<TaskVO> vo = sqlSession.selectList("lead.taskList", taskMap);
			return vo;
		}

		@Override
		public int getTaskListRow(Map<String, Object> map) {
			System.out.println("dao ? " + map.toString());
			int totalCount = 0;
			try {
				totalCount = sqlSession.selectOne("lead.taskListTotalRow", map);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return totalCount;
		}
		
		//조회
		@Override
		public List<TaskVO> taskSchList(Map<String, Object> taskMap) {
			
			List<TaskVO> vo = sqlSession.selectList("lead.taskList", taskMap);
			return vo;
		}
		
		//리드 상태코드
		@Override
		public List<LeadVO> leadStatusCode() {
			
			return sqlSession.selectList("lead.leadStatusCode");
		}

		@Override
		public List<LeadVO> leadOpptyCode() {
			
			return sqlSession.selectList("lead.leadOpptyCode");
		}

}
