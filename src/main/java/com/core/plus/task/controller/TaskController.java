package com.core.plus.task.controller;

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
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.oppty.vo.OpptyVO;
import com.core.plus.task.service.TaskService;
import com.core.plus.task.vo.TaskVO;

import net.sf.json.JSONArray;

@Controller
public class TaskController {

	
	@Resource
	TaskService taskService;
	
	@Resource
	MenuService menuService;
	
	//메뉴
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
	
	// List
	@RequestMapping(value="/task")
	public ModelAndView TaskList(HttpSession session,
									@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
									String excel, String cust_task_no) {
		
 		Map<String, Object> taskMap = new HashMap<String, Object>();
		taskMap.put("taskPageNum", taskPageNum);
		taskMap.put("cust_no", cust_task_no);
		
		// paging
		PagerVO page = taskService.getTaskListRow(taskMap);
		taskMap.put("page", page);
		
		List<TaskVO> taskList = taskService.taskList(taskMap);		// 전체 리스트
		List<TaskVO> dtypeCd  = taskService.taskDtypeCD();			// 분류코드
		List<TaskVO> scoreCd  = taskService.taskScoreCD();			// 상대가치점수
		List<TaskVO> ttypeCd = taskService.taskTtypeCD();			// 상담유형
		List<TaskVO> divisCd = taskService.taskDivisCD();			// 상담구분
		
//		System.out.println("cust_task_no : " + cust_task_no);
//		System.out.println("taskList : " + taskList);
		
		ModelAndView mov = new ModelAndView("task_list");
		
		mov.addObject("page", page);
		mov.addObject("taskPageNum", taskPageNum);
		mov.addObject("taskList", taskList);
		mov.addObject("dtypeCd", dtypeCd);
		mov.addObject("scoreCd", scoreCd);
		mov.addObject("ttypeCd", ttypeCd);
		mov.addObject("divisCd", divisCd);
		mov.addObject("cust_task_no", cust_task_no);
		
		if(cust_task_no == null)
		{
			mov.addObject("main_menu_url", "task");
			mov.addObject("sub_menu_url", "task");
			menuImport(mov, "task");
		}
		if(cust_task_no != null)
		{
			if(cust_task_no.equals("undefined") || cust_task_no.equals(" "))
			{
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task");
				menuImport(mov, "task");
			}
			else if(cust_task_no.equals(null))
			{
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task");
				menuImport(mov, "task");
			}
			else
			{
				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
				menuImport(mov, "cust");
			}
		}
		
		return mov;
	}
	
	// My List
	@RequestMapping(value="/mytask")
	public ModelAndView MyTaskList(HttpSession session,
									@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
									 				HttpServletRequest request, String excel ) {
		
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		}
		String my_user_id = session.getAttribute("user").toString(); 
		
