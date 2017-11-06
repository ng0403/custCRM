package com.core.plus.info.dashboard.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.plus.info.dashboard.dao.DashBoardDAO;
import com.core.plus.info.dashboard.vo.DashBoardVO;

@Service("DashBoardService")
public class DashBoardServiceImpl implements DashBoardService{
	@Autowired
	DashBoardDAO dashBoardDAO;

	@Override
	public DashBoardVO getCustCount() {
		// TODO Auto-generated method stub
		DashBoardVO dvo = dashBoardDAO.getCustCount();
		Map<String, List<String>> map = dashBoardDAO.getCustChange();
		dvo.setDate(map.get("date"));
		dvo.setCust_change(map.get("c_change"));
		List<String> c_increase = dashBoardDAO.getCustIncrease();
		dvo.setCust_increase(c_increase);
		return dvo;
	}

	@Override
	public List<DashBoardVO> countList() {
		// TODO Auto-generated method stub
		List<DashBoardVO> countList = dashBoardDAO.getCountList();
		return countList;
	}

	@Override
	public DashBoardVO avgList() {
		// TODO Auto-generated method stub
		DashBoardVO avgList = dashBoardDAO.getAvgList();
		return avgList;
	}

}
