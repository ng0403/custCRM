package com.core.plus.board.service;

import java.util.List;
import java.util.Map;

import com.core.plus.board.vo.ReplyVO;
import com.core.plus.common.PagerVO;


 
public interface ReplyService {

	  public List<ReplyVO> listReply(Integer bno) throws Exception;
	  public void addReply(ReplyVO vo) throws Exception;
	  public void removeReply(String REPLY_NO); 
 	  public List<ReplyVO> SearchList(Map<String, Object> map);

	  PagerVO getReplyListRow(Map<String, Object> map); 
 	  public List<ReplyVO> replyAllList(Map<String,Object> boardMap); //댓글 리스트 
	  public void AnswerFlg(int BOARD_NO);
	  public void AnswerFlgN(Integer bOARD_NO);  // 댓글 삭제시 Q&A 답변 플래그
	  public int replyCount(Integer bOARD_NO);  //댓글 개수 구하기

}
