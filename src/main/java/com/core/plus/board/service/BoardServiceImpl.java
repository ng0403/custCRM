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
	public PagerVO getBoardListCount(Map<String, Object> map) {
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
 
	//조회수 증가 
	@Override
	public void viewadd(int BOARD_NO) {
		
		boardDao.viewadd(BOARD_NO);
		
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
