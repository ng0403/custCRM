package com.core.plus.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.controller.MenuController;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.login.dao.LoginDAO;
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

	@Resource
	MenuController menuControlleri;

	@Resource
	LoginDAO loginDao;

	@Autowired
	private HttpSession session;

	/**
	 * 상담 리스트(시작)
	 * 
	 * task_code : 내 담당 리스트 코드
	 */
	@RequestMapping(value = "/task")
	public ModelAndView TaskList(HttpSession session,
			@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum, String excel, String cust_task_no,
			String task_code, String lead_no, String lead_code) {

		System.out.println("task enter" + cust_task_no);

		// session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		}

		Map<String, Object> taskMap = new HashMap<String, Object>();
		String user_id = null;

		if (task_code != null) {
			user_id = session.getAttribute("user").toString();
			taskMap.put("user_id", user_id);
		}

		taskMap.put("taskPageNum", taskPageNum);
		taskMap.put("cust_no", cust_task_no);

		// paging
		PagerVO page = taskService.getTaskListRow(taskMap);
		taskMap.put("page", page);

		List<TaskVO> taskList = taskService.taskList(taskMap); // 전체 리스트
		List<TaskVO> dtypeCd = taskService.taskDtypeCD(); // 분류코드
		List<TaskVO> scoreCd = taskService.taskScoreCD(); // 상대가치점수
		List<TaskVO> ttypeCd = taskService.taskTtypeCD(); // 상담유형
		List<TaskVO> divisCd = taskService.taskDivisCD(); // 상담구분

		ModelAndView mov = new ModelAndView("task_list");

		mov.addObject("page", page);
		mov.addObject("taskPageNum", taskPageNum);
		mov.addObject("taskList", taskList);
		mov.addObject("dtypeCd", dtypeCd);
		mov.addObject("scoreCd", scoreCd);
		mov.addObject("ttypeCd", ttypeCd);
		mov.addObject("divisCd", divisCd);
		mov.addObject("cust_task_no", cust_task_no);
		mov.addObject("pageType", "0"); // my page 구분해주기 위한 flg (0: 기본 페이지 1: my
										// page)

		if (cust_task_no != null) {
			System.out.println("cust_task_no " + cust_task_no);
			// 준석 추가.
			if (lead_no == null) {
				System.out.println("lead" + lead_no);
			 
					mov.addObject("cust_task_no", cust_task_no);
					mov.addObject("main_menu_url", "cust");
					mov.addObject("sub_menu_url", "cust");
					menuControlleri.menuImport(mov, "cust");
			 
			} else {
				System.out.println("cust_task_no1 " + cust_task_no);
				if (lead_code.isEmpty()) {
					System.out.println("lead detail : empty" + lead_code);
					mov.addObject("main_menu_url", "lead");
					mov.addObject("sub_menu_url", "lead");
					mov.addObject("lead_no", lead_no);
					mov.addObject("lead_code", lead_code);
					mov.addObject("flg", "0");
					menuControlleri.menuImport(mov, "lead");
				} else {
					System.out.println("lead detail : not empty" + lead_code);
					mov.addObject("main_menu_url", "lead");
					mov.addObject("sub_menu_url", "lead?lead_code=" + lead_code);
					mov.addObject("flg", "0");
					mov.addObject("lead_no", lead_no);
					mov.addObject("lead_code", lead_code);
					menuControlleri.menuImport(mov, "lead?lead_code=" + lead_code);
				}
			}
		}
		else{
			
			if (task_code == null) {
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task");
				menuControlleri.menuImport(mov, "task");
			} else {
				mov.addObject("session", user_id);
				mov.addObject("pageType", "1");
				mov.addObject("task_code", task_code);
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task?task_code=000");

				menuControlleri.menuImport(mov, "task?task_code=000");
			}
		}
		return mov;
	}

	/**
	 * 상담 Ajax 검색, 페이징했을 시
	 */
	@RequestMapping(value = "/task_sch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView taskSchList(@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
			String task_no_srch, String subject_srch, String cust_name_srch, String emp_name_srch, String next_day_srch,
			String dtype_cd_srch, String excel, String session, String cust_task_no) {

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
		taskMap.put("my_user_id", session);
		taskMap.put("cust_task_no", cust_task_no);

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

	/**
	 * 상세보기 및 단건등록화면
	 */
	@RequestMapping(value = "task_detail")
	public ModelAndView taskDetail(@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum,
			HttpServletRequest request, String page_type, String task_code, String task_no, String flg, String lead_no,
			String cust_no, String PageNum, String cust_task_no, String lead_code) {

		// url 가져오기
		String Url = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

		if (lead_no == null) {
			lead_no = "undefined";
		}
		if (cust_task_no == null || cust_task_no == "") {
			cust_task_no = "undefined";
		}

		if (task_no == null || task_no == "") // 단건등록 시
		{
			TaskVO taskNoIndex = taskService.taskNoIndex(); // 인덱스번호
			List<TaskVO> dtypeCd = taskService.taskDtypeCD(); // 분류코드
			List<TaskVO> scoreCd = taskService.taskScoreCD(); // 상대가치점수
			List<TaskVO> ttypeCd = taskService.taskTtypeCD(); // 상담유형
			List<TaskVO> divisCd = taskService.taskDivisCD(); // 상담구분

			ModelAndView mov = new ModelAndView("task_detail");

			if (lead_no.equals("undefined")) {
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task");
				mov.addObject("url", Url);
				mov.addObject("flg", "1");
				menuControlleri.menuImport(mov, "task");

			} else if (task_code != null) {
				mov.addObject("pageType", "1");
				mov.addObject("task_code", task_code);
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task?task_code=000");

				menuControlleri.menuImport(mov, "task?task_code=000");
			} else if (lead_no != null) {
				System.out.println("lead_no 단건 등록");
				mov.addObject("main_menu_url", "lead");
				mov.addObject("sub_menu_url", "lead?lead_code=" + lead_code);
				mov.addObject("lead_no", lead_no);
				mov.addObject("cust_no", cust_no);
				mov.addObject("flg", "2");
				mov.addObject("PageNum", PageNum);
				menuControlleri.menuImport(mov, "lead?lead_code=" + lead_code);

			}

			if (cust_task_no.equals("undefined")) {
				mov.addObject("main_menu_url", "task");
				mov.addObject("sub_menu_url", "task");
				mov.addObject("url", Url);
				menuControlleri.menuImport(mov, "task");

			} else if (!cust_task_no.equals("1")) {
				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
				mov.addObject("cust_task_no", cust_task_no);
				mov.addObject("PageNum", PageNum);
				menuControlleri.menuImport(mov, "cust");
			}

			mov.addObject("flg", "1");

			mov.addObject("taskNoIndex", taskNoIndex);
			mov.addObject("dtypeCd", dtypeCd);
			mov.addObject("scoreCd", scoreCd);
			mov.addObject("ttypeCd", ttypeCd);
			mov.addObject("divisCd", divisCd);
			mov.addObject("taskPageNum", taskPageNum);

			mov.addObject("url", Url);
			mov.addObject("page_type", page_type);

			return mov;
		} else // 상세보기
		{
			List<TaskVO> dtypeCd = taskService.taskDtypeCD(); // 분류코드
			List<TaskVO> scoreCd = taskService.taskScoreCD(); // 상대가치점수
			List<TaskVO> ttypeCd = taskService.taskTtypeCD(); // 상담유형
			List<TaskVO> divisCd = taskService.taskDivisCD(); // 상담구분

			ModelAndView mov = new ModelAndView("task_detail");

			if (lead_no.equals("undefined")) {
				if (page_type == "0") {
					mov.addObject("main_menu_url", "task");
					mov.addObject("sub_menu_url", "task");
					mov.addObject("url", Url);
					mov.addObject("flg", "2");
					menuControlleri.menuImport(mov, "task");
				} else {
					mov.addObject("main_menu_url", "task");
					mov.addObject("sub_menu_url", "mytask");
					mov.addObject("url", Url);
					mov.addObject("flg", "2");
					menuControlleri.menuImport(mov, "task");

				}
			} else {
				mov.addObject("main_menu_url", "lead");
				mov.addObject("sub_menu_url", "lead?lead_code=" + lead_code);
				mov.addObject("lead_no", lead_no);
				mov.addObject("lead_code", lead_code);
				mov.addObject("cust_no", cust_task_no);
				mov.addObject("PageNum", PageNum);
				menuControlleri.menuImport(mov, "lead?lead_code=" + lead_code);
			}

			if (cust_task_no.equals("undefined")) {
				if (page_type == "0") {
					mov.addObject("main_menu_url", "task");
					mov.addObject("sub_menu_url", "task");
					mov.addObject("url", Url);
					mov.addObject("flg", "2");
					menuControlleri.menuImport(mov, "task");
				} else {
					mov.addObject("main_menu_url", "task");
					mov.addObject("sub_menu_url", "task");
					mov.addObject("url", Url);
					mov.addObject("flg", "2");
					menuControlleri.menuImport(mov, "task");
				}
			} else if (cust_task_no != null && lead_code == null) {
				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
				mov.addObject("cust_task_no", cust_task_no);
				mov.addObject("PageNum", PageNum);
				menuControlleri.menuImport(mov, "cust");
			}

			mov.addObject("flg", "2");

			mov.addObject("taskDetail", taskService.taskDetail(task_no));
			mov.addObject("dtypeCd", dtypeCd);
			mov.addObject("scoreCd", scoreCd);
			mov.addObject("ttypeCd", ttypeCd);
			mov.addObject("divisCd", divisCd);
			mov.addObject("taskPageNum", taskPageNum);

			mov.addObject("url", Url);
			mov.addObject("page_type", page_type);

			return mov;
		}
	}

	// 추가
	@RequestMapping(value = "task_single_add", method = RequestMethod.POST)
	public @ResponseBody int taskSingleInsert(TaskVO taskVo, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum) {
		int flg = 1;
		int result = 0;
		result = taskService.taskInsert(taskVo);

		return 0;
	}

	// 수정
	@RequestMapping(value = "task_edit", method = RequestMethod.POST)
	public @ResponseBody int taskEdit(TaskVO taskVo, HttpSession session,
			@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum) {

		int result = 0;
		int flg = 2;

		result = taskService.taskEdit(taskVo);

		return result;
	}

	// 삭제
	@RequestMapping(value = "task_delete", method = RequestMethod.POST)
	public @ResponseBody int taskDelete(TaskVO taskVo, HttpSession session,
			@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum) {

		int result = 0;

		result = taskService.taskDelete(taskVo);

		return result;
	}

	/* Popup */
	@RequestMapping(value = "taskCustListAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskCustListPopup(
			@RequestParam(value = "custPopupPageNum", defaultValue = "1") int custPopupPageNum, String s_cust_name) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custPopupPageNum", custPopupPageNum);

		// paging
		PagerVO page = taskService.getTaskPopupRow(map);
		map.put("page", page);
		map.put("pageNum", custPopupPageNum);

		// 고객리스트 불러오는 서비스/다오/맵퍼 작성
		if (s_cust_name == null || s_cust_name == "") {

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

	@RequestMapping(value = "taskEmpListAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskEmpListPopup(
			@RequestParam(value = "empPopupPageNum", defaultValue = "1") int empPopupPageNum, String s_emp_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empPopupPageNum", empPopupPageNum);

		// paging
		PagerVO page = taskService.getEmpPopupRow(map);
		map.put("page", page);
		map.put("pageNum", empPopupPageNum);

		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if (s_emp_name == null || s_emp_name == "") {

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

	@RequestMapping(value = "taskLeadListAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskLeadListPopup(
			@RequestParam(value = "leadPopupPageNum", defaultValue = "1") int leadPopupPageNum, String s_lead_name) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leadPopupPageNum", leadPopupPageNum);

		// paging
		PagerVO page = taskService.getLeadPopupRow(map);
		map.put("page", page);
		map.put("pageNum", leadPopupPageNum);

		// 가망고객리스트 불러오는 서비스/다오/맵퍼 작성
		if (s_lead_name == null || s_lead_name == "") {

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

	@RequestMapping(value = "taskOpptyListAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskOpptyListPopup(
			@RequestParam(value = "opptyPopupPageNum", defaultValue = "1") int opptyPopupPageNum, String s_oppty_name) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opptyPopupPageNum", opptyPopupPageNum);

		// paging
		PagerVO page = taskService.getOpptyPopupRow(map);
		map.put("page", page);
		map.put("pageNum", opptyPopupPageNum);

		// 영업기회 리스트 불러오는 서비스/다오/맵퍼 작성
		if (s_oppty_name == null || s_oppty_name == "") {

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

	// 엑셀 출력
	@RequestMapping(value = "/toExcel", method = RequestMethod.POST)
	public ModelAndView toExcel(HttpServletRequest req, HttpSession session, String task_no_srch, String subject_srch,
			String cust_name_srch, String emp_name_srch, String next_day_srch, String dtype_cd_srch, String flg,
			String cst_num, String cust_task_no, String my_user_id) {

		String custtmp;

		char temp = flg.charAt(flg.length() - 1);

		ModelAndView result = new ModelAndView();
		Map<String, Object> taskMap = new HashMap<String, Object>();

		if (cust_task_no != null) {
			taskMap.put("cust_task_no", cust_task_no);
		}

		if (temp == '0') {
			// 검색 조건
			taskMap.put("task_no_srch", task_no_srch);
			taskMap.put("subject_srch", subject_srch);
			taskMap.put("cust_name_srch", cust_name_srch);
			taskMap.put("emp_name_srch", emp_name_srch);
			taskMap.put("next_day_srch", next_day_srch);
			taskMap.put("dtype_cd_srch", dtype_cd_srch);
			taskMap.put("cust_no", cst_num);

			if (my_user_id.contains("undefined") || my_user_id.contains("") || my_user_id.contains(null)) {
				my_user_id = "";
			}

			if (my_user_id.contains(",")) {

				String[] id_user = my_user_id.split(",");
				my_user_id = id_user[0].toString();
				taskMap.put("my_user_id", my_user_id);
			} else {
				taskMap.put("my_user_id", my_user_id);
			}

			// form 지속적인 append로 cust_no 스트링 자르기.
			if (cst_num.equals("undefined")) {
				cst_num = "";
				taskMap.put("cust_no", cst_num);
			} else {
				if (cst_num.contains(",")) {
					String[] num_cst = cst_num.split(",");
					cst_num = num_cst[0].toString();
					if (cst_num.equals("undefined")) {
						cst_num = "";
						taskMap.put("cust_no", cst_num);
					} else {
						taskMap.put("cust_no", cst_num);
					}
				}
			}

			// taskMap.put("some",req.getParameter("some")); // where에 들어갈 조건??
			List<TaskVO> list = taskService.taskExcelExport(taskMap); // 쿼리
			result.addObject("taskExcelExport", list); // 쿼리 결과를 model에 담아줌
			result.setViewName("/task/taskList_excel"); // 엑셀로 출력하기 위한 jsp 페이지

			return result;
		} else {
			result.setViewName("/task/taskList_excel"); // 엑셀로 출력하기 위한 jsp 페이지

			return result;
		}

	}

	// 엑셀 추가 전 팝업
	@RequestMapping(value = "/taskExcelImportTab", method = RequestMethod.GET)
	public ModelAndView excelImportTab(HttpSession session, Locale locale,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		ModelAndView mov = new ModelAndView("/lead/excel_import_tab");

		return mov;
	}

	// Excel Data Import
	@RequestMapping(value = "/taskExcelUploadAjax", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public ModelAndView excelUploadAjax(MultipartHttpServletRequest request) throws Exception {
		MultipartFile excelFile = request.getFile("excelFile");

		if (excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택 해 주세요.");
		}

		int result = taskService.excelUpload(excelFile);

		return new ModelAndView("/task/excel_import_tab", "result", result);
	}

	// Excel Data Import Ajax
	@RequestMapping(value = "/taskExcelUpload", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody int taskExcelForm(@RequestParam("excelFile") MultipartFile file) throws Exception {
		int result = taskService.excelUpload(file);

		return result;
	}

}
