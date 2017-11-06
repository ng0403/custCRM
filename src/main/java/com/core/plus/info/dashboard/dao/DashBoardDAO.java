package com.core.plus.info.dashboard.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.info.dashboard.vo.DashBoardVO;

public interface DashBoardDAO {

	DashBoardVO getCustCount();

	Map<String, List<String>> getCustChange();

	List<String> getCustIncrease();

	List<DashBoardVO> getCountList();

	DashBoardVO getAvgList();

}
