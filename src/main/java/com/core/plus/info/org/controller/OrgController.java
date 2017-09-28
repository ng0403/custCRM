package com.core.plus.info.org.controller;

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

import com.core.plus.common.PagerVO;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.info.org.service.OrgService;
import com.core.plus.info.org.vo.OrgVO;
import com.core.plus.login.dao.LoginDAO;

@Controller
public class OrgController{
	@Autowired
	OrgService orgService;
	
	@Autowired
	MenuService menuService;
	
	@Resource
	LoginDAO loginDao;
	
	@Autowired
	private HttpSession session;
	
	// 부서 리스트
	@RequestMapping(value="/org" , method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView OrgList(
			HttpSession session,
			String org_nm,
			String org_ph,
			String[] org_id,
			@RequestParam(value = "delFlag", defaultValue = "0") int delFlag,
			@RequestParam(value = "pageNum", defaultValue = "1")int pageNum
			)throws Exception{
		
		ModelAndView mov = new ModelAndView("orglist");
		mov.addObject("main_menu_url", "org");
		mov.addObject("sub_menu_url", "org");
		menuImport(mov, "org");
		
		if(delFlag == 1){
			// 삭제 기능
			int orgChkDel_result = orgService.getOrgChkDelete(org_id);
			mov.addObject("orgChkDel_result", orgChkDel_result);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("org_nm", org_nm);
		map.put("org_ph",org_ph);
		map.put("pageNum", pageNum);
		
		PagerVO page = orgService.getOrgListCount(map);		
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}		
		map.put("page", page);
		
		// 대표자 검색 리스트
		List<OrgVO> list = orgService.orgList(map);
		
		mov.addObject("orgList", list);				
		mov.addObject("page", page);				
		mov.addObject("org_nm", org_nm);
		mov.addObject("pageNum", pageNum);		
		
		return mov;
	}

	// 부서 상세정보
	@RequestMapping(value="/orgForm")
	public ModelAndView OrgDetail(HttpSession session
			, @RequestParam Map<String, Object> orgMap, OrgVO orgVO){

		ModelAndView mav = new ModelAndView("orgdetail");
		mav.addObject("main_menu_url", "org");
		mav.addObject("sub_menu_url", "org");
		menuImport(mav, "org");
		
		// 사용자 ID 세팅
//		String user_id = session.getAttribute("user").toString();
//		orgVO.setCrt_id(user_id);
		int orgResult = 0;
		
		/* form_flag : 1. 추가폼, 2. 상세정보 3. 추가, 4. 수정 */
		int form_flag = Integer.parseInt((String)orgMap.get("form_flag"));
		switch (form_flag) {
		case 1:		// 부서 추가 페이지 이동
			mav.addObject("orgMap", orgMap);
			break;
		case 2:		// 부서 상세 정보
			orgVO = orgService.orgDetail((String)orgMap.get("org_id"));
			mav.addObject("orgDetail", orgVO);
			mav.addObject("orgMap", orgMap);
			break;
		case 3:		// 부서 저장
			orgResult = orgService.orgInsert(orgVO);
			orgVO = orgService.orgDetail(orgVO.getOrg_id());
			mav.addObject("orgResult", orgResult);
			mav.addObject("orgDetail", orgVO);
			mav.addObject("orgMap", orgMap);
			break;
		case 4:		// 부서 수정
			orgResult = orgService.orgUpdate(orgVO);
			orgVO = orgService.orgDetail((String)orgMap.get("org_id"));
			mav.addObject("orgResult", orgResult);
			mav.addObject("orgDetail", orgVO);
			mav.addObject("orgMap", orgMap);
			break;
		}
		
		// 대표검색 리스트
//		List<OrgVO> replist = orgService.repOrgList(orgMap);		
//		mav.addObject("replist", replist);
		
		return mav;
	}
	
	//사용자검색리스트 , 페이징 Ajax통신
	@RequestMapping(value="/repMoList")
	public @ResponseBody Map<String,Object> repUserModalList(HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1")int pageNum
			, @RequestParam Map<String, Object> orgMap)throws ParseException{
		System.out.println(orgMap.toString());
		//map에 사용자검색부분을 넣어줌. 
		orgMap.put("pageNum", pageNum);
			
		//페이지정보얻기
		PagerVO page = orgService.getRepUserListCount(orgMap);
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}
		orgMap.put("page", page);
		
		// 대표검색 리스트
		List<OrgVO> replist = orgService.repOrgList(orgMap);		
		orgMap.put("replist", replist);
		orgMap.put("replistSize", replist.size());
		
		return orgMap;
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
