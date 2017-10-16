	package com.core.plus.boardmng.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.boardmng.dao.BoardMngDao;
import com.core.plus.boardmng.vo.BoardMngVO;
import com.core.plus.common.PagerVO;

@Service
public class BoardMngServiceImpl implements BoardMngService {

	@Autowired
	BoardMngDao boardmngDao;
	
	@Override
	public PagerVO getBoardMngListCount(Map<String, Object> map) {
		System.out.println("getboardmnglistcount" + map.toString());
		int boardPageNum = (Integer)map.get("pageNum");
		int totalRowCount = boardmngDao.BoardMngListCount("boardmngListCount", map);
		
		PagerVO page = new PagerVO(boardPageNum, totalRowCount, 6, 999);
		
		return page;
	}
	
	//전체리스트 개수 
	@Override
	public PagerVO getBoardMngListCountP(Map<String, Object> boardMap) {
		System.out.println("BoardListCount service " +  boardMap.toString());
		int actPageNum = (Integer) boardMap.get("boardPageNum");
		// 현재 페이지 얻어오기
		PagerVO page = new PagerVO(actPageNum, 0, 6, 6);
		// 전체 글의 갯수 구하기
		System.out.println("actPage Num " + actPageNum);
		int totalRowCount = boardmngDao.BoardMngListCountP(boardMap);
		System.out.println("totalRowCount ? " + totalRowCount);		
		page = new PagerVO(actPageNum, totalRowCount, 6, 6);
	
		return page;
	}

	@Override
	public List<Object> list(Map map) {
		// TODO Auto-generated method stub
 		return boardmngDao.list(map);

	}

	@Override
	public BoardMngVO detail(String BOARD_MNG_NO) {
			
		return boardmngDao.detail(BOARD_MNG_NO);
	}

	@Override
	public void modify(BoardMngVO vo) {
		boardmngDao.modify(vo);
	}

	@Override
	public void add(BoardMngVO vo) {

		boardmngDao.add(vo);
	}

	@Override
	public void remove(String dc) {

		boardmngDao.remove(dc);
		
	}

	@Override
	public List<BoardMngVO> ajaxlist() {

		return boardmngDao.ajaxlist();
	}

	@Override
	public List<BoardMngVO> codelist() {
		
		return boardmngDao.codelist();
		
	}

}
