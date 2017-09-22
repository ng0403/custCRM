package com.core.plus.info.org.service;

import java.util.List;
import java.util.Map;

import com.core.plus.common.PagerVO;
import com.core.plus.info.org.vo.OrgVO;

public interface OrgService {
	public List<OrgVO> orgList(Map<String, Object> orgMap);
	public OrgVO orgDetail(String org_id);
	public int orgInsert(OrgVO orgVO);
	public int orgUpdate(OrgVO orgVO);
	public List<OrgVO> repOrgList(Map<String, Object> orgMap);
	
	public int getOrgChkDelete(String[] org_id);
	public PagerVO getOrgListCount(Map<String, Object> map);
	public PagerVO getRepUserListCount(Map<String, Object> orgMap);
	
}
