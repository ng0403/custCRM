package com.core.plus.board.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.board.vo.ReplyVO;

public interface ReplyDao {

	  public List<ReplyVO> listReply(Integer BOARD_NO) throws Exception;
	  public void addReply(ReplyVO vo) throws Exception;
	  public void removeReply(String REPLY_NO);
	  int ReplyListCount(String string, Map<String, Object> map);
	 public List<ReplyVO> SearchList(Map<String, Object> map); 
	 
	 
		public int replyListCount(Map<String, Object> replyMap);
		public List<ReplyVO> replyAllList(Map<String, Object> replyMap); 
		
		public void AnswerFlg(int BOARD_NO);

		public void AnswerFlgN(Integer bOARD_NO);
		public int replyCount(Integer bOARD_NO); // 댓글 개수 구하기
}
