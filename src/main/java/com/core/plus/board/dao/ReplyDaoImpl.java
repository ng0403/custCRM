package com.core.plus.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.core.plus.board.vo.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ReplyVO> listReply(Integer BOARD_NO) throws Exception {
 		System.out.println("DAO" +BOARD_NO);

	    return sqlSession.selectList("replyList", BOARD_NO);

	}

	@Override
	public void addReply(ReplyVO vo) throws Exception {

		sqlSession.insert("addReply", vo);
		
	}
	
	@Override
	public void modifyReply(ReplyVO vo) {
		sqlSession.update("modifyReply", vo);		
	}

	@Override
	public void removeReply(String REPLY_NO) {
		
		sqlSession.delete("removeReply", REPLY_NO);
		
	}
 
	@Override
	public List<ReplyVO> SearchList(Map<String, Object> map) {
		
		return sqlSession.selectList("reply.SearchList", map);

	}
  
	@Override
	public int getReplyListRow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int totalCount = 0;
		
		try {
			totalCount = sqlSession.selectOne("reply.selectTotalCount", map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return totalCount;
	}

	//전체리스트 
	@Override
	public List<ReplyVO> replyAllList(Map<String, Object> boardMap) {
		List<ReplyVO> obj = sqlSession.selectList("reply.selectAll", boardMap);
		return obj;
	}

	@Override
	public void AnswerFlg(int BOARD_NO) {
		System.out.println("AnswerFlg " + BOARD_NO);
		sqlSession.update("board.AnswerFlg", BOARD_NO);
		
	}

	@Override
	public void AnswerFlgN(Integer BOARD_NO) {
		sqlSession.update("board.AnswerFlgN", BOARD_NO);
		
	}

	@Override
	public int replyCount(Integer BOARD_NO) {
		 
		return sqlSession.selectOne("replyCount", BOARD_NO);
	}

	
}
