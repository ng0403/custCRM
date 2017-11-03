package com.core.plus.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.board.service.BoardService;
import com.core.plus.board.service.ReplyService;
import com.core.plus.board.vo.BoardVO;
import com.core.plus.boardmng.vo.BoardMngVO;
import com.core.plus.common.PagerVO;
import com.core.plus.info.menu.controller.MenuController;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.dao.LoginDAO;
import com.core.plus.utils.FileManager;

@Controller
@SessionAttributes("session")
 public class BoardController {
	
	private static final String[] filename = null;
	@Autowired
	BoardService boardService; 
	ReplyService replyService;
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
	
	
	
	//보드 전체 리스트.
	@RequestMapping(value="/boardInqr", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardList(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, @RequestParam Map<String, Object> map ,HttpSession session
			,@RequestParam("BOARD_MNG_NO") String BOARD_MNG_NO) throws Exception{
		  
 		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		} 
 		
 		String sessionID = (String) session.getAttribute("user");
 		 
		map.put("PageNum", PageNum);
		map.put("session", sessionID); 
		map.put("BOARD_MNG_NO", BOARD_MNG_NO); 
		PagerVO page=boardService.getBoardListCount(map); 
 		map.put("page", page);
		
 		
		if(page.getEndRow() == 1){
			page.setEndRow(0);
		}
		
  		List<BoardVO> boardlist = boardService.list(map);
   		ModelAndView mov = new ModelAndView("board_list"); 
		mov.addObject("boardlist", boardlist);
		mov.addObject("page",  page);
		mov.addObject("PageNum",  PageNum);
 		mov.addObject("BOARD_MNG_NO", BOARD_MNG_NO);
		mov.addObject("main_menu_url", "boardInqr");
		mov.addObject("sub_menu_url", "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO);
		menuControlleri.menuImport(mov, "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO); 
 		/*menuImport(mov, "boardInqr?BOARD_MNG_NO=BMG001");*/
  		return mov; 
	} 
	
	/*
	 * 게시판 상세 정보.
	 * FILE_CD의 유무로 일반 게시물 OR 파일 게시물을 찾는 서비스 실행.
	 * 
	 * */
	@RequestMapping(value="/boardDetail", method= RequestMethod.GET)
 	public ModelAndView boardDetail(@RequestParam("BOARD_NO") int BOARD_NO, HttpSession session) throws Exception {
 		if (session.getAttribute("user") == null) {
 			return new ModelAndView("redirect:/");
		} 
 		String sessionID = (String) session.getAttribute("user");
 		 
  		
		BoardVO vo = boardService.detail(BOARD_NO);
		
 		String BOARD_MNG_NO = vo.getBOARD_MNG_NO(); 

		String FILE_CD = vo.getFILE_CD(); 
		
		boardService.viewadd(BOARD_NO);
		
		ModelAndView mov = new ModelAndView("board_detail");
    	 mov.addObject("session",sessionID);
		
    	 if(FILE_CD == null)
		{ 
			mov.addObject("boardlist", boardService.detail(BOARD_NO));
		}
		else
		{ 
			 
			mov.addObject("boardlist",  boardService.ReadFilePage(BOARD_NO));
		}
		mov.addObject("boardmnglist",boardService.checkBoardMngNo(BOARD_MNG_NO));
		mov.addObject("main_menu_url", "boardInqr");
		mov.addObject("sub_menu_url", "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO);
		menuControlleri.menuImport(mov, "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO); 


 		return mov;
		 
	} 
	

	//보드 추가 FORM.
	@RequestMapping(value="/boardInsertForm", method=RequestMethod.GET)
	public ModelAndView board_add(@RequestParam(value="BOARD_MNG_NO") String BOARD_MNG_NO) {
 
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
 			return new ModelAndView("redirect:/");
		} 
		
 		System.out.println(BOARD_MNG_NO);
		BoardMngVO vo =  boardService.checkBoardMngNo(BOARD_MNG_NO); 
		
		ModelAndView mov = new ModelAndView("board_insert");
  		  mov.addObject("boardmnglist", vo);
  		mov.addObject("main_menu_url", "boardInqr");
  		mov.addObject("sub_menu_url", "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO);
  		menuControlleri.menuImport(mov, "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO); 

  		  return mov; 
	}
	
	//보드 추가.
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String  board_insert(MultipartHttpServletRequest multi, HttpServletRequest request, BoardVO attach, HttpSession session, @RequestParam Map<String, Object> map ) throws IOException { 
		
		System.out.println("attach / " + attach.toString());
		
		
		String real_file_nm = "";
 		String BOARD_MNG_NO = (String) map.get("BOARD_MNG_NO");
		System.out.println("BOARD_MNG_NO"  + BOARD_MNG_NO);
		String sessionID = (String) session.getAttribute("user");
 		attach.setCREATED_BY(sessionID);
 		
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null; 
 	      
	     while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
 	        if(multipartFile.isEmpty() == false){
	        	
 	 		    attach.setFILE_NM(multipartFile.getOriginalFilename());
 	 		    String name = multipartFile.getOriginalFilename();
 
 	 		    
 	 		    StringTokenizer toke = new StringTokenizer(name, ".");
 	 		    int tokeSize = toke.countTokens();
 	 		    String[] filename = new String[tokeSize];
 	 		    
 	 		    for(int i= 0; toke.hasMoreElements() ; i++)
 	 		    {
  	 		     filename[i] = toke.nextToken(); 
   	 		    }
 	 		
  		 		
 			    for(int i = 0; i<filename.length-1; i++)
 		 		{
 	 	 			if(i < filename.length-2)
 		 			{
 	 	 				real_file_nm += filename[i]+ ".";	 		
 		 			}
 		 			else{
 		 				real_file_nm +=filename[i];	
 		 			}
 		 		}		    
 	 		   
 	 		    attach.setFILE_NM(real_file_nm);
 	 		    attach.setFILE_EXT(filename[tokeSize-1]);  
 	 		    
 	        }
	    } 
 
	    if(attach.getFILE_NM() != null){ 
 		FileManager fileManager = new FileManager(); 
 		List<MultipartFile> file = multi.getFiles("filedata");

		for(int i=0; i<file.size(); i++){
			//Created 넣기.
			attach.setCREATED_BY(session.getAttribute("user").toString());
			attach.setUPDATED_BY(session.getAttribute("user").toString());
 			String uploadpath = fileManager.doFileUpload(file.get(i), request);
  			attach.setFILE_PATH(uploadpath);
			boardService.insertAttachData(attach); 
		}
	 }
	    String file_nm = attach.getFILE_NM();
	    if(file_nm == null)
	    {
	    	attach.setFILE_NM("");
	    }
 		boardService.insert(attach);   
	 
		return "redirect:/boardInqr?BOARD_MNG_NO=" + BOARD_MNG_NO; 
		 
	} 
	  
	//보드 수정
	@RequestMapping(value="/boardModify", method=RequestMethod.GET)
	public ModelAndView board_modifyPage(int BOARD_NO, Model model, HttpSession session)
	{ 
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
 			return new ModelAndView("redirect:/");
		}
 		
		String sessionID = (String) session.getAttribute("user");
  		
		BoardVO vo = boardService.detail(BOARD_NO);
		vo.setCREATED_BY(sessionID);
		vo.setUPDATED_BY(sessionID);
 		String FILE_CD = vo.getFILE_CD();
		
		ModelAndView mov = new ModelAndView("board_modify"); 
		if(FILE_CD != null){
			mov.addObject("boardVO", boardService.readFileModify(BOARD_NO));
 		}
		else{
			mov.addObject("boardVO", boardService.read(BOARD_NO));
 		}
		String 	BOARD_MNG_NO = vo.getBOARD_MNG_NO();
		mov.addObject("boardmnglist",boardService.checkBoardMngNo(BOARD_MNG_NO)); 
		mov.addObject("main_menu_url", "boardInqr");
		mov.addObject("sub_menu_url", "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO);
		menuControlleri.menuImport(mov, "boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO); 

 		return mov;
		 
		
	}
	
	//보드 수정
	@RequestMapping(value="/board_modify", method=RequestMethod.POST)
	public String board_modify(BoardVO vo, HttpSession session, MultipartHttpServletRequest multi, HttpServletRequest request, BoardVO attach) throws IOException
	{
		String sessionID = (String) session.getAttribute("user");
		System.out.println("접속된 계정 : " + sessionID);
		
		vo.setCREATED_BY(sessionID);
 		vo.setUPDATED_BY(sessionID);
		System.out.println("modify  Entering" + vo);
		
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null; 
 	      
	     while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
 	        if(multipartFile.isEmpty() == false){
	        	
 	 		    attach.setFILE_NM(multipartFile.getOriginalFilename());
 	 		    String name = multipartFile.getOriginalFilename();
 	 		    
  	 		    StringTokenizer toke = new StringTokenizer(name, ".");
 	 		    String[] filename = new String[2];
 	 		    
 	 		    for(int i= 0; toke.hasMoreElements() ; i++)
 	 		    {
  	 		     filename[i] = toke.nextToken(); 
    	 		    }
 	 		   
 	 		    attach.setFILE_NM(filename[0]);
 	 		    attach.setFILE_EXT(filename[1]);  
 	 		    
 	        }
	    } 
		
	    if(attach.getFILE_NM() != null){ 
		FileManager fileManager = new FileManager(); 
		
		List<MultipartFile> file = multi.getFiles("filedata");
	
		for(int i=0; i<file.size(); i++){
			
			String uploadpath = fileManager.doFileUpload(file.get(i), request);
		
			attach.setFILE_PATH(uploadpath);
 			boardService.insertAttachData(attach);
		
		}
	 }
	    String file_nm = attach.getFILE_NM();
	    if(file_nm == null)
	    {
	    	attach.setFILE_NM("");
	    }
		
		 
		
		boardService.modify(vo);
		String BOARD_MNG_NO = vo.getBOARD_MNG_NO();
		return "redirect:/boardInqr?BOARD_MNG_NO=" + BOARD_MNG_NO;
	}
	
	//보드 삭제
	@RequestMapping(value="/board_remove", method=RequestMethod.POST) 
	 @ResponseBody
	public ResponseEntity<String> board_remove(@RequestBody String del_code){ 
		
		System.out.println("remove insert");

		String[] delcode = del_code.split(",");
		ResponseEntity<String> entity = null;
		System.out.println("delcode?" + delcode.toString());
		System.out.println("delcode length" + delcode.length);
		for(int i = 0; i < delcode.length; i++)
		{
			String dc = delcode[i];
 			boardService.removeBoard(dc);
 			
			if(i == delcode.length-1)
			{
		 	      entity = new ResponseEntity("success", HttpStatus.OK);
		 	      System.out.println(entity);
			}
		}  
	     
	    return entity;
	}
	
	//보드 삭제
	@RequestMapping(value="/detail_remove", method=RequestMethod.POST) 
	public String detailRemove(int BOARD_NO, String BOARD_MNG_NO){ 
		
		System.out.println("remove insert" + BOARD_NO + "," + BOARD_MNG_NO);

		 
			boardService.removeDetail(BOARD_NO); 
			
	    return "redirect:/boardInqr?BOARD_MNG_NO="+BOARD_MNG_NO;
	}
	 
	
	@RequestMapping(value="/search_boardInqr", method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> search_board_list( ModelMap model, HttpServletRequest request,
													   @RequestParam(value = "PageNum", defaultValue = "1") int PageNum) {
		System.out.println("search entering1111");
 		String keyword    = request.getParameter("keyword");
	    
	    Map<String,Object> map = new HashMap<String,Object>();
	    
 		map.put("keyword", keyword);
		map.put("PageNum", PageNum);

		PagerVO page = boardService.getBoardListCount(map);
		System.out.println("page?" + page.toString());
		if(page.getEndRow()==1){
			page.setEndRow(0);
		}
		
		int startRow = page.getStartRow();
		int endRow = page.getEndRow();
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		List<BoardVO> list = boardService.SearchList(map);
		System.out.println("list?" + list.toString());
		
		model.addAttribute("page", page);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("qna_list", list);

		return model;
	}
	 
	
	@RequestMapping(value = "/file_down", method = RequestMethod.GET)
	public void downloadFile( @RequestParam(value = "FILE_CD") String FILE_CD, HttpServletResponse response, HttpServletRequest request) {
		
 		
		Map<?, ?> map = (Map<?, ?>) boardService.searchOneFiledata(FILE_CD); 
  		if (map != null) {

			String fileroot = map.get("FILE_PATH").toString();
			String[] temp = fileroot.split("\\\\");
			String fileName = temp[temp.length - 1];
			String root = "";
 			
			//"x`x`"
			for (int i = 0; i < (temp.length - 2); i++) {
				root += temp[i] + "\\";
 			}

			FileManager fileManager = new FileManager();

			//파일의 확장자를 구한다.
 			int index = fileName.lastIndexOf(".");
			String ext = null;
			if(index != -1) {
				ext = fileName.substring(index + 1);
				System.out.println("확장자 ? "  + ext);
			} 
			
			
			boolean existfile = fileManager.doFileDownload(fileName, root, response);
 
			
			if (!existfile) {
				try {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('다운로드에 실패하였습니다.');history.back();</script>");
				} catch (Exception e) {
				}
			}

		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script>alert('파일이 없습니다.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}

			return;
		}

	}
	 
	// 전체리스트 출력 페이징/검색
	@RequestMapping(value = "/boardPaging", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> ActListSearch(HttpSession session,
			@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, @RequestParam Map<String, Object> boardMap) {
		
		System.out.println("board paging entering" + boardMap.toString());
  		boardMap.put("PageNum", PageNum);

		PagerVO page = boardService.getBoardListCount(boardMap);
		System.out.println("boardPage ? " + page.toString());
		boardMap.put("page", page);

		List<BoardVO> boardList = boardService.list(boardMap);
		System.out.println("boardLitst? "  + boardList.toString());
		System.out.println("boardListSize? " + boardList.size());
		boardMap.put("boardList", boardList);
		boardMap.put("boardListSize", boardList.size());
		
		System.out.println("final boardMap ? " + boardMap.toString());
		return boardMap;
	}
 	
	@RequestMapping(value="/file_remove", method=RequestMethod.POST) 
	 @ResponseBody
	public ResponseEntity<String> file_remove(@RequestBody String FILE_CD){ 
		
		System.out.println("file_remove Entering" + FILE_CD);

		ResponseEntity<String> entity = null;
	    try {
	      boardService.file_remove(FILE_CD);
	      entity = new ResponseEntity("success", HttpStatus.OK);
	      
	      boardService.file_removeMd(FILE_CD);
	      
	      System.out.println("entity? "+ entity);
	      System.out.println("file_remove entity" + entity);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity( HttpStatus.BAD_REQUEST);
	    }
	    return entity; 
	    
	}
	
	 
}
