package com.core.plus.info.user.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.core.plus.common.Commons;
import com.core.plus.common.PagerVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.info.user.service.UserService;
import com.core.plus.info.user.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	MenuService menuService;

//	@Resource
//	LoginDao loginDao;
	
	@Autowired
	private HttpSession session;
	
	// 유저 리스트
	@RequestMapping(value="/user" , method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView userList(
			HttpSession session,
			String user_id,
			String[] user_id2,
			String user_nm,
			String org_nm,
			@RequestParam(value = "delFlag", defaultValue = "0") int delFlag,
			@RequestParam(value = "pageNum", defaultValue = "1")int pageNum
			){
		String a = "";
		Map<String, Object> map = new HashMap<String, Object>();	// 검색 값을 넣을 Map생성
		map.put("user_id", user_id);
		map.put("user_nm", user_nm);
		map.put("org_nm", org_nm);
		map.put("pageNum", pageNum);
		
		PagerVO page = service.getUserListCount(map);
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}
		map.put("page", page);
		
		ModelAndView mov = new ModelAndView("userlist");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "user");

		if(delFlag == 1){
			int userDel_result = service.getUserChkDelete(user_id2);			
			mov.addObject("userDel_result", userDel_result);
		}	
		
		List<UserVO> list = service.userList(map);
		// 유저 리스트에 권한에 유저권한테이블에서 값을 가져와 합친후 넣어준다. 		
		for (UserVO userVO : list) {
			List<UserVO> ulist = service.userAuthList(userVO.getUser_id());
			for (UserVO userVO2 : ulist) {				
				a += userVO2.getAuth_nm()+",";
			}
			if(a.length()>0) {
				a = a.substring(0,(a.length()-1));				
			}
			userVO.setAuth_nm(a);
			a = "";
		}	
		mov.addObject("userList", list);
		mov.addObject("page", page);
		mov.addObject("pageNum", pageNum);
		mov.addObject("user_id", user_id);
		mov.addObject("user_nms", user_nm);
		mov.addObject("org_nm", org_nm);
		menuImport(mov, "user");
		return mov;
	}
	
//	//사용자 체크 삭제 (여러개 삭제)
//	@RequestMapping(value = "/userChkDelete", method=RequestMethod.POST)
//	public ModelAndView UserChkDelete(HttpSession session, String[] user_id){
//		
//		ModelAndView mov = new ModelAndView("userlist");
//		mov.addObject("main_menu_url", "org");
//		mov.addObject("sub_menu_url", "user");
//		
//		int userChkDel_result = service.getUserChkDelete(user_id);
//		mov.addObject("userChkDel_result", userChkDel_result);
//		menuImport(mov, "user");		
//		return mov;
//	}
	
	// 유저 삭제
