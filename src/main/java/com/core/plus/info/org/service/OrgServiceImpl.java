package com.core.plus.info.org.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.plus.common.PagerVO;
import com.core.plus.info.org.dao.OrgDao;
import com.core.plus.info.org.vo.OrgVO;

@Service("OrgService")
public class OrgServiceImpl implements OrgService{
	@Resource
	OrgDao orgDao;
	
	@Override
	public List<OrgVO> orgList(Map<String, Object> orgMap) {
		List<OrgVO> list = orgDao.orgList(orgMap);		
		return list;
	}

	@Override
	public OrgVO orgDetail(String org_id) {
		OrgVO  orgVO = orgDao.orgDetail(org_id);
		return orgVO;
	}

	@Override
	public int orgInsert(OrgVO orgVO) {
		int result = 0;
		orgDao.orgInsert(orgVO);
		return result;
	}

	@Override
	public int orgUpdate(OrgVO orgVO) {		
		int result = 0;
		orgDao.orgUpdate(orgVO);
		return result;
	}

	@Override
	public List<OrgVO> repOrgList(Map<String, Object> orgMap) {
		List<OrgVO> list = orgDao.repOrgList(orgMap);		
		return list;
	}

	@Override
	public int getOrgChkDelete(String[] org_id) {
		int orgChkDel_result = orgDao.getOrgChkDelete(org_id);
		return orgChkDel_result;
	}

	@Override
	public PagerVO getOrgListCount(Map<String, Object> map) {
		int PageNum = (Integer) map.get("pageNum");
		 int pageListCount = orgDao.getOrgListCount(map);
		 PagerVO page = new PagerVO(PageNum, pageListCount, 10, 10);
		return page;
	}

	@Override
	public PagerVO getRepUserListCount(Map<String, Object> orgMap) {
		int PageNum = (Integer) orgMap.get("pageNum");
		 int pageListCount = orgDao.getRepUserListCount(orgMap);
		 PagerVO page = new PagerVO(PageNum, pageListCount, 5, 10);
		return page;
	}
	
}
