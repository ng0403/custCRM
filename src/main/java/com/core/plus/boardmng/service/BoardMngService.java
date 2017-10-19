package com.core.plus.boardmng.service;

import java.util.List;
import java.util.Map;

import com.core.plus.boardmng.vo.BoardMngVO;
import com.core.plus.common.PagerVO;

public interface BoardMngService {
 	  public PagerVO getBoardMngListCount(Map<String, Object> boardMap); //보드 페이징

	  public List<Object> list(Map map);
	  public BoardMngVO detail(String BOARD_MNG_NO);
	  public void modify(BoardMngVO vo);
	  public void add(BoardMngVO vo);
	  public void remove(String dc);
	  public List<BoardMngVO> ajaxlist();
	  public List<BoardMngVO> codelist();
	  PagerVO getBoardMngListCountP(Map<String, Object> map);	//페이징처리
	
}
