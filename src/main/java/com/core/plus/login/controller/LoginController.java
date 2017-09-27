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
	
	//로그인처리
		@RequestMapping(value = "/login", method=RequestMethod.POST)
		public String Login(String user_id, String pwd){ 
  			
			String result = loginService.doLogin(user_id, pwd);
			
/*			MenuVo loginMenu = loginService.getLoginMenuInfo();
			
			String menu_id = loginMenu.getMenu_id();
			String menu_url = loginMenu.getMenu_url();
*/			
			String destPage="";
			if(result.equals("LOGIN_SUCCESS")){
				// 로그인이 성공한 경우, 모니터링 - 통합상황판 페이지로 이동
				destPage ="redirect:/cust";
//				destPage = "redirect:/"+menu_url+"?menu_id="+menu_id;
			}else{
				// 로그인이 실패한 경우
				destPage = "redirect:/" + "?loginResult=" + result;
			}
			
			return destPage;
		}
 
	
	@RequestMapping(value="/sessionExpire", method=RequestMethod.GET)
	public String sessionExpire(HttpSession session){
		session.invalidate();
		return "redirect:/standard/home/session_expire";
	}
}

