package com.core.plus.contact.cust.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.task.vo.TaskVO;

@Repository
public class CustDAOImpl implements CustDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CustVO> custList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<CustVO> custList = sqlSession.selectList("cust.custList", map);
		
		return custList;
	}

	@Override
	public CustVO custDetailList(String cust_no) {
		// TODO Auto-generated method stub
		CustVO custDList = sqlSession.selectOne("cust.custDetailList", cust_no);
		
		return custDList;
	}

	@Override
	public int custAdd(CustVO cvo) {
		// TODO Auto-generated method stub
		int result = sqlSession.insert("cust.custInsert", cvo);
		return result;
	}

	@Override
	public int custMdfy(CustVO cvo) {
		// TODO Auto-generated method stub
		int result = sqlSession.update("cust.custUpdate", cvo);
		return result;
	}

	@Override
	public int getCustListRow(Map<String, Object> custMap) {
		// TODO Auto-generated method stub
		int totalCount = 0;
		
		String phone = (String) custMap.get("phone_no");
		
		System.out.println("DAO : " + phone);
		
		try {
			totalCount = sqlSession.selectOne("cust.custListTotalRow", custMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return totalCount;
	}

	@Override
	public int custDelete(CustVO custVo) {
		// TODO Auto-generated method stub
		int result = sqlSession.update("cust.custDelete", custVo);
		return result;
	}

	@Override
	public int custUploadExcel(MultipartFile destFile) {
		// TODO Auto-generated method stub
		System.out.println("Excel Upload Dao");
		int result = 0;
		
		// excel import
		try {
			Workbook workBook = WorkbookFactory.create(destFile.getInputStream());
			Sheet sheet = workBook.getSheetAt(0);
			Row row = null;
			Cell cell = null;
			
			// 컬럼을 담을 변수를 생성
			String cust_no = null;			// 0
			String cust_name = null;
			String resident_no = null;
			String chart_no = null;
			String cust_id = null;
			String visit_cd = null;
			String visit_dtl_cd = null;
			String visit_cn = null;
			String rec_per = null;
			String cust_rank = null;
			String cust_type = null;
			String remark_cn = null;
			
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("rows : " + rows);
			
			for(int i=1; i<rows; i++)
			{
				row = sheet.getRow(i);
				
//				cell = row.getCell(0);	// cust_no
//				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
//				{
//					cell.setCellType(Cell.CELL_TYPE_STRING);
//					cust_no = cell.getStringCellValue().trim();
//					
//					System.out.println("cust_no : " + cust_no);
//				}
				
				cell = row.getCell(0);	// cust_name
				cust_name = cell.getStringCellValue().trim();
				System.out.println("cust_name : " + cust_name);
				
				cell = row.getCell(1);	// resident_no
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					cell.setCellType(Cell.CELL_TYPE_STRING);
					resident_no = cell.getStringCellValue().trim();
					
					System.out.println("resident_no : " + resident_no);
				}
				
				cell = row.getCell(2);	// chart_no
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					cell.setCellType(Cell.CELL_TYPE_STRING);
					chart_no = cell.getStringCellValue().trim();
					
					System.out.println("chart_no : " + chart_no);
				}
				
				cell = row.getCell(3);	// cust_id
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cust_id = cell.getStringCellValue().trim();
					
					System.out.println("cust_id : " + cust_id);
				}
				
				cell = row.getCell(4);	
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					visit_cd = String.format("%03d", tmp);
					
					System.out.println("visit : " + visit_cd);
				}
				else
				{
					visit_cd = cell.getStringCellValue().trim();
				}
				
				cell = row.getCell(5);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					visit_dtl_cd = String.format("%03d", tmp);
				}
				else
				{
					visit_dtl_cd = cell.getStringCellValue().trim();
				}
				
				cell = row.getCell(6);	// visit_cn
				visit_cn = cell.getStringCellValue().trim();
				
				cell = row.getCell(7);	// cust_rank
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					cust_rank = String.format("%03d", tmp);
					
					System.out.println("cust_rank : " + cust_rank);
				}
				else
				{
					cust_rank = cell.getStringCellValue().trim();
				}
				
				cell = row.getCell(8);	// cust_type
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					int tmp = (int) cell.getNumericCellValue();
					cust_type = String.format("%03d", tmp);
					
					System.out.println("cust_type : " + cust_type);
				}
				else
				{
					cust_type = cell.getStringCellValue().trim();
				}
				
				cell = row.getCell(9);	// rec_per
				rec_per = cell.getStringCellValue().trim();
				
				cell = row.getCell(10);	// remark_cn
				remark_cn = cell.getStringCellValue().trim();

				// VO
				CustVO custVo = new CustVO();
