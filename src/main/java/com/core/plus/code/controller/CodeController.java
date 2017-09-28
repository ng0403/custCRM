package com.core.plus.code.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.core.plus.code.service.CodeService;
import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.dao.LoginDAO;
import com.core.plus.task.vo.TaskVO;

import net.sf.json.JSONArray;

@Controller
public class CodeController {

	@Resource
	CodeService codeService;
	
	@Resource
	MenuService menuService;
	
	@Resource
	LoginDAO loginDao;
	
	@Autowired
	private HttpSession session;
	
	public void menuImport(ModelAndView mav, String url){
		String menu_id = menuService.getMenuUrlID(url);
		String user_id = session.getAttribute("user").toString();
	
		Map<String, String> menuAuthMap = new HashMap<String, String>();
		menuAuthMap.put("menu_url", url);
		menuAuthMap.put("user_id", user_id);
		menuAuthMap.put("menu_id", menu_id);
		MenuVo menuAuth = loginDao.getMenuAuthInfo(menuAuthMap);
		mav.addObject("menuAuth", menuAuth);
			
		List<MenuVo> mainMenuList = menuService.getMainMenuList(user_id);
		List<MenuVo> subMenuList = menuService.getSubMenuList(menuAuthMap);
		mav.addObject("mainMenuList", mainMenuList);  //mainMenuList
		mav.addObject("subMenuList", subMenuList);    //subMenuList
	}
	
	// List
	@RequestMapping(value="/code")
	public ModelAndView CodeList(HttpSession session,
									@RequestParam(value = "codePageNum", defaultValue = "1") int codePageNum,
									String excel ) {
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("codePageNum", codePageNum);
		
		// paging
		PagerVO page = codeService.getCodeListRow(codeMap);
		codeMap.put("page", page);
		
		List<CodeVO> codeList = codeService.codeList(codeMap);		// 전체 리스트
		
		ModelAndView mov = new ModelAndView("code_list");
		
		mov.addObject("page", page);
		mov.addObject("codePageNum", codePageNum);
		mov.addObject("codeList", codeList);
		mov.addObject("main_menu_url", "code");
		mov.addObject("sub_menu_url", "code");
		menuImport(mov, "code");
		
		return mov;
	}
	
	// 조회
	@RequestMapping(value="/code_sch", method=RequestMethod.POST)
	@ResponseBody
	public  ModelAndView codeSchList(HttpSession session,
										  @RequestParam(value = "codePageNum", defaultValue = "1") int codePageNum,
										  String code_no_srch, String code_srch, String code_name_srch, 
										  String excel) {
		
		ModelAndView mov = new ModelAndView(new MappingJacksonJsonView());
		JSONArray json = new JSONArray();
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		
		codeMap.put("codePageNum", codePageNum);
		codeMap.put("code_no_srch", code_no_srch);
		codeMap.put("code_srch", code_srch);
		codeMap.put("code_name_srch", code_name_srch);
		
		// paging
		PagerVO page = codeService.getCodeListRow(codeMap);
		codeMap.put("page", page);
		
		List<CodeVO> srcList = codeService.codeSchList(codeMap);
		codeMap.put("srcList", srcList);
				
		mov.addObject("page", page);
		mov.addObject("codePageNum", codePageNum);
		mov.addObject("srcList", srcList);
		
		return mov;
	} 
	
	//코드명 눌렀을 때 상세정보
	@RequestMapping(value="/codeDetailAjax" , method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> codeDetail(  @RequestParam(value = "codePageNum", defaultValue = "1") int codePageNum,
										String code_no, String code) {
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("codePageNum", codePageNum);
		codeMap.put("code_no", code_no);
		codeMap.put("code", code);
		
		// paging
		PagerVO page = codeService.getCodeListRow(codeMap);
		codeMap.put("page", page);
		
		List<CodeVO> codeDetail = codeService.codeDetail(codeMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeDetail", codeDetail);
		
		System.out.println("codeDetail : " + codeDetail);
		
		return map;
	}
	
	
	// 추가
	@RequestMapping(value="code_add", method=RequestMethod.POST)
	public @ResponseBody int codeInsert(CodeVO codeVO, HttpSession session, HttpServletRequest request,
												@RequestParam(value = "codePageNum", defaultValue = "1") int codePageNum) {
		int flg=1;
		int result = 0;
		result = codeService.codeInsert(codeVO);
		
		return 0;
	}
	
	// 수정
	@RequestMapping(value="code_edit", method=RequestMethod.POST)
	public @ResponseBody int codeEdit(CodeVO codeVO, HttpSession session,
										@RequestParam(value = "codePageNum", defaultValue = "1") int codePageNum) {
		
		int result = 0;
		int flg=2;
		
		result = codeService.codeEdit(codeVO);

		return result;
	}
	
	// 삭제
	@RequestMapping(value="code_delete", method=RequestMethod.POST)
	public @ResponseBody int codeDelete(CodeVO codeVO, HttpSession session,
											@RequestParam(value = "codePageNum", defaultValue = "1") int codePageNum) {
		
		int result = 0;
		
		result = codeService.codeDelete(codeVO);
		
		return result;
	}
	
	// 상위코드를 위한 팝업
	@RequestMapping(value="codeUpListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> codeUpListPopup(@RequestParam(value = "codePopupPageNum", defaultValue = "1") 
																int codePopupPageNum, String s_code_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codePopupPageNum", codePopupPageNum);
		
		// paging
		PagerVO page = codeService.getCodePopupRow(map);
		map.put("page", page);
		map.put("pageNum", codePopupPageNum);
		
		
		// 상위코드번호를 불러오는 서비스/다오/맵퍼 작성
		if(s_code_no == null || s_code_no == "") {
			
			List<CodeVO> codePopupList = codeService.codePopupList(map);
			
			map.put("codePopupList", codePopupList);
			
			return map;
			
		} else {
			
			map.put("s_code_no", s_code_no);
			
			List<CodeVO> schcodePopupList = codeService.codePopupList(map);
			
			map.put("codePopupList", schcodePopupList);
			
			return map;
		}
	}
	
}
