package com.core.plus.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.core.plus.board.vo.BoardVO;
import com.core.plus.boardmng.vo.BoardMngVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	public void setSqlSessionTemplate(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BoardVO> list(Map map) {	
 		 List<BoardVO> qwer = sqlSession.selectList("BoardList", map);
 		return qwer ;
	}

	@Override
	public BoardVO detail(int BOARD_NO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.ReadPage", BOARD_NO);
	}

	@Override
	public void insert(BoardVO vo) {
		 System.out.println("insert Dao/ " + vo.toString());
 		 sqlSession.insert("InsertBoard",vo);
	}

	@Override
	public void modify(BoardVO vo) {
		
		sqlSession.update("ModifyBoard" ,vo);
		
	}

	@Override
	public void removeBoard(String dc) {
 		sqlSession.update("removeBoard", dc);
	}

	@Override
	public BoardVO read(int BOARD_NO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("readBoard", BOARD_NO);
	}

 

	@Override
	public int BoardListCount(String string, Map<String, Object> map) {
 		int totalCount = 0;
		try {
			totalCount = sqlSession.selectOne("board.boardListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	@Override
	public List<BoardVO> SearchList(Map<String, Object> map) {
		
		return sqlSession.selectList("board.SearchList", map);

	}

	@Override
	public void insertAttachData(BoardVO attach) {
		
		sqlSession.insert("board.insertAttachData", attach);
		
	}

	@Override
	public Object searchOneFiledata(String FILE_CD) {
  		Object obj = sqlSession.selectOne("board.searchOneFiledata", FILE_CD);
  		return obj;
	}

	@Override
	public BoardVO ReadFilePage(int BOARD_NO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.ReadFilePage", BOARD_NO);
		 
	}

	@Override
	public BoardVO readFileModify(int BOARD_NO) {
		 
		return sqlSession.selectOne("readBoardFile", BOARD_NO);
	}

	@Override
	public void removeDetail(int BOARD_NO) {
		sqlSession.update("removeDetail", BOARD_NO);
		
	}

 
	@Override
	public void viewadd(int BOARD_NO) {
		
		sqlSession.update("viewadd",BOARD_NO);
		
	}
 
	 
			@Override
			public List<BoardVO> fileboardList() {
				// TODO Auto-generated method stub
				return sqlSession.selectList("board.fileboardList");
			}

			@Override
			public void file_remove(String FILE_CD) {
				
				sqlSession.delete("board.file_remove", FILE_CD);
				
			}

			@Override
			public void file_removeMd(String FILE_CD) {
				sqlSession.update("board.file_removeMd", FILE_CD);
				
			}

			@Override
			public BoardMngVO checkBoardMngNo(String BOARD_MNG_NO) {
				//  
				return sqlSession.selectOne("board.checkBoardMngNo", BOARD_MNG_NO);
			}

}
