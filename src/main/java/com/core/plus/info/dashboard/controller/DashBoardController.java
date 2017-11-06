package com.core.plus.info.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.info.dashboard.service.DashBoardService;
import com.core.plus.info.dashboard.vo.DashBoardVO;
import com.core.plus.info.menu.controller.MenuController;

@Controller
public class DashBoardController {
	@Autowired
	DashBoardService dashBoardService;
	
	@Resource
	MenuController menuController;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/dashboard")
	public ModelAndView dashBoard(HttpSession session, @ModelAttribute DashBoardVO dvo){
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if(session.getAttribute("user") == null){
			return new ModelAndView("redirect:/");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dash_board");
		mav.addObject("main_menu_url","dashboard");
		mav.addObject("sub_menu_url","dashboard");
		
		//메뉴 그리기
		menuController.menuImport(mav, "dashboard");
		
		List<DashBoardVO> countList = dashBoardService.countList();
		DashBoardVO avgList = dashBoardService.avgList();
		
		dvo = dashBoardService.getCustCount();
		
		mav.addObject("count",dvo);
		mav.addObject("countList",countList);
		mav.addObject("avgList",avgList);
		
		return mav;
	}

}