//				custVo.setCust_no(cust_no);
				custVo.setCust_name(cust_name);
				custVo.setResident_no(resident_no);
				custVo.setChart_no(chart_no);
				custVo.setCust_id(cust_id);
				custVo.setVisit_cd(visit_cd);
				custVo.setVisit_dtl_cd(visit_dtl_cd);
				custVo.setVisit_cn(visit_cn);
				custVo.setRec_per(rec_per);
				custVo.setCust_rank(cust_rank);
				custVo.setCust_type(cust_type);
				custVo.setRemark_cn(remark_cn);
				
				System.out.println("VO : " + custVo);
				
				// 이름 / 주민번호를 확인하는 쿼리 필요.(중복체크)
				// check 변수(int) 카운트? 0, 1
				int check = sqlSession.selectOne("cust.custDuplicate", custVo);	// count 있으면 1 / 0
				System.out.println(check);
				
				if(check == 1)	// 존재하는 회원
				{
					result += sqlSession.update("cust.custExcelUpdate", custVo);
				}
				else			// 신규 회원
				{
					result += sqlSession.insert("cust.custExcelInsert", custVo);
				}
				
			}
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	// 엑셀 출력
	@Override
	public List<CustVO> custExcelExport(Map<String, Object> custkMap) {
		
		List<CustVO> custExcelExport = null;
		try {
			custExcelExport = sqlSession.selectList("cust.custExcelExport", custkMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custExcelExport;
	}

	/**
	 * 청구/수금 리스트 목록
	 * */
	@Override
	public CustVO optyItemAmount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		CustVO optyItemAmount = sqlSession.selectOne("cust.optyAmount", map);
		System.out.println("DAO : " + optyItemAmount);
		return optyItemAmount;
	}
	
	@Override
	public List<CustVO> optyItemAmountList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<CustVO> optyItemAmount = sqlSession.selectList("cust.optyAmount", map);
		System.out.println("DAO : " + optyItemAmount);
		return optyItemAmount;
	}
	
	/**
	 * 납부 상품 리스트
	 * */
	@Override
	public List<CustVO> salesList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<CustVO> salesList = sqlSession.selectList("cust.salesList", map);
		System.out.println("DAO : " + salesList);
		return salesList;
	}
	
	/**
	 * 납부금액 등록
	 * */
	@Override
	public int optyPaymentAdd(CustVO cvo) {
		// TODO Auto-generated method stub
		System.out.println("Insert");
		int result = sqlSession.insert("cust.paymentInsert", cvo);
		return result;
	}

	/**
	 * 납부금액 이력 등록
	 * */
	@Override
	public int optyPaymentRecordAdd(CustVO cvo) {
		// TODO Auto-generated method stub
		int result = sqlSession.insert("cust.paymentRecordInsert", cvo);
		return result;
	}

	/**
	 * 납부를 했을 때 수정되는 메소드
	 * 
	 * 납부금액이 수정이 되고 미수금이 0이 되면
	 * payment_flg 값을 Y로 변경해준다.
	 * */
	@Override
	public int optyPaymentMdfy(CustVO cvo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		int result = sqlSession.update("cust.paymentUpdate", cvo);
		
		if(result == 1)
		{
			map.put("cust_no", cvo.getCust_no());
			map.put("oppty_no", cvo.getOppty_no());
			
			List<CustVO> tmp = sqlSession.selectList("cust.paymentFlgUpdateList", map);
			System.out.println("TT : " + tmp.get(0).getOutstanding_amount());
			
			if(tmp.get(0).getOutstanding_amount() == 0)
			{
				cvo.setPayment_flg("Y");
				System.out.println("0 : " + cvo);
				sqlSession.update("cust.paymentFlgUpdate", cvo);
			}
			else
			{
				cvo.setPayment_flg("N");
				System.out.println("1 : " + cvo);
				sqlSession.update("cust.paymentFlgUpdate", cvo);
			}
		}
		
		return result;
	}

}
