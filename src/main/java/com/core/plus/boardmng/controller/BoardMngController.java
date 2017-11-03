package com.core.plus.boardmng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.boardmng.service.BoardMngService;
import com.core.plus.boardmng.vo.BoardMngVO;
import com.core.plus.common.PagerVO;
import com.core.plus.info.menu.controller.MenuController;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.dao.LoginDAO;

@Controller
/*@RequestMapping("/board_mng")*/
public class BoardMngController {
	
	@Autowired
	BoardMngService boardmngService;
	@Resource
	MenuService menuService;
	@Autowired
	private HttpSession session;
    @Resource
	LoginDAO loginDao;
  	@Resource
 	MenuController menuControlleri;

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
	
	
	
	
	//게시판 관리 리스트
	@RequestMapping(value="/boardmngInqr",  method=RequestMethod.POST) 
	public ModelAndView boardmngList(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, @RequestParam Map<String, Object> map ) throws Exception{
		
		//		접속된 사용자 아이디 
		String sessionID = (String) session.getAttribute("user");
		System.out.println("접속된 계정 : " + sessionID);
		 
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		} 
		
		map.put("PageNum", PageNum);
  		PagerVO page= boardmngService.getBoardMngListCount(map); 
 		map.put("page", page);
		
		if(page.getEndRow() == 1){
			page.setEndRow(0);
		}
 		List<Object> boardmnglist = boardmngService.list(map); 
		List<BoardMngVO> codelist = boardmngService.codelist();
 		ModelAndView mov = new ModelAndView("board_mng_list");
		mov.addObject("boardmnglist", boardmnglist);
		mov.addObject("page",  page);
		mov.addObject("PageNum",  PageNum); 
		mov.addObject("codelist", codelist);
		mov.addObject("main_menu_url", "boardmngInqr");
		mov.addObject("sub_menu_url", "boardmngInqr");
		menuControlleri.menuImport(mov, "boardmngInqr"); 

 		return mov; 
		
	}  
	
	// 게시판 관리 상세정보
		  @RequestMapping(value = "boardMngDetail", method = RequestMethod.POST)
		  public @ResponseBody Map<String, Object> companyCutomerDetail(@RequestBody String BOARD_MNG_NO) {
		  
			BoardMngVO boardMngVo =  boardmngService.detail(BOARD_MNG_NO); 
 
			Map<String, Object> boardMap = new HashMap<String, Object>();
			boardMap.put("boardMngvo", boardMngVo); 
			  
			 return boardMap;
		  
		  } 
		  
		  @RequestMapping(value = "/boardmngupdate", method = RequestMethod.POST)
			public @ResponseBody Map<String, Object> boardmngupdate(HttpSession session, BoardMngVO boardMngVo) {
				System.out.println("boardmngupdate entering" + boardMngVo.toString());
				Map<String, Object> rstMap = new HashMap<String, Object>();
				
				if (session.getAttribute("user") == null) { // 로그인 페이지 이동
					rstMap.put("mdfyResult", "standard/home/session_expire");
				} else {
					boardMngVo.setUPDATED_BY(session.getAttribute("user").toString());
		 		    boardmngService.modify(boardMngVo); 
					rstMap.put("mdfyResult", "success");
				}  
				return rstMap;
 }	  
 
    //게시판 관리 추가.
	@RequestMapping(value = "/boardMngInsert", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> boardMngInsert(HttpSession session, BoardMngVO boardMngVO) {
 		
		Map<String, Object> rstMap = new HashMap<String, Object>();
		if (session.getAttribute("user") == null) { // 로그인 페이지 이동
			rstMap.put("mdfyResult", "standard/home/session_expire");
		} else {
			boardMngVO.setUPDATED_BY(session.getAttribute("user").toString());
			boardMngVO.setCREATED_BY(session.getAttribute("user").toString());
 			boardmngService.add(boardMngVO); 
			rstMap.put("mdfyResult", "success");
		}  
		return rstMap;
	}

	
	
	@RequestMapping(value="/board_mng_remove" ,method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> board_mng_remove(@RequestBody String del_code){
 		 
		String[] delcode = del_code.split(",");
		ResponseEntity<String> entity = null;
		
		for(int i = 0; i < delcode.length; i++)
		{
			String dc = delcode[i];
			boardmngService.remove(dc);
 			
			if(i == delcode.length-1)
			{
		 	      entity = new ResponseEntity("success", HttpStatus.OK);
		 	      System.out.println(entity);
			}
		}  
	     
	    return entity;
		
	}
	
	// 전체리스트 출력 페이징/검색
		@RequestMapping(value = "/boardmngPaging", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> ActListSearch(HttpSession session,
				@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, @RequestParam Map<String, Object> boardMap) {
 			
	  		boardMap.put("PageNum", PageNum);
 	  		
			PagerVO page = boardmngService.getBoardMngListCount(boardMap);
 			boardMap.put("page", page);

			List<Object> boardList = boardmngService.list(boardMap);
 			
			
			
			boardMap.put("boardList", boardList);
			boardMap.put("boardListSize", boardList.size());

			return boardMap;
		}
	
	  

}
