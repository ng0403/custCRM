package com.core.plus.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.code.service.CodeService;
import com.core.plus.code.vo.CodeVO;
import com.core.plus.common.PagerVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;

@Controller
public class CodeController {

	@Resource
	CodeService codeService;
	
	@Resource
	MenuService menuService;
	
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
	
	
	
}