//		@RequestMapping(value = "/userdelete",method={RequestMethod.GET, RequestMethod.POST})
//		public ModelAndView userDelete(
//				HttpSession session,
//				String user_id,
//				String modes,
//				@RequestParam(value = "pageNum", defaultValue = "1")int pageNum
//				){
//			RedirectView rv = new RedirectView("user");
//			rv.setExposeModelAttributes(false);
//			
//			ModelAndView mov = new ModelAndView(rv);
//			mov.addObject("main_menu_url", "org");
//			mov.addObject("sub_menu_url", "user");
//			
//			Map<String, Object> map = new HashMap<String, Object>();	// 검색 값을 넣을 Map생성
//
//			map.put("pageNum",pageNum);
//			PagerVO page = service.getUserListCount(map);
//			if(page.getEndRow()==1){
//				page.setEndRow(0);
//			}
//			map.put("page", page);
//			
//			/*service.userAuthDelete(Duser_id);
//			service.userDelete(Duser_id);*/			
//
//
//			if(modes.equals("update")){
//				mov.addObject("message", "성공적으로 수정 되었습니다.");
//			}
//			
//			List<UserVO> list = service.userList(map);
//			mov.addObject("userList", list);	
//			mov.addObject("page", page);
//			mov.addObject("pageNum", pageNum);
//			menuImport(mov, "user");
//			return mov;
//		}
	
		// 유저 상세
		@RequestMapping(value="/userdetail" , method={RequestMethod.GET, RequestMethod.POST})
		public ModelAndView userdetail(
				HttpSession session,
				String user_id,
				String mode
				){
			String a = "";
			String b = "";
			
			Map<String, Object> map = new HashMap<String, Object>();	// 검색 값을 넣을 Map생성
			
			ModelAndView mov = new ModelAndView("userdetail");
			mov.addObject("main_menu_url", "org");
			mov.addObject("sub_menu_url", "user");
			
			List<UserVO> userTypeList = service.userType();
			
			if(mode.equals("detail")){
			UserVO userVO = service.userDetail(user_id);
//			List<UserVO> orgList = service.userOrgList(map);
			List<UserVO> authList = service.authList();		
					
			List<UserVO> userAuthList = service.userAuthList(user_id);     		
			
			for (UserVO userVO1 : userAuthList) {				
				a += userVO1.getAuth_id()+",";				
				b += userVO1.getAuth_nm()+",";
			}
			if(a.length()>0) {
				if(a.length()!=0){
					a = a.substring(0,(a.length()-1));
					userVO.setAuth_id(a);
				}
				if(b.length()!=0){
					b = b.substring(0,(b.length()-1));				
					userVO.setAuth_nm(b);
				}
			}
			
			mov.addObject("userVO", userVO);
			mov.addObject("userAuthList", userAuthList);
//			mov.addObject("orgList", orgList);
			mov.addObject("authList", authList);
			}
			
			mov.addObject("userType", userTypeList);
			mov.addObject("mode", mode);
			menuImport(mov, "user");		
			return mov;
		}	
		
	// 유저 추가
	@RequestMapping(value="/userinsert" , method=RequestMethod.POST)
	public ModelAndView userinsert(
			HttpSession session,
			UserVO userVO,
			String modes,
			@RequestParam(value = "pageNum", defaultValue = "1")int pageNum
			){
		String suc = "ok";
		String[] auth_ids = userVO.getAuth_id().split(",");
		String[] auth_nms = userVO.getAuth_nm().split(",");
		int userInResult = 0;
		/*ID,NM 테스트*/
		/*for (String a : auth_ids) {
		System.out.println(">>>>ID>>>>"+a);
		}
		for (String a : auth_nms) {
			System.out.println(">>>>NM>>>>"+a);
		}*/
		
		RedirectView rv = new RedirectView("user");
		rv.setExposeModelAttributes(false);
		
		
		ModelAndView mov = new ModelAndView(rv);
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "user");
		
		// 암호화
		if(userVO.getPwd()!=""){
			try {
				userVO.setPwd(Commons.getCryptoMD5String(userVO.getPwd()));
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
//		userVO.setCrt_id((String)session.getAttribute("user"));
		
		if(service.userInsert(userVO) != 0){
			userInResult = 1;
		}
		service.userAuthDelete(userVO.getUser_id());
		if(auth_ids.length != 0){
			for(int i=0;i<auth_ids.length;i++){
				service.userAuthInsert(auth_ids[i],userVO.getUser_id());			
			}
		}
//		userVO.setCrt_id((String)session.getAttribute("user"));
		
		Map<String, Object> map = new HashMap<String, Object>();	// 검색 값을 넣을 Map생성
		map.put("pageNum",pageNum);
		PagerVO page = service.getUserListCount(map);
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}
		map.put("page", page);		
		List<UserVO> list = service.userList(map);
		// 유저 리스트에 권한에 유저권한테이블에서 값을 가져와 합친후 넣어준다. 
		String a = "";
		for (UserVO userVO01 : list) {
			List<UserVO> ulist = service.userAuthList(userVO01.getUser_id());
			for (UserVO userVO2 : ulist) {				
				a += userVO2.getAuth_nm()+",";
			}
			if(a.length()>0) {
				a = a.substring(0,(a.length()-1));				
			}
			userVO01.setAuth_nm(a);
			a = "";
		}		
		mov.addObject("userList", list);
	    	    

		System.out.println(modes);
		if(modes.equals("insert")){
			mov.addObject("message", "성공적으로 저장 되었습니다.");
		}	
		
		mov.addObject("suc", suc);
		mov.addObject("page", page);
		mov.addObject("pageNum", pageNum);
	    mov.addObject("userVO", userVO);
	    mov.addObject("userInResult", userInResult);
		menuImport(mov, "user");	    
		return mov;
	}
	// 유저 수정
	@RequestMapping(value="/userupdate", method=RequestMethod.POST)
	public ModelAndView userUpdate(
			HttpSession session,
			UserVO userVO,
			String modes,
			@RequestParam(value = "pageNum", defaultValue = "1")int pageNum
			){
		int userInResult = 2;
		String[] auth_ids = userVO.getAuth_id().split(",");
		String[] auth_nms = userVO.getAuth_nm().split(",");
		
		for (String a : auth_ids) {
			System.out.println(">>>>ID>>>>"+a);
		}
		for (String a : auth_nms) {
			System.out.println(">>>>NM>>>>"+a);
		}
		
		RedirectView rv = new RedirectView("user");
		rv.setExposeModelAttributes(false);
		
		ModelAndView mov = new ModelAndView(rv);		
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "user");
		
		// 암호화		
		if(userVO.getPwd()!=""){
			try {
				userVO.setPwd(Commons.getCryptoMD5String(userVO.getPwd()));
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}		
		}		
		// 생성자 아이디 입력
