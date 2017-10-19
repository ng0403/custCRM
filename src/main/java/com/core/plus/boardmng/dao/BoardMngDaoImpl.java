package com.core.plus.boardmng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.core.plus.boardmng.vo.BoardMngVO;

@Repository
public class BoardMngDaoImpl implements BoardMngDao {
	
	@Autowired
	SqlSession sqlSession;
 
 	 @Override
		public int BoardMngListCount(Map<String, Object> boardMap){
			System.out.println("BoardListCount Dao " + boardMap.toString());
			int totalCount = 0;
			try {
				totalCount = sqlSession.selectOne("boardmng.boardMngListCount", boardMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return totalCount;
		} 
 	 
	//전체리스트 개수
	@Override
	public int BoardMngListCountP(Map<String, Object> boardMap) {
		System.out.println("board Map Dao "  + boardMap.toString());
		int totalCount = 0;
		try {
			totalCount = sqlSession.selectOne("boardmng.boardMngListCount", boardMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}

	@Override
	public List<Object> list(Map map) {
		
		return sqlSession.selectList("BoardMngList", map);


	}

	@Override
	public BoardMngVO detail(String BOARD_MNG_NO) {

		return sqlSession.selectOne("boardmng.ReadPage", BOARD_MNG_NO);
	}

	@Override
	public void modify(BoardMngVO vo) {
		sqlSession.update("boardmng.Modify", vo);
	}

	@Override
	public void add(BoardMngVO vo) {
		sqlSession.insert("boardmng.Add", vo);
		
	}

	@Override
	public void remove(String dc) {

		sqlSession.delete("boardmng.Delete", dc);
	}

	@Override
	public List<BoardMngVO> ajaxlist() {
 
		return sqlSession.selectList("boardmng.ajaxList");
	}

	@Override
	public List<BoardMngVO> codelist() {
		
	return sqlSession.selectList("boardmng.codelist");
		
	}

}
