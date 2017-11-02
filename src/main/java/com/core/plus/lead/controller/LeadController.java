package com.core.plus.lead.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.controller.MenuController;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.lead.service.LeadService;
import com.core.plus.lead.vo.InterestItemVO;
import com.core.plus.lead.vo.LeadVO;
import com.core.plus.login.dao.LoginDAO;
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

	@Resource
	LoginDAO loginDao;

	// 메뉴를 위한 추가
	@Resource
	MenuController menuControlleri;

	@Autowired
	private HttpSession session;

	// 초기 list 출력
	@RequestMapping(value = "lead")
	public ModelAndView lead_list(HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int PageNum, String cust_lead_no, String lead_code) {

		if (session.getAttribute("user") == null) {
 			return new ModelAndView("redirect:/");
		}
		 
		Map<String, Object> leadMap = new HashMap<String, Object>();
		leadMap.put("PageNum", PageNum);
		leadMap.put("cust_lead_no", cust_lead_no); 
	 
		if (lead_code != null) {
			if (lead_code.equals("000")) {
				leadMap.put("my_user_id", session.getAttribute("user").toString());
			} 
			else if (lead_code.equals("001")) {
				leadMap.put("lead_code", "001");
			}
			else if(lead_code.equals("002")) {
				leadMap.put("lead_code", "002");
			}
			else if (lead_code.equals("003")) {
				leadMap.put("lead_code", "003");
			}
			else if(lead_code.equals("004")) {
				leadMap.put("lead_code", "004");
			} 
		}
		// paging
		PagerVO page = leadService.getLeadListRow(leadMap);
		leadMap.put("page", page);

		List<LeadVO> vo = leadService.lead_list(leadMap);
 		ModelAndView mov = new ModelAndView("lead_list");

		mov.addObject("page", page);
		mov.addObject("pageNum", PageNum);
		mov.addObject("cust_lead_no", cust_lead_no);
		mov.addObject("lead_list", vo);
 	 

		// 재욱
		if (cust_lead_no == null) {
			mov.addObject("main_menu_url", "lead");
			menuControlleri.menuImport(mov, "lead");
			if (lead_code == null) {
				mov.addObject("sub_menu_url", "lead");
			} 
			else if (lead_code.equals("000")) {
 				mov.addObject("pageType", "1");
 				mov.addObject("sub_menu_url", "lead?lead_code=000");
			} 
			else if (lead_code.equals("001")) {
 				mov.addObject("lead_status_cd", "001");
 				mov.addObject("sub_menu_url", "lead?lead_code=001");
			}
			else if(lead_code.equals("002")) {
 				mov.addObject("lead_status_cd", "002");
 				mov.addObject("sub_menu_url", "lead?lead_code=002");
			}
			else if(lead_code.equals("003")) {
 				mov.addObject("lead_status_cd", "003");
 				mov.addObject("sub_menu_url", "lead?lead_code=003");
			}
			else if(lead_code.equals("004")) {
 				mov.addObject("lead_status_cd", "004");
				mov.addObject("flg", "004");
				mov.addObject("sub_menu_url", "lead?lead_code=004");
			}
		}
		if (cust_lead_no != null) {

			mov.addObject("cust_lead_no", cust_lead_no);
			mov.addObject("main_menu_url", "cust");
			mov.addObject("sub_menu_url", "cust");
			menuControlleri.menuImport(mov, "cust");
		}
 		return mov;

	} 
	
	// 가망 고객 상세정보
	@RequestMapping(value = "lead_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView lead_detail(@RequestParam("lead_no") String lead_no, @RequestParam("pageNum") String PageNum,
			String cust_lead_no, String lead_code) {
		 
		// 관심상품 리스트 조회
		List<InterestItemVO> itemList = leadService.leadItemList(lead_no);

		// 코드 load
		List<LeadVO> status = leadService.leadStatusCode();
		List<LeadVO> opptycd = leadService.leadOpptyCode();

		ModelAndView mov = new ModelAndView("leadCRUD");

		if (cust_lead_no == null) {
			mov.addObject("detail", leadService.lead_detail(lead_no));

			mov.addObject("main_menu_url", "lead");
			if(lead_code.isEmpty())
			{
				System.out.println("lead detail : empty" + lead_code);
				mov.addObject("sub_menu_url", "lead");
				mov.addObject("lead_code", lead_code);
				mov.addObject("flg", "0");
				menuControlleri.menuImport(mov, "lead");
			}else{
		    System.out.println("lead detail : not empty" + lead_code);
			mov.addObject("sub_menu_url", "lead?lead_code="+lead_code);
			mov.addObject("flg", "0");
			mov.addObject("lead_code", lead_code);
			menuControlleri.menuImport(mov, "lead?lead_code="+lead_code);
			}
		}
		
		//재욱씨 부분
		if (cust_lead_no != null) {
			if (cust_lead_no.equals("undefined") || cust_lead_no.equals(" ")) {
				mov.addObject("detail", leadService.lead_detail(lead_no));

				mov.addObject("main_menu_url", "lead");
				mov.addObject("sub_menu_url", "lead");
				mov.addObject("flg", "0");

				menuControlleri.menuImport(mov, "lead");
			} else if (cust_lead_no.equals(null)) {
				mov.addObject("detail", leadService.lead_detail(lead_no));

				mov.addObject("main_menu_url", "lead");
				mov.addObject("sub_menu_url", "lead");
				mov.addObject("flg", "0");
				menuControlleri.menuImport(mov, "lead");
			} else {
				mov.addObject("detail", leadService.lead_detail(lead_no, cust_lead_no));

				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
				mov.addObject("flg", "007");

				menuControlleri.menuImport(mov, "lead");
			}
		} 
		 
		mov.addObject("PageNum", PageNum);
		mov.addObject("itemList", itemList);
		mov.addObject("leadstatuscode", status);
		mov.addObject("cust_lead_no", cust_lead_no);
		mov.addObject("opptycd", opptycd);

		return mov;
	}

	// 가망 고객 추가 get.
	@RequestMapping(value = "lead_single_add", method = RequestMethod.GET)
	public ModelAndView lead_single_add_get(LeadVO vo) {
 		// 코드 load
		List<LeadVO> status = leadService.leadStatusCode();
		List<LeadVO> opptycd = leadService.leadOpptyCode();

		LeadVO leadNoIndex = leadService.leadNoIndex();
		ModelAndView mov = new ModelAndView("leadCRUD");
		mov.addObject("flg", "1");
		mov.addObject("PageNum", "1");
		mov.addObject("leadNoIndex", leadNoIndex);
		mov.addObject("leadstatuscode", status);
		mov.addObject("opptycd", opptycd);
 		mov.addObject("main_menu_url", "lead");
		mov.addObject("sub_menu_url", "lead");
		menuControlleri.menuImport(mov, "lead");

		return mov;
	}

	// 가망 고객 추가 post.
	@RequestMapping(value = "lead_single_add", method = RequestMethod.POST)
	public String lead_single_add_post(LeadVO vo) {
 
		String cust_no = vo.getCust_no();

		if (cust_no == null) {
			vo.setCust_no(" ");
		}

		leadService.lead_insert(vo);

		System.out.println("lead add success");

		return "redirect:/lead";
	}
 
	// 가망 고객 수정 post.
	@RequestMapping(value = "lead_update", method = RequestMethod.POST)
	public String lead_update_post(LeadVO vo) {

		String cust_no = vo.getCust_no();

		if (cust_no == null) {
			vo.setCust_no(" ");
		}
		leadService.lead_update(vo);

		return "redirect:/lead";
	}

	@RequestMapping(value = "lead_delete", method = RequestMethod.POST)
	public String lead_delete(String lead_no) {

		leadService.lead_delete(lead_no);

		return "redirect:/lead";
	}

	// 조건 검색
	@RequestMapping(value = "/searchKeyword", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> searchKeyword(
			@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, @RequestParam Map<String, Object> search_map) {

 		String lead_code = search_map.get("lead_code").toString();
 		
		Map<String, Object> kwMap = new HashMap<String, Object>();
		System.out.println("page num : " + PageNum);
		kwMap.put("PageNum", PageNum);
		kwMap.put("lead_no_srch", search_map.get("lead_no_srch").toString());
		kwMap.put("lead_name_srch", search_map.get("lead_name_srch").toString());
		kwMap.put("cust_name", search_map.get("cust_name").toString());
		kwMap.put("emp_name", search_map.get("emp_name").toString());
		kwMap.put("contact_day", search_map.get("contact_day_srch").toString());
		kwMap.put("rank_cd", search_map.get("rank_cd").toString());
		kwMap.put("cust_lead_no", search_map.get("cust_lead_no").toString());
		
 		// 진행된 고객리드
		if (lead_code != null) {
			
			kwMap.put("lead_code", lead_code);
			
			if(lead_code.equals("000"))
			{
 				kwMap.put("my_user_id", session.getAttribute("user").toString());
				kwMap.remove("lead_code");
			} 
		}

		// paging
		PagerVO page = leadService.getLeadListRow(kwMap);

		kwMap.put("page", page);

		List<LeadVO> leadList = leadService.leadSearch(kwMap);
		System.out.println("leadList? " + leadList.toString());
		kwMap.put("leadList", leadList);

		return kwMap;
	} 
	 
	/* Item CUD */
	// 상품추가
	@RequestMapping(value = "leadItemInsert", method = RequestMethod.POST)
	public @ResponseBody List<InterestItemVO> leadItemInsert(
			@RequestParam(value = "leadItemList[]", required = false) List<String> leadItemList, String lead_no) {
		System.out.println("Item Insert : " + leadItemList);
		System.out.println("Item Insert : " + lead_no);

		List<InterestItemVO> itemList = new ArrayList<InterestItemVO>();

		List<InterestItemVO> ditemList = leadService.leadItemList(lead_no); // 관심상품
																			// 조회
		if (ditemList == null) {
			System.out.println("list 없음.");
		} else // 리스트가 존재하면 전부 삭제한다.
		{
			System.out.println("list");
			int result = leadService.leadItemDelete(lead_no);
		}

		if (leadItemList != null) {
			for (int i = 0; i < leadItemList.size(); i++) {
				InterestItemVO ovo = new InterestItemVO();

				ovo.setLead_no(lead_no);
				ovo.setMain_cate_cd(leadItemList.get(i));
				ovo.setMid_cate_cd(leadItemList.get(++i));
				ovo.setSmall_cate_cd(leadItemList.get(++i));
				ovo.setQty(Integer.parseInt(leadItemList.get(++i)));
				ovo.setList_price(Integer.parseInt(leadItemList.get(++i)));
				itemList.add(ovo);
			}
			System.out.println("it emList : " + itemList);
			// opptyItem Insert
			int oResult = leadService.leadItemInsert(itemList); // 매출상품 추가
		}

		// 바로 detail 화면으로 뿌려준다.
		// List<OpptyVO> optyItemList = opptyService.opptyDetail(oppty_no);
		List<InterestItemVO> interItemList = leadService.leadItemList(lead_no); // 조회
																				// 후
																				// 상세보기에
																				// 출력
		System.out.println("interItemList : " + interItemList);

		return interItemList;
	}
 

	// 상담이력 조회
	@RequestMapping(value = "/cust_task_sch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView taskSchList(HttpSession session,
			@RequestParam(value = "taskPageNum", defaultValue = "1") int taskPageNum, String task_no_srch,
			String subject_srch, String cust_name_srch, String emp_name_srch, String next_day_srch,
			String dtype_cd_srch, String cust_no, String excel) {
		System.out.println("entrering" + cust_no);
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
		System.out.println("page ? " + page.toString());
		taskMap.put("page", page);

		List<TaskVO> srcList = leadService.taskSchList(taskMap);
		System.out.println("srcList ? " + srcList.toString());
		taskMap.put("srcList", srcList);

		mov.addObject("page", page);
		mov.addObject("taskPageNum", taskPageNum);
		mov.addObject("srcList", srcList);
		mov.addObject("main_menu_url", "lead");
		return mov;
	}

	// 고객 팝업 리스트
	@RequestMapping(value = "custPopListAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> custListPopup(
			@RequestParam(value = "custPopupPageNum", defaultValue = "1") int PageNum, String s_cust_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custPopupPageNum", PageNum);

		PagerVO page = opptyService.getCustPopupRow(map);
		map.put("page", page);
		map.put("pageNum", PageNum);

		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if (s_cust_name == null || s_cust_name == "") {
			List<CustVO> custPopupList = leadService.custPopupList(map);
			map.put("custPopupList", custPopupList);

			return map;
		} else {
			map.put("s_cust_name", s_cust_name);
			List<CustVO> schCustPopupList = leadService.custPopupList(map);
			map.put("custPopupList", schCustPopupList);

			return map;
		}
	}

	// 담당자 팝업 리스트
	@RequestMapping(value = "empPopListAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> empListPopup(
			@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, String s_emp_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empPopupPageNum", PageNum);

		PagerVO page = opptyService.getEmpPopupRow(map);
		map.put("page", page);
		map.put("PageNum", PageNum);

		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if (s_emp_name == null || s_emp_name == "") {
			List<EmpVO> empPopupList = leadService.empPopupList(map);
			map.put("empPopupList", empPopupList);

			return map;
		} else {
			map.put("s_emp_name", s_emp_name);
			List<EmpVO> schEmpPopupList = leadService.empPopupList(map);
			map.put("empPopupList", schEmpPopupList);

			return map;
		}
	}

	// 엑셀 출력
	@RequestMapping(value = "/toLeadExcel", method = RequestMethod.POST)
	public ModelAndView toExcel(HttpServletRequest req, HttpSession session, String lead_no_srch, String lead_name_srch,
			String cust_name, String cust_no, String user_no, String contact_day_srch, String rank_cd, String flg,
			String code_flg, String cust_lead_no, String user_id, String path) {
		System.out.println("flg ? " + flg);
		System.out.println("url ? " + path);
		if (path == null) {
			path = "";
		}
		System.out.println("cust_name ? " + cust_name);
		String contact_day;

		contact_day = contact_day_srch.replace("-", "");
		char temp = flg.charAt(flg.length() - 1);

		ModelAndView result = new ModelAndView();
		Map<String, Object> leadMap = new HashMap<String, Object>();
		leadMap.put("lead_no_srch", lead_no_srch);
		leadMap.put("lead_name_srch", lead_name_srch);
		leadMap.put("cust_no", cust_no);
		leadMap.put("user_no", user_no);
		leadMap.put("contact_day", contact_day);
		leadMap.put("rank_cd", rank_cd);
		leadMap.put("cust_name", cust_name);
		// my_lead url 값을 비교
		if (!path.isEmpty()) {
			String[] path_my = path.split(",");
			System.out.println("path ??? " + path_my[0].toString());
			path = path_my[0].toString();
			if (path.equals("/my_lead")) {
				// user_id 또한 form문에 중첩이 생기므로 , 없애주는 작업을 한다.
				if (user_id.contains(",")) {
					String[] id_user = user_id.split(",", 0);
					user_id = id_user[0].toString();
				}
				leadMap.put("user_id", user_id);
			}
		}

		// code_flg가 Null이지만, 두번째 엑셀 출력 후 code_flg에 ,가 추가 되어 null처리
		if (code_flg == null) {
			code_flg = "";
		}

		if (!code_flg.isEmpty() && code_flg.charAt(0) == ',') {
			code_flg = "";
		}

		// code flg가 not null일 때 form문의 (보류, 기회전환, 실패한 리드)
		if (!code_flg.isEmpty() && !code_flg.equals("")) {
			String code = code_flg.substring(0, 3);
			System.out.println("code ? " + code);
			leadMap.put("code", code);
		}

		// taskMap.put("some",req.getParameter("some")); // where에 들어갈 조건??

		if (cust_lead_no != null) {
			leadMap.put("cust_lead_no", cust_lead_no);
		}

		if (temp == '0') {
			System.out.println("leadMap?? " + leadMap.toString());
			List<LeadVO> list = leadService.leadExcelExport(leadMap); // 쿼리

			System.out.println("excel list  ? " + list.toString());
			System.out.println("size? " + list.size());
			result.addObject("leadExcelExport", list); // 쿼리 결과를 model에 담아줌
			result.setViewName("/lead/leadList_excel"); // 엑셀로 출력하기 위한 jsp 페이지
		} else {
			result.setViewName("/lead/leadList_excel");
		}

		return result;
	}

	// 엑셀 추가 전 팝업
	@RequestMapping(value = "/leadExcelImportTab", method = RequestMethod.GET)
	public ModelAndView excelImportTab(HttpSession session, Locale locale,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		ModelAndView mov = new ModelAndView("/lead/excel_import_tab");

		return mov;
	}

	// Excel Data Import
	@RequestMapping(value = "/leadExcelUploadAjax", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public ModelAndView excelUploadAjax(MultipartHttpServletRequest request) throws Exception {
		MultipartFile excelFile = request.getFile("excelFile");
		System.out.println("excelFile : " + excelFile);

		System.out.println("lead 엑셀 파일 업로드 컨트롤러");

		if (excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택 해 주세요.");
		}

		int result = leadService.excelUpload(excelFile);
		System.out.println(result);

		return new ModelAndView("/lead/excel_import_tab", "result", result);
	}

	// Excel Data Import Ajax
	@RequestMapping(value = "/leadExcelUpload", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody int opptyExcelForm(@RequestParam("excelFile") MultipartFile file) throws Exception {
		System.out.println("Excel Ajax : " + file);
		int result = leadService.excelUpload(file);

		return result;
	}

}
