package com.core.plus.lead.controller;

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
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.lead.service.LeadService;
import com.core.plus.lead.vo.InterestItemVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.oppty.service.OpptyService;
import com.core.plus.task.service.TaskService;
import com.core.plus.task.vo.TaskVO;

import net.sf.json.JSONArray;

@Controller
public class LeadController {
	
	@Resource
	LeadService leadService;
	
	@Resource
	MenuService menuService;
	
	@Resource
	OpptyService opptyService;
	
	@Resource
	TaskService taskService;
	
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
		mov.addObject("sub_menu_url", "lead");
		menuImport(mov, "lead");
		
		System.out.println("mov ?  " + mov.toString());
		return mov;
		
	}
	
	//내 담당 리드 list 출력
	@RequestMapping(value="my_lead")
	public ModelAndView my_lead_list(@RequestParam(value = "pageNum", defaultValue = "1") int PageNum) {
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
		mov.addObject("sub_menu_url", "my_lead");
		menuImport(mov, "lead");
		
		System.out.println("mov ?  " + mov.toString());
		return mov;
		
	}
	
	//리드 상태(보류, 전환, 실패) list 출력
		@RequestMapping(value="lead_status")
		public ModelAndView lead_status(@RequestParam(value = "pageNum", defaultValue = "1") int PageNum, @RequestParam(value="code") String code) {
			System.out.println("entering" + PageNum);
			
			Map<String, Object> leadMap = new HashMap<String, Object>();
			leadMap.put("PageNum", PageNum); 
			
			ModelAndView mov = new ModelAndView("lead_status"); 
			 
			//보류 
			if(code.equals("002"))
			{
			 leadMap.put("lead_status_cd", "002");
			 // paging
			 PagerVO page = leadService.getLeadStatusListRow(leadMap);
			 leadMap.put("page", page);  
			
			 List<LeadVO> vo = leadService.lead_status_list(leadMap);
			 mov.addObject("lead_list", vo);
			 mov.addObject("page", page);
			 mov.addObject("flg", "002");
			 mov.addObject("sub_menu_url", "lead_status?code=002");
			}
			//기회전환
			else if(code.equals("003")) 
			{
			  leadMap.put("lead_status_cd", "003");
			  PagerVO page = leadService.getLeadStatusListRow(leadMap);
		      leadMap.put("page", page); 
			  List<LeadVO> vo = leadService.lead_status_list(leadMap);
			  mov.addObject("lead_list", vo);		
			  mov.addObject("page", page);
			  mov.addObject("flg", "003");
			  mov.addObject("sub_menu_url", "lead_status?code=003");
			}
			//실패
			else if(code.equals("004")) 
			{
			 leadMap.put("lead_status_cd", "004");
			 PagerVO page = leadService.getLeadStatusListRow(leadMap);
			 leadMap.put("page", page); 
			 List<LeadVO> vo = leadService.lead_status_list(leadMap);
			 mov.addObject("lead_list", vo);
			 mov.addObject("page", page);
			 mov.addObject("flg", "004");
			 mov.addObject("sub_menu_url", "lead_status?code=004");
			} 
			
			mov.addObject("pageNum", PageNum); 
			mov.addObject("main_menu_url", "lead");
			menuImport(mov, "lead");
			mov.addObject("lead_status_cd", code); 
			System.out.println("mov ?  " + mov.toString());
			return mov;
			
		}
	
	//가망 고객 상세정보
	@RequestMapping(value="lead_detail", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView lead_detail(@RequestParam("lead_no") String lead_no, @RequestParam("pageNum") String PageNum){ 
	 
		System.out.println("lead_no : " + lead_no);
		List<InterestItemVO> itemList 	= leadService.leadItemList(lead_no);	// 관심상품 리스트 조회
		System.out.println("itemList ? " + itemList.toString());
		
		ModelAndView mov = new ModelAndView("leadCRUD");
		mov.addObject("detail", leadService.lead_detail(lead_no));
		mov.addObject("nal","2017-08-09");
		mov.addObject("flg", "0");
		mov.addObject("PageNum", PageNum);
		mov.addObject("main_menu_url", "lead");
		mov.addObject("itemList", itemList);
		mov.addObject("sub_menu_url", "lead");
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
			
			List<InterestItemVO> itemList 	= leadService.leadItemList(lead_no);	// 관심상품 리스트 조회
			
			ModelAndView mov = new ModelAndView("leadCRUD");
			mov.addObject("detail", leadService.lead_detail(lead_no));
			mov.addObject("flg", "2");
			mov.addObject("itemList", itemList);
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
	
	//조건 검색
		@RequestMapping(value = "/StatusSearchKeyword", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> StatusSearchKeyword(
				@RequestParam(value = "PageNum", defaultValue = "1") int PageNum,
				String lead_no_srch,
				String lead_name_srch, String cust_name, String emp_name, String contact_day_srch, String rank_cd, String lead_status_cd) {
		  System.out.println("satus entering");
		  
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
			kwMap.put("lead_status_cd", lead_status_cd);
			// paging
		  PagerVO page = leadService.getLeadStatusListRow(kwMap);
		 
		  kwMap.put("page", page); 
			
			List<LeadVO> leadList = leadService.leadStatusSearch(kwMap);
			
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
    
    
    /* Item CUD */
	// 상품추가
	@RequestMapping(value="leadItemInsert", method=RequestMethod.POST)
	public @ResponseBody List<InterestItemVO> leadItemInsert(@RequestParam(value="leadItemList[]", required=false) List<String> leadItemList, String lead_no)
	{
		System.out.println("Item Insert : " + leadItemList);
		System.out.println("Item Insert : " + lead_no);
		 
		List<InterestItemVO> itemList = new ArrayList<InterestItemVO>();
	 
		List<InterestItemVO> ditemList = leadService.leadItemList(lead_no);		// 관심상품 조회
 		if(ditemList == null)
		{
			System.out.println("list 없음.");
		}
		else		// 리스트가 존재하면 전부 삭제한다.
		{
			System.out.println("list");
			int result = leadService.leadItemDelete(lead_no);
		}
		
		if(leadItemList != null)
		{
 			for(int i=0; i<leadItemList.size(); i++)
			{ 
				InterestItemVO ovo = new InterestItemVO();
				
				ovo.setLead_no(lead_no);
				ovo.setMain_cate_cd(leadItemList.get(i));
				ovo.setMid_cate_cd(leadItemList.get(++i));
				ovo.setSmall_cate_cd(leadItemList.get(++i));
				ovo.setQty(Integer.parseInt(leadItemList.get(++i)));
				ovo.setList_price(Integer.parseInt(leadItemList.get(++i)));
 				itemList.add(ovo);
 			}
			System.out.println("itemList : " + itemList);
			// opptyItem Insert
			int oResult = leadService.leadItemInsert(itemList);	// 매출상품 추가
		}
		
		// 바로 detail 화면으로 뿌려준다.
//		List<OpptyVO> optyItemList = opptyService.opptyDetail(oppty_no);
		List<InterestItemVO> interItemList = leadService.leadItemList(lead_no);	// 조회 후 상세보기에 출력
		System.out.println("interItemList : " + interItemList);
		
		return interItemList;
	}
	
	
	// 상담 이력 리스트
		@RequestMapping(value="/cust_task")
		public ModelAndView TaskList(HttpSession session,
										@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
										String excel, String cust_no, String lead_no, String PageNum) {
			
			System.out.println("lead_no" + lead_no);
			Map<String, Object> taskMap = new HashMap<String, Object>();
			taskMap.put("taskPageNum", taskPageNum);
			taskMap.put("cust_no", cust_no);
			
			// paging
			PagerVO page = taskService.getTaskListRow(taskMap);
			taskMap.put("page", page);
			
			List<TaskVO> taskList = leadService.taskList(taskMap);		// 전체 리스트
			List<TaskVO> dtypeCd  = taskService.taskDtypeCD();			// 분류코드
			List<TaskVO> scoreCd  = taskService.taskScoreCD();			// 상대가치점수
			List<TaskVO> ttypeCd = taskService.taskTtypeCD();			// 상담유형
			List<TaskVO> divisCd = taskService.taskDivisCD();			// 상담구분
			
			ModelAndView mov = new ModelAndView("cust_task_list");
			
			mov.addObject("page", page);
			mov.addObject("taskPageNum", taskPageNum);
			mov.addObject("cust_no", cust_no);
			mov.addObject("taskList", taskList);
			mov.addObject("dtypeCd", dtypeCd);
			mov.addObject("scoreCd", scoreCd);
			mov.addObject("ttypeCd", ttypeCd);
			mov.addObject("divisCd", divisCd);
			mov.addObject("lead_no", lead_no);
			mov.addObject("PageNum", PageNum);
		/*	mov.addObject("main_menu_url", "task");
			mov.addObject("sub_menu_url", "task");
			menuImport(mov, "task");*/
			menuImport(mov, "lead");
			mov.addObject("main_menu_url", "lead");
			mov.addObject("sub_menu_url", "lead");
			return mov;
		}
		
		
		// 상담이력 조회
		@RequestMapping(value="/cust_task_sch", method=RequestMethod.POST)
		@ResponseBody
		public  ModelAndView taskSchList(HttpSession session,
											  @RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
											  String task_no_srch, String subject_srch, 
											  String cust_name_srch, String emp_name_srch,
											  String next_day_srch, String dtype_cd_srch, String cust_no,
											  String excel) {
			
			ModelAndView mov = new ModelAndView(new MappingJacksonJsonView());
			JSONArray json = new JSONArray();
			
			Map<String, Object> taskMap = new HashMap<String, Object>();
			
			taskMap.put("taskPageNum", taskPageNum);
			taskMap.put("task_no_srch", task_no_srch);
			taskMap.put("subject_srch", subject_srch);
			taskMap.put("cust_name_srch", cust_name_srch);
			taskMap.put("emp_name_srch", emp_name_srch);
			taskMap.put("next_day_srch", next_day_srch);
			taskMap.put("dtype_cd_srch", dtype_cd_srch);
			taskMap.put("cust_no", cust_no);
			// paging
			PagerVO page = leadService.getTaskListRow(taskMap);
			taskMap.put("page", page);
			
			List<TaskVO> srcList = leadService.taskSchList(taskMap);
			taskMap.put("srcList", srcList);
					
			mov.addObject("page", page);
			mov.addObject("taskPageNum", taskPageNum);
			mov.addObject("srcList", srcList);
			mov.addObject("main_menu_url", "lead");
			return mov;
		}

}