		//url 가져오기
		String Url = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE); 
		
		Map<String, Object> taskMap = new HashMap<String, Object>();
		taskMap.put("taskPageNum", taskPageNum);
		taskMap.put("my_user_id", my_user_id);
		
		// paging
		PagerVO page = taskService.getTaskListRow(taskMap);
		taskMap.put("page", page);
		
		List<TaskVO> taskList = taskService.taskList(taskMap);		// 전체 리스트
		List<TaskVO> dtypeCd  = taskService.taskDtypeCD();			// 분류코드
		List<TaskVO> scoreCd  = taskService.taskScoreCD();			// 상대가치점수
		List<TaskVO> ttypeCd = taskService.taskTtypeCD();			// 상담유형
		List<TaskVO> divisCd = taskService.taskDivisCD();			// 상담구분
		
		ModelAndView mov = new ModelAndView("task_list");
		
		mov.addObject("page", page);
		mov.addObject("taskPageNum", taskPageNum);
		mov.addObject("taskList", taskList);
		mov.addObject("dtypeCd", dtypeCd);
		mov.addObject("scoreCd", scoreCd);
		mov.addObject("ttypeCd", ttypeCd);
		mov.addObject("divisCd", divisCd);
		mov.addObject("main_menu_url", "task");
		mov.addObject("sub_menu_url", "mytask");
		mov.addObject("session", my_user_id);
		mov.addObject("url", Url);
		menuImport(mov, "task");
		
		return mov;
	}
		
	// 조회
	@RequestMapping(value="/task_sch", method=RequestMethod.POST)
	@ResponseBody
	public  ModelAndView taskSchList(
//										HttpSession session,
										  @RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
										  String task_no_srch, String subject_srch, 
										  String cust_name_srch, String emp_name_srch,
										  String next_day_srch, String dtype_cd_srch, String excel 
										, String session) {
		
		ModelAndView mov = new ModelAndView(new MappingJacksonJsonView());
		JSONArray json = new JSONArray();
		
		Map<String, Object> taskMap = new HashMap<String, Object>();
		
		taskMap.put("taskPageNum", taskPageNum);
		taskMap.put("task_no_srch", task_no_srch);
		taskMap.put("subject_srch", subject_srch);
		taskMap.put("cust_name_srch", cust_name_srch);
		taskMap.put("emp_name_srch", emp_name_srch);
		taskMap.put("next_day_srch", next_day_srch);
		taskMap.put("my_user_id", session); 
		
		// paging
		PagerVO page = taskService.getTaskListRow(taskMap);
		taskMap.put("page", page);
		
		List<TaskVO> srcList = taskService.taskSchList(taskMap);
		taskMap.put("srcList", srcList);
				
		mov.addObject("page", page);
		mov.addObject("taskPageNum", taskPageNum);
		mov.addObject("srcList", srcList);
		
		return mov;
	}
	
	//엑셀 출력 
	@RequestMapping(value = "/toExcel",  method=RequestMethod.POST)
	public ModelAndView toExcel(HttpServletRequest req, HttpSession session, 
									String task_no_srch,  String subject_srch,  String cust_name_srch, 
									String emp_name_srch, String next_day_srch, String dtype_cd_srch, String flg, 
									String cst_num, String cust_task_no, String my_user_id ) {
		
		System.out.println("cst_num ? " + cst_num);
		String custtmp;

//		System.out.println(flg);
//		System.out.println(cust_task_no);
//		System.out.println(cst_num);
 		char temp = flg.charAt(flg.length()-1);
		
 		
 		ModelAndView result = new ModelAndView();
		Map<String, Object> taskMap = new HashMap<String, Object> ();
		
		if(cust_task_no != null)
		{
			taskMap.put("cust_task_no", cust_task_no);
		}
		
		if(temp == '0')
		{
			// 검색 조건
			taskMap.put("task_no_srch", task_no_srch);
			taskMap.put("subject_srch", subject_srch);
			taskMap.put("cust_name_srch", cust_name_srch);
			taskMap.put("emp_name_srch", emp_name_srch);
			taskMap.put("next_day_srch", next_day_srch);
			taskMap.put("dtype_cd_srch", dtype_cd_srch);

			if(my_user_id.contains(","))
			{
				String[] id_user = my_user_id.split(",");
				my_user_id = id_user[0].toString();
 				 taskMap.put("my_user_id", my_user_id);
			}
			else{
				 taskMap.put("my_user_id", my_user_id);
			}
			
			//form 지속적인 append로 cust_no 스트링 자르기.
			
			if(cst_num != null){
				System.out.println("enter2");
				if(cst_num.contains(",")&& cst_num.equals("undefined")){
					cst_num = "";
					taskMap.put("cust_no", cst_num);
				} else{
				  String[] num_cst = cst_num.split(",");
				  cst_num = num_cst[0].toString();
				  taskMap.put("cust_no", cst_num);
				}
				/*custtmp = cst_num.substring(0, 15);
				System.out.println(custtmp);
				taskMap.put("cust_no", custtmp);*/
			}else{
				System.out.println("enter1");
				custtmp = "";
			}
			
			//taskMap.put("some",req.getParameter("some"));    			// where에 들어갈 조건??
			System.out.println("task map ? " + taskMap.toString()); 
			List<TaskVO> list = taskService.taskExcelExport(taskMap);	// 쿼리
//			System.out.println("list ?? " + list.toString());
//			System.out.println("taskMap"+ taskMap.toString());
			result.addObject("taskExcelExport", list); 					// 쿼리 결과를 model에 담아줌
			result.setViewName("/task/taskList_excel");					// 엑셀로 출력하기 위한 jsp 페이지
			
			return result;
		}
		else
		{
			result.setViewName("/task/taskList_excel");						// 엑셀로 출력하기 위한 jsp 페이지
			
			return result;
		}
		
	}
	
	// 상세보기 및 단건등록화면
	@RequestMapping(value="task_detail")
	public ModelAndView taskDetail(@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
									String task_no, String flg, String lead_no, String cust_no, String PageNum) {
//		System.out.println("PageNum ? "  + PageNum);
		if(task_no == null || task_no == "")	// 단건등록 시
		{
			TaskVO taskNoIndex	 = taskService.taskNoIndex();			// 인덱스번호
			List<TaskVO> dtypeCd = taskService.taskDtypeCD();			// 분류코드
			List<TaskVO> scoreCd = taskService.taskScoreCD();			// 상대가치점수
			List<TaskVO> ttypeCd = taskService.taskTtypeCD();			// 상담유형
			List<TaskVO> divisCd = taskService.taskDivisCD();			// 상담구분
						

			ModelAndView mov = new ModelAndView("task_detail");

			mov.addObject("taskNoIndex", taskNoIndex);
			mov.addObject("dtypeCd", dtypeCd);
			mov.addObject("scoreCd", scoreCd);
			mov.addObject("ttypeCd", ttypeCd);
			mov.addObject("divisCd", divisCd);
			mov.addObject("flg", "1");
			mov.addObject("taskPageNum", taskPageNum);
			mov.addObject("main_menu_url", "task");
			mov.addObject("sub_menu_url", "task");
			menuImport(mov, "task");
			
			return mov;
		}
		else	// 상세보기	
		{
//			System.out.println("lead_no ? " + lead_no);

			List<TaskVO> dtypeCd  = taskService.taskDtypeCD();			// 분류코드
			List<TaskVO> scoreCd  = taskService.taskScoreCD();			// 상대가치점수
			List<TaskVO> ttypeCd = taskService.taskTtypeCD();			// 상담유형
			List<TaskVO> divisCd = taskService.taskDivisCD();			// 상담구분
			
			ModelAndView mov = new ModelAndView("task_detail");
			
			//
			if(lead_no.equals("undefined"))
			{
//				System.out.println("null");
				mov.addObject("main_menu_url", "task"); 
				mov.addObject("sub_menu_url", "task");
				menuImport(mov, "task");
			}
			else 
			{
//				System.out.println("not null");
				mov.addObject("main_menu_url", "lead");
				mov.addObject("sub_menu_url", "lead");
				mov.addObject("lead_no", lead_no);
				mov.addObject("cust_no", cust_no);
				mov.addObject("PageNum", PageNum); 
				menuImport(mov, "lead");
			}
			
			mov.addObject("taskDetail",  taskService.taskDetail(task_no));
			mov.addObject("dtypeCd", dtypeCd);
			mov.addObject("scoreCd", scoreCd);
			mov.addObject("ttypeCd", ttypeCd);
			mov.addObject("divisCd", divisCd);
			mov.addObject("flg", "2");
			mov.addObject("taskPageNum", taskPageNum);
			
//			System.out.println(taskService.taskDetail(task_no));
			
			return mov;
		}
	}
	
	// 추가
	@RequestMapping(value="task_single_add", method=RequestMethod.POST)
	public @ResponseBody int taskSingleInsert(TaskVO taskVo, HttpSession session, HttpServletRequest request,
												@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum) {
		int flg=1;
		int result = 0;
		result = taskService.taskInsert(taskVo);
		
		return 0;
	}
	
	// 수정
	@RequestMapping(value="task_edit", method=RequestMethod.POST)
	public @ResponseBody int taskEdit(TaskVO taskVo, HttpSession session,
										@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum) {
		
		int result = 0;
		int flg=2;
		
		result = taskService.taskEdit(taskVo);

		return result;
	}
	
	// 삭제
	@RequestMapping(value="task_delete", method=RequestMethod.POST)
	public @ResponseBody int taskDelete(TaskVO taskVo, HttpSession session,
											@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum) {
		
		int result = 0;
		
		result = taskService.taskDelete(taskVo);
		
		return result;
	}
	
	/* Popup*/
	@RequestMapping(value="taskCustListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskCustListPopup(@RequestParam(value = "custPopupPageNum", defaultValue = "1") 
																int custPopupPageNum, String s_cust_name) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custPopupPageNum", custPopupPageNum);
		
		// paging
		PagerVO page = taskService.getTaskPopupRow(map);
		map.put("page", page);
		map.put("pageNum", custPopupPageNum);
		
		// 고객리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_cust_name == null || s_cust_name == "") {
			
			List<CustVO> custPopupList = taskService.custPopupList(map);
			
			map.put("custPopupList", custPopupList);
			
			return map;
			
		} else {
			
			map.put("s_cust_name", s_cust_name);
			
			List<CustVO> schCustPopupList = taskService.custPopupList(map);
			
			map.put("custPopupList", schCustPopupList);
			
			return map;
		}
	}
	
	@RequestMapping(value="taskEmpListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskEmpListPopup(@RequestParam(value = "empPopupPageNum", defaultValue = "1") 
																int empPopupPageNum, String s_emp_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empPopupPageNum", empPopupPageNum);
		
		// paging
		PagerVO page = taskService.getEmpPopupRow(map);
		map.put("page", page);
		map.put("pageNum", empPopupPageNum);
		
		
		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_emp_name == null || s_emp_name == "") {
			
			List<EmpVO> empPopupList = taskService.empPopupList(map);
			
			map.put("empPopupList", empPopupList);
			
			return map;
			
		} else {
			
			map.put("s_emp_name", s_emp_name);
			
			List<EmpVO> schEmpPopupList = taskService.empPopupList(map);
			
			map.put("empPopupList", schEmpPopupList);
			
			return map;
		}
	}
	
	
	@RequestMapping(value="taskLeadListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskLeadListPopup(@RequestParam(value = "leadPopupPageNum", defaultValue = "1") 
																int leadPopupPageNum, String s_lead_name) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leadPopupPageNum", leadPopupPageNum);
		
		// paging
		PagerVO page = taskService.getLeadPopupRow(map);
		map.put("page", page);
		map.put("pageNum", leadPopupPageNum);
		
		// 가망고객리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_lead_name == null || s_lead_name == ""){
			
			List<LeadVO> leadPopupList = taskService.leadPopupList(map);
			
			map.put("leadPopupList", leadPopupList);
			
			return map;
			
		} else {
			
			map.put("s_lead_name", s_lead_name);
			
			List<LeadVO> schLeadPopupList = taskService.leadPopupList(map);
			
			map.put("leadPopupList", schLeadPopupList);
			
			return map;
		}
	}
	
	@RequestMapping(value="taskOpptyListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskOpptyListPopup(@RequestParam(value = "opptyPopupPageNum", defaultValue = "1") 
																	int opptyPopupPageNum, String s_oppty_name) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opptyPopupPageNum", opptyPopupPageNum);
		
		// paging
		PagerVO page = taskService.getOpptyPopupRow(map);
		map.put("page", page);
		map.put("pageNum", opptyPopupPageNum);
		
		// 영업기회 리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_oppty_name == null || s_oppty_name == "") {
			
			List<OpptyVO> opptyPopupList = taskService.opptyPopupList(map);
			
			map.put("opptyPopupList", opptyPopupList);
			
			return map;
		} else {
			
			map.put("s_oppty_name", s_oppty_name);
			
			List<OpptyVO> schOpptyPopupList = taskService.opptyPopupList(map);
			
			map.put("opptyPopupList", schOpptyPopupList);
			
			return map;
		}
	}
	
	//엑셀 추가 전 팝업
	@RequestMapping(value="/taskExcelImportTab", method=RequestMethod.GET)
	public ModelAndView excelImportTab(HttpSession session, Locale locale,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum)
	{
//		System.out.println("ExcelTab Controller");
		ModelAndView mov = new ModelAndView("/lead/excel_import_tab");
		
		return mov;
	}	
		
	// Excel Data Import
    @RequestMapping(value = "/taskExcelUploadAjax", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public ModelAndView excelUploadAjax(MultipartHttpServletRequest request)  throws Exception
    {
        MultipartFile excelFile = request.getFile("excelFile");
//        System.out.println("excelFile : " + excelFile);
		
//        System.out.println("엑셀 파일 업로드 컨트롤러");
       
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        
        int result = taskService.excelUpload(excelFile);
//        System.out.println(result);
        
        return new ModelAndView("/task/excel_import_tab", "result", result);
    }	
    
    // Excel Data Import Ajax
    @RequestMapping(value="/taskExcelUpload", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody int taskExcelForm(@RequestParam("excelFile") MultipartFile file) throws Exception 
    {
//    	System.out.println("Excel Ajax : " + file);
		int result = taskService.excelUpload(file);
		
		return result;
	}
    
}	
