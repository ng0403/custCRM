package com.core.plus.boardmng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
/*@RequestMapping("/board_mng")*/
public class BoardMngController {
	
	@Autowired
	BoardMngService boardmngService;
	
	//게시판 관리 리스트
	@RequestMapping(value="/boardmngInqr", method=RequestMethod.GET) 
	public ModelAndView boardmngList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam Map<String, Object> map ) throws Exception{
		map.put("pageNum", pageNum);
		
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
		mov.addObject("pageNum",  pageNum); 
		mov.addObject("codelist", codelist);
		
		System.out.println(mov.toString());
		return mov; 
		
	}
	
	/*@RequestMapping(value="/board_mng_detail", method=RequestMethod.GET)
	public ModelAndView board_mng_detail(@RequestParam("BOARD_MNG_NO")String BOARD_MNG_NO , HttpSession session ){ 
		//		접속된 사용자 아이디 
		String sessionID = (String) session.getAttribute("user");
		System.out.println("접속된 계정 : " + sessionID);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionID", sessionID);  
		
		BoardMngVO vo = boardmngService.detail(BOARD_MNG_NO);
		System.out.println("vo ?? " + vo.toString());
		
		List<BoardMngVO> codelist = boardmngService.codelist();
		System.out.println("codelist ??? " + codelist.toString());
		
		
		ModelAndView mov = new ModelAndView("board_mng_detail");
 		mov.addObject("board_mng_list", vo);
 		mov.addObject("codelist", codelist);
	 
 		return mov;
	}*/
	
	
	
	// 게시판 관리 상세정보
		  @RequestMapping(value = "boardMngDetail", method = RequestMethod.POST)
		  public @ResponseBody Map<String, Object> companyCutomerDetail(@RequestBody String BOARD_MNG_NO) {
		 
			System.out.println("ajax detail BOARDMNG" + BOARD_MNG_NO);
			  
			BoardMngVO boardMngVo =  boardmngService.detail(BOARD_MNG_NO); 
			System.out.println("boardMngVo " + boardMngVo.toString());

			Map<String, Object> boardMap = new HashMap<String, Object>();
			boardMap.put("boardMngvo", boardMngVo); 
			  
			 return boardMap;
		  
		  }
	
	/*@RequestMapping(value="/board_mng_modify", method=RequestMethod.GET)
	public void board_mng_modify(){ 
		System.out.println("modify page entering");
	}
	
	
	@RequestMapping(value="/board_mng_modify", method=RequestMethod.POST)
	public String board_mng_modify(BoardMngVO vo){
			
		System.out.println("BOARD_MNG_MODIFY ENTERING");
		
		boardmngService.modify(vo);
		System.out.println("modify success" + vo.toString());
		return "redirect:/boardmngInqr";
	}*/
		  
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
		  
		  
	
	/*@RequestMapping(value="/board_mng_add" ,method=RequestMethod.GET)
	public ModelAndView board_mng_add() {
		
		List<BoardMngVO> codelist = boardmngService.codelist();
		System.out.println("codelist ??? " + codelist.toString());

		ModelAndView mov = new ModelAndView("board_mng_add");
		mov.addObject("codelist", codelist);
		
		return mov;
		
	}
		
	@RequestMapping(value="/board_mng_add" ,method=RequestMethod.POST)
	public String board_mng_add_post(BoardMngVO vo) {
		
		System.out.println("enter board_mng_add..." + vo.toString());
		boardmngService.add(vo);
		
		return "redirect:/boardmngInqr";
		
	}
	
	*/
		  
    //게시판 관리 추가.
	@RequestMapping(value = "/boardMngInsert", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> boardMngInsert(HttpSession session, BoardMngVO boardMngVO) {
		System.out.println("cont insert entering" + boardMngVO.toString());
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
		System.out.println("remove insert" + del_code);
		 
		String[] delcode = del_code.split(",");
		ResponseEntity<String> entity = null;
		
		for(int i = 0; i < delcode.length; i++)
		{
			String dc = delcode[i];
			boardmngService.remove(dc);
			System.out.println("success"); 
			
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
				@RequestParam(value = "boardPageNum", defaultValue = "1") int boardPageNum, @RequestParam Map<String, Object> boardMap) {
			
			
	  		boardMap.put("boardPageNum", boardPageNum);
	  		System.out.println("board paging entering" + boardMap.toString());
	  		
			PagerVO page = boardmngService.getBoardMngListCountP(boardMap);
			System.out.println("boardPage ? " + page.toString());
			boardMap.put("page", page);

			List<Object> boardList = boardmngService.list(boardMap);
			System.out.println("boardLitst? "  + boardList.toString());
			System.out.println("boardListSize? " + boardList.size());
			
			
			
			boardMap.put("boardList", boardList);
			boardMap.put("boardListSize", boardList.size());

			return boardMap;
		}
	
	  

}
