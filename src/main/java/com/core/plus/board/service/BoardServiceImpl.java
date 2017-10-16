package com.core.plus.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.board.dao.BoardDao;
import com.core.plus.board.vo.BoardVO;
import com.core.plus.boardmng.vo.BoardMngVO;
import com.core.plus.common.PagerVO;
 
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardVO> list(Map map) {
		System.out.println("boardList Service " + map.toString());
 		return boardDao.list(map);
	}

	@Override
	public BoardVO detail(int BOARD_NO) {
 		return boardDao.detail(BOARD_NO);
	}

	@Override
	public void insert(BoardVO vo) {
		System.out.println("insert dao " + vo.toString());
 		 boardDao.insert(vo);
	}

	@Override
	public void modify(BoardVO vo) {
		
		boardDao.modify(vo);
		
	}

	@Override
	public void removeBoard(String dc) {

		boardDao.removeBoard(dc);
	}

	@Override
	public BoardVO read(int BOARD_NO) {
		// TODO Auto-generated method stub
		return boardDao.read(BOARD_NO);
	}

	@Override
	public List<BoardVO> ajaxlist() {
		
 		return boardDao.ajaxlist();
	}
 
	
	@Override
	public PagerVO getBoardListCount(Map<String, Object> map) {
		System.out.println("getboardListCOunt  " + map.toString());
		int PageNum = (Integer)map.get("PageNum");
		int totalRowCount = boardDao.BoardListCount("boardListCount", map);
		
		PagerVO page = new PagerVO(PageNum, totalRowCount, 10, 999);
		
		return page;
	}

	@Override
	public List<BoardVO> SearchList(Map<String, Object> map) {
		List<BoardVO> list = boardDao.SearchList(map);
		return list;
	}

	@Override
	public void insertAttachData(BoardVO attach) {
		boardDao.insertAttachData(attach);
	}

	@Override
	public Object searchOneFiledata(String FILE_CD) {
 		Object obj =  boardDao.searchOneFiledata(FILE_CD); 
 		return obj;
	}

	@Override
	public BoardVO ReadFilePage(int BOARD_NO) {
		// TODO Auto-generated method stub
		return boardDao.ReadFilePage(BOARD_NO);
	}

	@Override
	public BoardVO readFileModify(int BOARD_NO) {
	 
		return boardDao.readFileModify(BOARD_NO);
	}

	@Override
	public void removeDetail(int BOARD_NO) {
		
		 boardDao.removeDetail(BOARD_NO);
		
	}

	@Override
	public List<BoardVO> QnAajaxlist() {
		 
		return boardDao.QnAajaxlist();
	}

	
	@Override
	public void viewadd(int BOARD_NO) {
		
		boardDao.viewadd(BOARD_NO);
		
	}
	
	
	//전체리스트 개수 
			@Override
			public PagerVO boardListCount(Map<String, Object> boardMap) {
				System.out.println("BoardListCount service " +  boardMap.toString());
				int actPageNum = (Integer) boardMap.get("boardPageNum");
				// 현재 페이지 얻어오기
				PagerVO page = new PagerVO(actPageNum, 0, 10, 10);
				// 전체 글의 갯수 구하기
				System.out.println("actPage Num " + actPageNum);
				int totalRowCount = boardDao.boardListCount(boardMap);
				System.out.println("totalRowCount ? " + totalRowCount);		
				page = new PagerVO(actPageNum, totalRowCount, 10, 10);
			
				return page;
			}
 
			//전체리스트 
			@Override
			public List<BoardVO> boardAllList(Map<String,Object> boardMap) {
				return boardDao.boardAllList(boardMap);
			}

			@Override
			public void AnswerFlg(int BOARD_NO) {
				System.out.println("service map ? " + BOARD_NO);
				  boardDao.AnswerFlg(BOARD_NO); 
			}

			@Override
			public List<BoardVO> fileboardList() {
 				return boardDao.fileboardList();
			}

			@Override
			public void file_remove(String FILE_CD) {
				
				boardDao.file_remove(FILE_CD);
			}

			@Override
			public void file_removeMd(String FILE_CD) {
				boardDao.file_removeMd(FILE_CD);
				
			}

			@Override
			public BoardMngVO checkBoardMngNo(String BOARD_MNG_NO) {
 				
				return boardDao.checkBoardMngNo(BOARD_MNG_NO);
			}
 
}
