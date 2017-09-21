package com.core.plus.lead.controller;

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
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.lead.service.LeadService;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.oppty.service.OpptyService;
import com.core.plus.task.vo.TaskVO;

@Controller
public class LeadController {
	
	@Resource
	LeadService leadService;
	
	@Resource
	MenuService menuService;
	
	@Resource
	OpptyService opptyService;
	// git test
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
	
	
	//초기 list 출력
	@RequestMapping(value="lead")
	public ModelAndView lead_list(@RequestParam(value = "pageNum", defaultValue = "1") int PageNum) {
		System.out.println("entering" + PageNum);
		
		Map<String, Object> leadMap = new HashMap<String, Object>();
		leadMap.put("PageNum", PageNum);
		
		// paging
		PagerVO page = leadService.getLeadListRow(leadMap);
		leadMap.put("page", page); 
		
		System.out.println("page?? " + page.toString());
		
		List<LeadVO> vo = leadService.lead_list(leadMap);
		System.out.println("vovo ?? " + vo.toString());
		ModelAndView mov = new ModelAndView("lead_list");
		mov.addObject("page", page);
		mov.addObject("pageNum", PageNum);
		mov.addObject("lead_list", vo);
		mov.addObject("main_menu_url", "lead");
		
		menuImport(mov, "lead");
		
		System.out.println("mov ?  " + mov.toString());
		return mov;
		
	}
	
	//가망 고객 상세정보
	@RequestMapping(value="lead_detail", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView lead_detail(@RequestParam("lead_no") String lead_no, @RequestParam("pageNum") String PageNum){ 
	 
		System.out.println("lead_no : " + lead_no);
		
		
		ModelAndView mov = new ModelAndView("leadCRUD");
		mov.addObject("detail", leadService.lead_detail(lead_no));
		mov.addObject("nal","2017-08-09");
		mov.addObject("flg", "0");
		mov.addObject("PageNum", PageNum);
		mov.addObject("main_menu_url", "lead");
		System.out.println(mov.toString());
		
		menuImport(mov, "lead");
		return mov;
	}
	
	//가망 고객 추가 get.
	@RequestMapping(value="lead_single_add" , method=RequestMethod.GET)
	public ModelAndView lead_single_add_get(LeadVO vo) {
		System.out.println("single enter");
		
		LeadVO leadNoIndex	 = leadService.leadNoIndex();
 		ModelAndView mov = new ModelAndView("leadCRUD");
		mov.addObject("flg", "1");
		mov.addObject("PageNum", "1");
		mov.addObject("leadNoIndex", leadNoIndex);
		mov.addObject("main_menu_url", "lead");
		menuImport(mov, "lead");
		
		return mov;
	}
	 
	//가망 고객 추가 post.
	@RequestMapping(value="lead_single_add" , method=RequestMethod.POST)
	public String lead_single_add_post(LeadVO vo) {
		
		System.out.println("insert vo ? " +vo.toString());
		 
 		String cust_no = vo.getCust_no() ;
 		
		if(cust_no == null)
		{
			vo.setCust_no(" ");
		}
		
		leadService.lead_insert(vo);
 		
 		System.out.println("lead add success");
 		
		return "redirect:/lead";
	}
	
	//가망 고객 수정 get.
		@RequestMapping(value="lead_update" , method=RequestMethod.GET)
		public ModelAndView lead_update_get(@RequestParam("lead_no") String lead_no){
			 
			ModelAndView mov = new ModelAndView("leadCRUD");
			mov.addObject("detail", leadService.lead_detail(lead_no));
			mov.addObject("flg", "2");
			
			menuImport(mov, "lead");
			
			return mov;
		}
	
	//가망 고객 수정 post.
	@RequestMapping(value="lead_update" , method=RequestMethod.POST)
	public String lead_update_post(LeadVO vo){
		
		System.out.println("update vo ? " + vo.toString());
		
		String cust_no = vo.getCust_no();
		
		if(cust_no == null)
		{
			vo.setCust_no(" ");
		} 
		leadService.lead_update(vo); 
		
		return "redirect:/lead";
	}
	
	@RequestMapping(value="lead_delete", method=RequestMethod.POST)
	public String lead_delete(String lead_no){
		 
		leadService.lead_delete(lead_no);
		
		return "redirect:/lead";
	}
	
	//조건 검색
	@RequestMapping(value = "/searchKeyword", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> searchKeyword(
			@RequestParam(value = "PageNum", defaultValue = "1") int PageNum,
			String lead_no_srch,
			String lead_name_srch, String cust_name, String emp_name, String contact_day_srch, String rank_cd) {
	 
		String contact_day;
		
		contact_day = contact_day_srch.replace("-", "");
		 
		
		Map<String, Object> kwMap = new HashMap<String, Object>(); 
 		System.out.println("page num : " + PageNum);
 		kwMap.put("PageNum", PageNum);
		kwMap.put("lead_no_srch", lead_no_srch);
		kwMap.put("lead_name_srch", lead_name_srch);
		kwMap.put("cust_name", cust_name);
		kwMap.put("emp_name", emp_name);
		kwMap.put("contact_day", contact_day);
		kwMap.put("rank_cd", rank_cd);
		 
		// paging
	  PagerVO page = leadService.getLeadListRow(kwMap);
	 
	  kwMap.put("page", page); 
		
		List<LeadVO> leadList = leadService.leadSearch(kwMap);
		
		kwMap.put("leadList", leadList);
		 
		return kwMap;
	}
	
	//고객 팝업 리스트
	@RequestMapping(value="custPopListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> custListPopup(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, String s_cust_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custPopupPageNum", PageNum);
		
		PagerVO page = opptyService.getCustPopupRow(map);
		map.put("page", page);
		map.put("pageNum", PageNum);
		
		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_cust_name == null || s_cust_name == "")
		{
			List<CustVO> custPopupList = leadService.custPopupList(map);
			map.put("custPopupList", custPopupList);
			
 			return map;
		}
		else
		{
			map.put("s_cust_name", s_cust_name);
			List<CustVO> schCustPopupList = leadService.custPopupList(map);
			map.put("custPopupList", schCustPopupList); 
 			
			return map;
		}
	}
	
