package com.core.plus.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.board.dao.ReplyDao;
import com.core.plus.board.vo.ReplyVO;
import com.core.plus.common.PagerVO;
 
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyDao replyDao;
	
	@Override
	public List<ReplyVO> listReply(Integer BOARD_NO) throws Exception {
		return replyDao.listReply(BOARD_NO);
	}

	@Override
	public void addReply(ReplyVO vo) throws Exception {
 		
		  replyDao.addReply(vo);
	}

	@Override
	public void removeReply(String REPLY_NO) {
		
		replyDao.removeReply(REPLY_NO);
		
	}

	@Override
	public PagerVO getReplyListCount(Map<String, Object> map) {
		int boardPageNum = (Integer)map.get("pageNum");
		int totalRowCount = replyDao.ReplyListCount("replyListCount", map);
		
		PagerVO page = new PagerVO(boardPageNum, totalRowCount,5, 999);
		
		return page;
	}

	@Override
	public List<ReplyVO> SearchList(Map<String, Object> map) {
		List<ReplyVO> list = replyDao.SearchList(map);
		return list;
	}
	
	
	//전체리스트 개수 
	@Override
	public PagerVO replyListCount(Map<String, Object> replyMap) {
		System.out.println("ReplyListCount service " +  replyMap.toString());
		int actPageNum = (Integer) replyMap.get("replyPageNum");
		// 현재 페이지 얻어오기
		PagerVO page = new PagerVO(actPageNum, 0, 5, 10);
		// 전체 글의 갯수 구하기
		System.out.println("actPage Num " + actPageNum);
		int totalRowCount = replyDao.replyListCount(replyMap);
		System.out.println("totalRowCount ? " + totalRowCount);		
		page = new PagerVO(actPageNum, totalRowCount, 5, 10);
	
		return page;
	}

	//전체리스트 
	@Override
	public List<ReplyVO> replyAllList(Map<String,Object> replyMap) {
		return replyDao.replyAllList(replyMap);
	}
	@Override
	public void AnswerFlg(int BOARD_NO) {
		System.out.println("service map ? " + BOARD_NO);
		replyDao.AnswerFlg(BOARD_NO); 
	}

	@Override
	public void AnswerFlgN(Integer BOARD_NO) {
		replyDao.AnswerFlgN(BOARD_NO);
		
	}

	@Override
	public int replyCount(Integer BOARD_NO) {
		 
		return replyDao.replyCount(BOARD_NO);
	}
}
