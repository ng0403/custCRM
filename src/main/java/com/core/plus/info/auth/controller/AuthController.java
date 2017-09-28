/*
업 무 명 : 권한관리
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/07/29
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/07/29
내 용 : 권한관리에 대한 서버 부분이다.
*참고사항 : 
*/

package com.core.plus.info.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.info.auth.service.AuthService;
import com.core.plus.info.auth.vo.AuthVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.dao.LoginDAO;

@Controller
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	MenuService menuService;
	
	@Resource
	LoginDAO loginDao;
	
	@Autowired
	private HttpSession session;
	
	//기준정보 권한관리 리스트
	@RequestMapping(value="/auth")
	public ModelAndView AuthList(HttpSession session, @RequestParam Map<String, Object> map, String menu_id, String main_menu_id){

		ModelAndView mov = new ModelAndView("auth");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
				
		List<AuthVO> authList = authService.getAuthList(map);
		mov.addObject("authList", authList);  //"" 안은 for each의 items 이름
		
		return mov;
	}
	
	//권한id 눌렀을 때 상세정보
	@RequestMapping(value = "/authDetail")
	public ModelAndView AuthDetail(String auth_id, @RequestParam Map<String, Object> map, String main_menu_id){
		
		ModelAndView mov = new ModelAndView("auth");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
		List<AuthVO> authList = authService.getAuthList(map);
		AuthVO authVO = authService.getAuthDetail(auth_id);
		
//		System.out.println("권한관리 상세정보 auth_id : " +auth_id);
		
		mov.addObject("authVO", authVO); //"" 안은 for each의 items 이름
		mov.addObject("authList", authList);
		
		return mov;
	}
	
	//권한 추가
	@RequestMapping(value = "/authInsert", method = RequestMethod.POST)
	public ModelAndView AuthInsert(HttpSession session, AuthVO authVo, String main_menu_id) {
		
		ModelAndView mov = new ModelAndView("auth");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//등록, 수정자 ID 세팅
		String user_id = session.getAttribute("user").toString();
//		authVo.setCrt_id(user_id);
//		authVo.setMdfy_id(user_id);
		authVo.setFin_mdfy_id(user_id);
		//메뉴 그리기
		menuImport(mov, "auth");
		
		int result = authService.getAuthInsert(authVo);
		
		mov.addObject("result", result);
		mov.addObject("auth_id", authVo.getAuth_id());
		
		return mov; 
	}
	
	//권한 수정
	@RequestMapping(value = "/authUpdate", method=RequestMethod.POST)
	public ModelAndView AuthUpdate(HttpSession session, AuthVO authVo, String main_menu_id){
		
		ModelAndView mov = new ModelAndView("auth");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//수정자 ID 가져오기
		String user_id = session.getAttribute("user").toString();
		authVo.setFin_mdfy_id(user_id);
		//메뉴 그리기
		menuImport(mov, "auth");
		
		//String auth_id = authVo.getAuth_id();
		int authMdf_result = authService.getAuthUpdate(authVo);
//		System.out.println("auth_id 컨트롤 : " + authVo.getAuth_id());
		
		mov.addObject("authMdf_result", authMdf_result);
		mov.addObject("auth_id", authVo.getAuth_id());
		
		return mov;
	}
	
	//권한 삭제 (여러개 삭제)
	@RequestMapping(value = "/authDelete", method=RequestMethod.POST)
	public ModelAndView AuthDelete(HttpSession session, AuthVO authVo, String main_menu_id){

		ModelAndView mov = new ModelAndView("auth");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
		String auth_id = authVo.getAuth_id();
		
		int authDel_result = authService.getAuthDelete(authVo);
		
		mov.addObject("authDel_result", authDel_result);
		mov.addObject("auth_id", auth_id);
		
		return mov;
	}
	
	//권한 체크 삭제 (여러개 삭제)
	@RequestMapping(value = "/authChkDelete", method=RequestMethod.POST)
	public ModelAndView AuthChkDelete(HttpSession session, String[] auth_id, String main_menu_id){

		ModelAndView mov = new ModelAndView("auth");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
		int authChkDel_result = authService.getAuthChkDelete(auth_id);
		
		mov.addObject("authChkDel_result", authChkDel_result);
		mov.addObject("auth_id", auth_id);
		
		return mov;
	}
	
	//기준정보 권한관리 > 권한메뉴 리스트
	@RequestMapping(value="/authmenu")
	public ModelAndView AuthmenuList(HttpSession session, @RequestParam Map<String, Object> map
			, MenuVo menuVo, String main_menu_id){
		
		ModelAndView mov = new ModelAndView("authmenu");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
		List<MenuVo> authmenuList = menuService.getAuthMenuList(map);
		List<MenuVo> menumodalList = menuService.getMenuModalList(map);
		List<MenuVo> menuList = menuService.getMenuList(map); //메뉴리스트
		
//		System.out.println("메뉴권한 리스트 auth_id : " +menuVo.getAuth_id());
		
		mov.addObject("authmenuList", authmenuList);  //"" 안은 for each의 items 이름
		mov.addObject("menuVo", menuVo);
		mov.addObject("auth_id", menuVo.getAuth_id());
		mov.addObject("menumodalList", menumodalList);
		mov.addObject("menuList", menuList);
		
		return mov;
	}
	
	//메뉴권한 추가 저장
	@RequestMapping(value = "/authmenuInsert" , method = RequestMethod.POST)
	public ModelAndView authmenuInsert(HttpSession session, MenuVo menuVo, String main_menu_id, String auth_id) {
		
		ModelAndView mov = new ModelAndView("authmenu");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//등록, 수정자 ID 세팅
		String user_id = session.getAttribute("user").toString();
		menuVo.setFin_mdfy_id(user_id);
//		menuVo.setMdfy_id(user_id);
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
		int authmenu_result = 0;
		int authmenu_result1 = 0;
		
//		System.out.println("메뉴권한 추가 auth_id : " + auth_id );
		
		String [] menu_id = menuVo.getMenu_id().split(",");
		
		String [] crt_yn  = menuVo.getCrt_yn().split(",");
		String [] mdfy_yn = menuVo.getMdfy_yn().split(",");
		String [] del_yn  = menuVo.getDel_yn().split(",");
		String [] rtrv_yn = menuVo.getRtrv_yn().split(",");
		
		for (int i=0; i<menu_id.length-1; i++) {
			authmenu_result1 = menuService.getAuthMenuInsert1(menu_id[i], menuVo.getAuth_id()
							, crt_yn[i], mdfy_yn[i], del_yn[i], rtrv_yn[i], menuVo);
		}
		
//		System.out.println("메뉴권한 추가 : " + menu_id[0] +" , " + menu_id[1]);
		
		mov.addObject("authmenu_result", authmenu_result);
		mov.addObject("authmenu_result1", authmenu_result1);
		mov.addObject("auth_id", menuVo.getAuth_id());
		mov.addObject("crt_yn", crt_yn);
		mov.addObject("mdfy_yn", mdfy_yn);
		mov.addObject("del_yn", del_yn);
		mov.addObject("rtrv_yn", rtrv_yn);
		mov.addObject("menuVo", menuVo);
		
		return mov;
	}
	
	//기준정보 권한관리 > 권한메뉴 체크 삭제 (여러개 삭제)
	@RequestMapping(value = "/authmenuChkDelete")
	public ModelAndView AuthMenuChkDelete(HttpSession session, String[] menu_id, MenuVo menuVo
			, @RequestParam Map<String, Object> map, String main_menu_id){
		
		ModelAndView mov = new ModelAndView("authmenu");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
//		System.out.println(menu_id[0]);
//		System.out.println("삭제로 넘어온 auth_id : " + menuVo.getAuth_id());
		
		String auth_id = menuVo.getAuth_id();
		
		int authmenuChkDel_result = menuService.getAuthMenuChkDelete(menu_id, auth_id);
		
		List<MenuVo> authmenuList = menuService.getAuthMenuList(map);
		
		mov.addObject("authmenuChkDel_result",  authmenuChkDel_result);
		mov.addObject("auth_id", auth_id);
		mov.addObject("authmenuList", authmenuList);
		
		return mov;
	}
	
	//기준정보 권한관리 > 사용자 권한 리스트
	@RequestMapping(value="/authuser")
	public ModelAndView AuthuserList(HttpSession session, @RequestParam Map<String, Object> map
			, String main_menu_id, AuthVO authVo){
		
		ModelAndView mov = new ModelAndView("authuser");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
//		System.out.println("사용자권한 리스트 : " + map.toString());
		
		List<AuthVO> authuserList = authService.getAuthUserList(map);
		
		mov.addObject("authuserList", authuserList);  //"" 안은 for each의 items 이름
		mov.addObject("authVo", authVo); 
		mov.addObject("auth_id", authVo.getAuth_id());

		return mov;
	}
	
	//기준정보 권한관리 > 사용자권한 체크 삭제 (여러개 삭제)
	@RequestMapping(value = "/authuserChkDelete")
	public ModelAndView AuthUserChkDelete(HttpSession session, String[] user_id, AuthVO authVo
			, @RequestParam Map<String, Object> map, String main_menu_id){
		
		ModelAndView mov = new ModelAndView("authuser");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "auth");
		
		//메뉴 그리기
		menuImport(mov, "auth");
		
		System.out.println("사용자 id : " + user_id[0]);
		System.out.println("사용자권한 삭제로 넘어온 auth_id : " + authVo.getAuth_id());
		
		String auth_id = authVo.getAuth_id();
		
		int authuserChkDel_result = authService.getAuthUserChkDelete(user_id, auth_id);
		
		List<AuthVO> authuserList = authService.getAuthUserList(map);
		
		mov.addObject("authuserChkDel_result", authuserChkDel_result);
		mov.addObject("auth_id", authVo.getAuth_id());
		mov.addObject("authuserList", authuserList);
		
		return mov;
	}
	
	// 메뉴 가져오기
	public void menuImport(ModelAndView mav, String url){
		String menu_id = menuService.getMenuUrlID(url);
		String user_id = session.getAttribute("user").toString();
			
		// 메뉴에 따른 권한 주기
		Map<String, String> menuAuthMap = new HashMap<String, String>();
		menuAuthMap.put("menu_url", url);
		menuAuthMap.put("user_id", user_id);
		menuAuthMap.put("menu_id", menu_id);
		MenuVo menuAuth = loginDao.getMenuAuthInfo(menuAuthMap);
		mav.addObject("menuAuth", menuAuth);
			
		//메뉴 그리기
		List<MenuVo> mainMenuList = menuService.getMainMenuList(user_id);
		List<MenuVo> subMenuList = menuService.getSubMenuList(menuAuthMap);
		mav.addObject("mainMenuList", mainMenuList);  //mainMenuList
		mav.addObject("subMenuList", subMenuList);    //subMenuList
	}
}