//		userVO.setCrt_id((String)session.getAttribute("user"));		
			service.userUpdate(userVO);
			// 아이디값의 권한 다 삭제 
			service.userAuthDelete(userVO.getUser_id());			
		if(auth_ids.length != 0){	
			for(int i=0;i<auth_ids.length;i++){
				// 아이디값의 권한 다시 넣어주기
				service.userAuthInsert(auth_ids[i],userVO.getUser_id());			
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();	// 검색 값을 넣을 Map생성

		map.put("pageNum",pageNum);
		PagerVO page = service.getUserListCount(map);
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}
		map.put("page", page);
		
		List<UserVO> list = service.userList(map);
		// 유저 리스트에 권한에 유저권한테이블에서 값을 가져와 합친후 넣어준다. 
		String a = "";
		for (UserVO userVO01 : list) {
			List<UserVO> ulist = service.userAuthList(userVO01.getUser_id());
			for (UserVO userVO2 : ulist) {
				a += userVO2.getAuth_nm()+",";
			}
			if(a.length()>0) {
				a = a.substring(0,(a.length()-1));				
			}
			userVO01.setAuth_nm(a);
			a = "";
		}
		
		System.out.println(modes);
		if(modes.equals("update")){
			mov.addObject("message", "성공적으로 수정 되었습니다.");
		} 
			
		mov.addObject("userList", list);		
		mov.addObject("page", page);
		mov.addObject("pageNum", pageNum);
	    mov.addObject("userInResult", userInResult);
		menuImport(mov, "user");
	    return mov;
	}	
	
	//부서검색리스트 , 페이징 Ajax통신
	@RequestMapping(value="/orgMoList")
	public @ResponseBody Map<String,Object> OrgModalList(HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1")int pageNum
			, @RequestParam Map<String, Object> orgMap)throws ParseException{
		System.out.println(orgMap.toString());
		//map에 사용자검색부분을 넣어줌. 
		orgMap.put("pageNum", pageNum);
			
		//페이지정보얻기
		PagerVO page = service.getOrgModalListCount(orgMap);
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}
		orgMap.put("page", page);
		
		// 부서검색 리스트
		List<Map<String, Object>> orgMlist = service.getOrgModalList(orgMap);
		
		for (Map<String, Object> map : orgMlist) {
			System.out.println(map.toString());
		}
		
		orgMap.put("orgMlist", orgMlist);
		orgMap.put("orgMlistSize", orgMlist.size());
		
		return orgMap;
	}
	
	//ID 중복체크
	@RequestMapping(value="/idChk")
	public @ResponseBody int idChk(HttpSession session
			, @RequestParam(value = "user_id")String user_id)throws ParseException{
		System.out.println(user_id.toString());
		
		int result = 0;
					
		// ID 존재여부 확인
		result = service.getUserIdCount(user_id);
		
		return result;
	}
	
	
	// 메뉴 가져오기
	public void menuImport(ModelAndView mav, String url){
		String menu_id = menuService.getMenuUrlID(url);
//		String user_id = session.getAttribute("user").toString();
			
		// 메뉴에 따른 권한 주기
		Map<String, String> menuAuthMap = new HashMap<String, String>();
		menuAuthMap.put("menu_url", url);
//		menuAuthMap.put("user_id", user_id);
		menuAuthMap.put("menu_id", menu_id);
//		MenuVo menuAuth = loginDao.getMenuAuthInfo(menuAuthMap);
//		mav.addObject("menuAuth", menuAuth);
			
		//메뉴 그리기
		List<MenuVo> mainMenuList = menuService.getMainMenuList(/*user_id*/);
		List<MenuVo> subMenuList = menuService.getSubMenuList(menuAuthMap);
		mav.addObject("mainMenuList", mainMenuList);  //mainMenuList
		mav.addObject("subMenuList", subMenuList);    //subMenuList
	}
	
	
}
