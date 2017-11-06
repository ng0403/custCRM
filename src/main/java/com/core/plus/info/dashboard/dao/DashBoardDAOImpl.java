package com.core.plus.info.dashboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.core.plus.info.dashboard.vo.DashBoardVO;

@Repository
public class DashBoardDAOImpl implements DashBoardDAO{
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	@Override
	public DashBoardVO getCustCount() {
		// TODO Auto-generated method stub
		DashBoardVO dvo = sqlSession.selectOne("dashBoard.getCustCount");
		
		
		return dvo;
	}

	@Override
	public Map<String, List<String>> getCustChange() {
		// TODO Auto-generated method stub
		Map<String, List<String>> map = new HashMap<String,List<String>>();
		List<String> date = sqlSession.selectList("dashBoard.getDate");
		List<String> c_change = sqlSession.selectList("dashBoard.getC_Change");
		
		map.put("date", date);
		map.put("c_change", c_change);
		
		return map;
	}

	@Override
	public List<String> getCustIncrease() {
		// TODO Auto-generated method stub
		List<String> c_increase = sqlSession.selectList("dashBoard.getC_Increase");
		return c_increase;
	}

	@Override
	public List<DashBoardVO> getCountList() {
		// TODO Auto-generated method stub
		List<DashBoardVO> countList = sqlSession.selectList("dashBoard.getCountList");
		return countList;
	}

	@Override
	public DashBoardVO getAvgList() {
		// TODO Auto-generated method stub
		DashBoardVO avgList = sqlSession.selectOne("dashBoard.getAvgList");
		return avgList;
	}

}