	//담당자 팝업 리스트
	@RequestMapping(value="empPopListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> empListPopup(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, String s_emp_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empPopupPageNum", PageNum);
		
		PagerVO page = opptyService.getEmpPopupRow(map);
		map.put("page", page);
		map.put("PageNum", PageNum);
		
		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_emp_name == null || s_emp_name == "")
		{
			List<EmpVO> empPopupList = leadService.empPopupList(map);
			map.put("empPopupList", empPopupList);
			
			return map;
		}
		else
		{
			map.put("s_emp_name", s_emp_name);
			List<EmpVO> schEmpPopupList = leadService.empPopupList(map);
			map.put("empPopupList", schEmpPopupList);
 			
			return map;
		}
	}
	
	
	//엑셀 출력 
	@RequestMapping(value = "/toLeadExcel",  method=RequestMethod.POST)
	public ModelAndView toExcel(HttpServletRequest req, HttpSession session, String lead_no_srch,
			String lead_name_srch, String cust_no, String emp_no, String contact_day_srch, String rank_cd, String flg) {
		
		String contact_day;
		
		contact_day = contact_day_srch.replace("-", "");
		 
		char temp = flg.charAt(flg.length()-1);
 		ModelAndView result = new ModelAndView();
		Map<String, Object> leadMap = new HashMap<String, Object> ();
		leadMap.put("lead_no_srch", lead_no_srch);
		leadMap.put("lead_name_srch", lead_name_srch);
		leadMap.put("cust_no", cust_no);
		leadMap.put("emp_no", emp_no);
		leadMap.put("contact_day", contact_day);
		leadMap.put("rank_cd", rank_cd);
		
		//taskMap.put("some",req.getParameter("some"));    			// where에 들어갈 조건??
		if(temp == '0'){
 			List<LeadVO> list = leadService.leadExcelExport(leadMap);	// 쿼리
	  
		
		System.out.println("excel list  ? " + list.toString());
		System.out.println("size? " + list.size());
		result.addObject("leadExcelExport", list); 					// 쿼리 결과를 model에 담아줌
		result.setViewName("/lead/leadList_excel");					// 엑셀로 출력하기 위한 jsp 페이지
		}
		else{
 			result.setViewName("/lead/leadList_excel");
		}
		
		return result;
	}
	
	//엑셀 추가 전 팝업
	@RequestMapping(value="/leadExcelImportTab", method=RequestMethod.GET)
	public ModelAndView excelImportTab(HttpSession session, Locale locale,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum)
	{
 		ModelAndView mov = new ModelAndView("/lead/excel_import_tab");
		
		return mov;
	}	
		
		
	// Excel Data Import
    @RequestMapping(value = "/leadExcelUploadAjax", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public ModelAndView excelUploadAjax(MultipartHttpServletRequest request)  throws Exception
    {
        MultipartFile excelFile = request.getFile("excelFile");
        System.out.println("excelFile : " + excelFile);
		
        System.out.println("lead 엑셀 파일 업로드 컨트롤러");
       
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        
        int result = leadService.excelUpload(excelFile);
        System.out.println(result);
        
        return new ModelAndView("/lead/excel_import_tab", "result", result);
    }	
    
    // Excel Data Import Ajax
    @RequestMapping(value="/leadExcelUpload", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody int opptyExcelForm(@RequestParam("excelFile") MultipartFile file) throws Exception 
    {
    	System.out.println("Excel Ajax : " + file);
		int result = leadService.excelUpload(file);
		
		return result;
	}

}
