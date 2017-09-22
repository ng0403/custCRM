package com.core.plus.info.org.dao;

import java.util.List;
import java.util.Map;

import com.core.plus.info.org.vo.OrgVO;

public interface OrgDao {
	public List<OrgVO> orgList(Map<String, Object> orgMap);
	public OrgVO orgDetail(String org_id);
	public int orgInsert(OrgVO orgVO);
	public int orgUpdate(OrgVO orgVO);
	public List<OrgVO> repOrgList(Map<String, Object> orgMap);
	
	public int getOrgChkDelete(String[] org_id);
	public int getOrgListCount(Map<String, Object> map);
	public int getRepUserListCount(Map<String, Object> orgMap);
}
