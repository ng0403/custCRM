package com.core.plus.contact.cust.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.service.CommonCodeService;
import com.core.plus.contact.cust.service.CustAddrService;
import com.core.plus.contact.cust.service.CustPhoneService;
import com.core.plus.contact.cust.service.CustService;
import com.core.plus.contact.cust.vo.CommonCodeVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.oppty.service.OpptyService;
import com.core.plus.oppty.vo.OpptyVO;
import com.core.plus.task.vo.TaskVO;

@Controller
public class CustController {
	
	@Resource
	CustService custService;
	
	@Resource
	CustPhoneService custPhoneService;
	
	@Resource
	CustAddrService custAddrService;
	
	@Resource
	CommonCodeService commonCode;
	
	@Resource
	MenuService menuService;
	
	@Resource
	OpptyService opptyService;
	
	@RequestMapping(value="/cust")
	public ModelAndView custList(HttpSession session, 
							@RequestParam(value = "custPageNum", defaultValue = "1") int custPageNum)
	{
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		}
				
		Map<String, Object> custMap = new HashMap<String, Object>();
		custMap.put("custPageNum", custPageNum);
		
		// paging
		PagerVO page = custService.getCustListRow(custMap);
		System.out.println("cust page : " + page);
		custMap.put("page", page);
		
		List<CustVO> custList = custService.custList(custMap);

		List<CommonCodeVO> vititCdList = commonCode.vititCdList();
		List<CommonCodeVO> vititDtlCdList = commonCode.vititDtlCdList();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("cust_list");
		mav.addObject("page", page);
		mav.addObject("pageNum", custPageNum);
		mav.addObject("custList", custList);
		mav.addObject("vititCdList", vititCdList);
		mav.addObject("vititDtlCdList", vititDtlCdList);
		mav.addObject("vititDtlCdList", vititDtlCdList);
		mav.addObject("pageType", "0");		// my page 구분해주기 위한 flg (0: 기본 페이지 1: my page)
		
		// 메뉴
		mav.addObject("main_menu_url", "cust");
		mav.addObject("sub_menu_url", "cust");
		
		menuImport(mav, "cust");
		
