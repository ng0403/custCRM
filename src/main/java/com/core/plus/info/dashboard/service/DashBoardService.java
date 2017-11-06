package com.core.plus.info.dashboard.service;

import java.util.List;

import com.core.plus.info.dashboard.vo.DashBoardVO;

public interface DashBoardService {

	DashBoardVO getCustCount();

	List<DashBoardVO> countList();

	DashBoardVO avgList();

}
