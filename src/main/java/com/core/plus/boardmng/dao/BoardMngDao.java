package com.core.plus.boardmng.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.boardmng.vo.BoardMngVO;

public interface BoardMngDao {
	int BoardMngListCount(String string, Map<String, Object> map);
	public List<Object> list(Map map);
	public BoardMngVO detail(String BOARD_MNG_NO);
	public void modify(BoardMngVO vo);
	public void add(BoardMngVO vo);
	public void remove(String dc);
	public List<BoardMngVO> ajaxlist();
	public List<BoardMngVO> codelist();
 
	public int BoardMngListCountP(Map<String, Object> boardMap);

}
