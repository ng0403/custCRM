package com.core.plus.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.plus.login.service.LoginService;

@Controller
public class LoginController {
	
	@Resource
	LoginService loginService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String loginPage(HttpSession session){
 		return "/login/login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutPage(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/loginValidate", method = RequestMethod.POST)
	public @ResponseBody int loginValidate(@RequestBody Object map){
		int result = loginService.searchUser(map);
		
		System.out.println("로그인");
		return result;
	}
	
	@RequestMapping(value="/sessionExpire", method=RequestMethod.GET)
	public String sessionExpire(HttpSession session){
		session.invalidate();
		return "redirect:/standard/home/session_expire";
	}
}