		return mav; 
	}
	
	@RequestMapping(value="/my_cust")
	public ModelAndView myCustList(HttpSession session,
			@RequestParam(value = "custPageNum", defaultValue = "1") int custPageNum)
	{
		Map<String, Object> custMap = new HashMap<String, Object>();
		String user_id = null;
		
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		}
		else {
			user_id = session.getAttribute("user").toString();
			System.out.println("user_id : " + user_id);
			custMap.put("user_id", user_id);
		}
		
		custMap.put("custPageNum", custPageNum);
		
		// paging
		PagerVO page = custService.getCustListRow(custMap);
		custMap.put("page", page);
		
		List<CustVO> custList = custService.custList(custMap);

		List<CommonCodeVO> vititCdList = commonCode.vititCdList();
		List<CommonCodeVO> vititDtlCdList = commonCode.vititDtlCdList();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("cust_list");
		mav.addObject("page", page);
		mav.addObject("pageNum", custPageNum);
		mav.addObject("custList", custList);
		mav.addObject("vititCdList", vititCdList);
		mav.addObject("vititDtlCdList", vititDtlCdList);
		mav.addObject("pageType", "1");
		mav.addObject("session", user_id);
		mav.addObject("main_menu_url", "cust");
		mav.addObject("sub_menu_url", "my_cust");
		
		menuImport(mav, "my_cust");
		
		return mav; 
	}
	
	// 리스트 Ajax(조회, 페이징)
	@RequestMapping(value="/custAjax", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> custListAjax(@RequestParam(value = "custPageNum", defaultValue = "1") int custPageNum,
											String cust_no, String cust_name, String chart_no, String visit_cd, 
											String rec_per, String phone_no, String session)
	{
		Map<String, Object> result = new HashMap<String, Object>(0);
		Map<String, Object> custMap = new HashMap<String, Object>();

		custMap.put("custPageNum", custPageNum);
		custMap.put("cust_no", cust_no);
		custMap.put("cust_name", cust_name);
		custMap.put("chart_no", chart_no);
		custMap.put("visit_cd", visit_cd);
		custMap.put("rec_per", rec_per);
		custMap.put("phone_no", phone_no);
		custMap.put("user_id", session);

		// paging
		PagerVO page = custService.getCustListRow(custMap);
		custMap.put("page", page);
		
		System.out.println("custMap? " + custMap.toString());
		System.out.println("page? " + page);
		
		List<CustVO> custList = custService.custList(custMap);
		
		List<CommonCodeVO> vititCdList = commonCode.vititCdList();
		List<CommonCodeVO> vititDtlCdList = commonCode.vititDtlCdList();
		
		System.out.println("cust page : " + page);
		System.out.println("custList : " + custList);
		
		result.put("page", page);
		result.put("pageNum", custPageNum);
		result.put("custList", custList);
		result.put("vititCdList", vititCdList);
		result.put("vititDtlCdList", vititDtlCdList);
		
		return result;
	}
	
	//엑셀 출력 
	@RequestMapping(value = "/toCustExcel",  method=RequestMethod.POST)
	public ModelAndView toExcel(HttpServletRequest req, HttpSession session
			,String cust_no, String cust_name, String chart_no, String visit_cd, String rec_per, String phone_no, String flg) {
		
		char temp = flg.charAt(flg.length()-1);
		
		ModelAndView result = new ModelAndView();
		Map<String, Object> custkMap = new HashMap<String, Object> ();
		if(temp == '0')
		{
			custkMap.put("cust_no", cust_no);
			custkMap.put("cust_name", cust_name);
			custkMap.put("chart_no", chart_no);
			custkMap.put("visit_cd", visit_cd);
			custkMap.put("rec_per", rec_per);
			custkMap.put("phone_no", phone_no);
			//taskMap.put("some",req.getParameter("some"));    			// where에 들어갈 조건??
			
			System.out.println("custkMap??? "  + custkMap.toString());
			
			List<CustVO> list = custService.custExcelExport(custkMap);	// 쿼리
			result.addObject("custExcelExport", list); 					// 쿼리 결과를 model에 담아줌
			result.setViewName("/cust/custList_excel");					// 엑셀로 출력하기 위한 jsp 페이지
			
			return result;
		}
		else
		{
			result.setViewName("/cust/custList_excel");					// 엑셀로 출력하기 위한 jsp 페이지
			return result;
		}
	}

	@RequestMapping(value="/custForm")
	public ModelAndView custForm(@RequestParam("cust_no") String cust_no, 
			@RequestParam(value = "custPageNum", defaultValue = "1") int custPageNum, String page_type){
		
		List<CommonCodeVO> vititCdList = commonCode.vititCdList();
		List<CommonCodeVO> vititDtlCdList = commonCode.vititDtlCdList();
		List<CommonCodeVO> custTypeCdList = commonCode.custTypeCdList();
		List<CommonCodeVO> custRankCdList = commonCode.custRankCdList();
		
		List<CommonCodeVO> phoneTypeCdList = commonCode.phoneTypeCdList(); 			//전화번호구분
		List<CommonCodeVO> phoneCountryCdList = commonCode.phoneCountryCdList(); 	//국가번호
		List<CommonCodeVO> addrTypeCdList = commonCode.addrTypeCdList();			//주소구분
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("cust_detail");
		
		System.out.println("page_type : " + page_type);
		System.out.println("cust_type : " + custTypeCdList.toString());
		System.out.println("cust_rank : " + custRankCdList.toString());
		
		if(cust_no == null || cust_no == "" ){	// 단건등록
			
			mav.addObject("flg", "1");
			mav.addObject("custPageNum", custPageNum);
			
		}
		else if(cust_no != null || cust_no != ""){	// 상세보기
			
			CustVO custDlist = custService.custDetailList(cust_no);
			List<CustVO> custPList = custPhoneService.custPhoneDetailList(cust_no);
			List<CustVO> custAList = custAddrService.custAddrDetailList(cust_no);
			
			mav.addObject("flg", "2");
			
			mav.addObject("custDlist", custDlist);
			mav.addObject("custPList", custPList);
			mav.addObject("custAList", custAList);
			mav.addObject("custPageNum", custPageNum);
		}
		
		mav.addObject("vititCdList", vititCdList);
		mav.addObject("vititDtlCdList", vititDtlCdList);
		mav.addObject("custTypeCdList", custTypeCdList);
		mav.addObject("custRankCdList", custRankCdList);
		mav.addObject("phoneTypeCdList", phoneTypeCdList);
		mav.addObject("phoneCountryCdList", phoneCountryCdList);
		mav.addObject("addrTypeCdList", addrTypeCdList);
		mav.addObject("custPageNum", custPageNum);
		mav.addObject("page_type", page_type);
		mav.addObject("main_menu_url", "cust");
		mav.addObject("sub_menu_url", "cust");
		
		menuImport(mav, "cust");
		
		return mav;
	}
	
	@RequestMapping(value="/custSave", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public CustVO custSave(
//			@RequestParam(value="cust_list[]",required=false) List<String> cust_list
			CustVO cvoS, String cust_no){
		int result;
		CustVO custVO = null;
		
		System.out.println("Save : " + cvoS);
		
		if(cust_no == null || cust_no == ""){
			result = custService.custAdd(cvoS);
			System.out.println("result"+result);
			
			if(result == 1){
				String custNo = cvoS.getCust_no();
				custVO = custService.custDetailList(custNo);
				System.out.println("if문 안 custVO : "+custVO);
				
			}
		}
		else if(cust_no != null || cust_no != ""){
//			flg=1;
			cvoS.setCust_no(cust_no);
			
			System.out.println("mdfy : " + cvoS);
			result = custService.custMdfy(cvoS);
			if(result == 1){
				custVO = custService.custDetailList(cust_no);
			}
		}
		
		
		return custVO;
	}
	
	// 전화번호 등록
	@RequestMapping(value="/custPhoneSave", method=RequestMethod.POST)
	@ResponseBody
	public List<CustVO> custPhoneSave(
			@RequestParam(value="custPlist[]",required=false) List<String> custP_list
			,String cust_no){
		
		System.out.println("cust_no : " + cust_no);
		System.out.println("custP_list : " + custP_list);
		
		int Dresult = custPhoneService.custPhoneDelete(cust_no);
		
		//파라미터 리스트
		List<CustVO> custPH_list = new ArrayList<CustVO>();
		//반환 리스트
		List<CustVO> custPList;

		System.out.println(custP_list.size());
		
		if(custP_list != null){
			for(int i=0; i < custP_list.size(); i++){
				CustVO cvo = new CustVO();
				
				cvo.setCust_no(cust_no);
				cvo.setPhone_type_cd(custP_list.get(i));
				cvo.setPhone_country_cd(custP_list.get(++i));
				cvo.setPhone_area_no(custP_list.get(++i));
				cvo.setPhone_no(custP_list.get(++i));
				cvo.setPrimary_yn(custP_list.get(++i));

				custPH_list.add(cvo);
			}
			
			int Iresult = custPhoneService.custPhoneAdd(custPH_list);
		}
		custPList = custPhoneService.custPhoneDetailList(cust_no);
		
		return custPList;
	}
	
	// 주소등록
	@RequestMapping(value="/custAddrSave")
	@ResponseBody
	public List<CustVO> custAddrSave(
			@RequestParam(value="custAlist[]",required=false) List<String> custA_list
			,String cust_no){
		
		System.out.println("custA_list : " + custA_list);
		
		int Dresult = custAddrService.custAddrDelete(cust_no);
		
		//파라미터 리스트
		List<CustVO> custAD_list = new ArrayList<CustVO>();
		//반환 리스트
		List<CustVO> custAList;
		
		if(custA_list != null){
			for(int i=0; i < custA_list.size(); i++){
				CustVO cvo = new CustVO();
				
				cvo.setCust_no(cust_no);
				cvo.setAddr_type_cd(custA_list.get(i));
				cvo.setZip_no(custA_list.get(++i));
				cvo.setMain_address(custA_list.get(++i));
				cvo.setDetail_address(custA_list.get(++i));
				cvo.setPrimary_yn(custA_list.get(++i));
				
				custAD_list.add(cvo);
			}
			
			int result = custAddrService.custAddrAdd(custAD_list);
		}
		
		custAList = custAddrService.custAddrDetailList(cust_no);
		
		return custAList;
	}
	
	@RequestMapping(value="cust_delete", method=RequestMethod.POST)
	public @ResponseBody int custDelete(CustVO custVo, HttpSession session)
	{
		int result = 0;
		
		result = custService.custDelete(custVo);
		return result;
	}
	
	// Excel Data Import
    @RequestMapping(value="/custExcelUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody int custExcelForm(@RequestParam("excelFile") MultipartFile file) throws Exception
    {
    	int result = custService.excelUpload(file);
    	
    	return result;
    }

	public void menuImport(ModelAndView mav, String url){
		String menu_id = menuService.getMenuUrlID(url);
//		String user_id = session.getAttribute("user").toString();
	
		Map<String, String> menuAuthMap = new HashMap<String, String>();
		menuAuthMap.put("menu_url", url);
//		menuAuthMap.put("user_id", user_id);
		menuAuthMap.put("menu_id", menu_id);
//		MenuVo menuAuth = loginDao.getMenuAuthInfo(menuAuthMap);
//		mav.addObject("menuAuth", menuAuth);
			
		List<MenuVo> mainMenuList = menuService.getMainMenuList(/*user_id*/);
		List<MenuVo> subMenuList = menuService.getSubMenuList(menuAuthMap);
		mav.addObject("mainMenuList", mainMenuList);  //mainMenuList
		mav.addObject("subMenuList", subMenuList);    //subMenuList
	}
	
	// 담당자 팝업
	@RequestMapping(value="custEmpListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> empListPopup(@RequestParam(value = "empPopupPageNum", defaultValue = "1") int empPopupPageNum, String s_emp_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empPopupPageNum", empPopupPageNum);
		
		// paging
		PagerVO page = opptyService.getEmpPopupRow(map);
		map.put("page", page);
		map.put("pageNum", empPopupPageNum);
		
		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_emp_name == null || s_emp_name == "")
		{
			List<EmpVO> empPopupList = opptyService.empPopupList(map);
			map.put("empPopupList", empPopupList);
			
			
			return map;
		}
		else
		{
			map.put("s_emp_name", s_emp_name);
			List<EmpVO> schEmpPopupList = opptyService.empPopupList(map);
			map.put("empPopupList", schEmpPopupList);
			
			return map;
		}
	}	

}

