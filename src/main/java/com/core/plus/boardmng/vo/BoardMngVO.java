package com.core.plus.boardmng.vo;

import java.util.Date;

public class BoardMngVO {
	
	private String BOARD_MNG_NO;
	private String BOARD_MNG_CD;
	private String BOARD_NM;
	private String REPLY_FLG;
	private String FILE_ATTACH_FLG;
	private String NOTICE_FLG;
	private String ACTIVE_FLG;
	private String DEL_FLG;
	private String CREATED_BY;
	private String CREATED;
	private String UPDATED_BY;
	private String UPDATED;
	private String BOARD_MNG_NM;
	private String CODE_TXT;
	private String ACTIVE_FLGSTRING;
	
	
	public String getACTIVE_FLGSTRING() {
		return ACTIVE_FLGSTRING;
	}
	public void setACTIVE_FLGSTRING(String aCTIVE_FLGSTRING) {
		ACTIVE_FLGSTRING = aCTIVE_FLGSTRING;
	}
	public String getCODE_TXT() {
		return CODE_TXT;
	}
	public void setCODE_TXT(String cODE_TXT) {
		CODE_TXT = cODE_TXT;
	}
	public String getBOARD_MNG_NM() {
		return BOARD_MNG_NM;
	}
	public void setBOARD_MNG_NM(String bOARD_MNG_NM) {
		BOARD_MNG_NM = bOARD_MNG_NM;
	}
	public String getBOARD_MNG_NO() {
		return BOARD_MNG_NO;
	}
	public void setBOARD_MNG_NO(String bOARD_MNG_NO) {
		BOARD_MNG_NO = bOARD_MNG_NO;
	}
	public String getBOARD_MNG_CD() {
		return BOARD_MNG_CD;
	}
	public void setBOARD_MNG_CD(String bOARD_MNG_CD) {
		BOARD_MNG_CD = bOARD_MNG_CD;
	}
	public String getBOARD_NM() {
		return BOARD_NM;
	}
	public void setBOARD_NM(String bOARD_NM) {
		BOARD_NM = bOARD_NM;
	}
	public String getREPLY_FLG() {
		return REPLY_FLG;
	}
	public void setREPLY_FLG(String rEPLY_FLG) {
		REPLY_FLG = rEPLY_FLG;
	}
	public String getFILE_ATTACH_FLG() {
		return FILE_ATTACH_FLG;
	}
	public void setFILE_ATTACH_FLG(String fILE_ATTACH_FLG) {
		FILE_ATTACH_FLG = fILE_ATTACH_FLG;
	}
	public String getNOTICE_FLG() {
		return NOTICE_FLG;
	}
	public void setNOTICE_FLG(String nOTICE_FLG) {
		NOTICE_FLG = nOTICE_FLG;
	}
	public String getACTIVE_FLG() {
		return ACTIVE_FLG;
	}
	public void setACTIVE_FLG(String aCTIVE_FLG) {
		ACTIVE_FLG = aCTIVE_FLG;
	}
	public String getDEL_FLG() {
		return DEL_FLG;
	}
	public void setDEL_FLG(String dEL_FLG) {
		DEL_FLG = dEL_FLG;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}
	public String getCREATED() {
		return CREATED;
	}
	public void setCREATED(String cREATED) {
		CREATED = cREATED;
	}
	public String getUPDATED_BY() {
		return UPDATED_BY;
	}
	public void setUPDATED_BY(String uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}
	public String getUPDATED() {
		return UPDATED;
	}
	public void setUPDATED(String uPDATED) {
		UPDATED = uPDATED;
	}
	
	
	@Override
	public String toString() {
		return "BoardMngVO [BOARD_MNG_NO=" + BOARD_MNG_NO + ", BOARD_MNG_CD=" + BOARD_MNG_CD + ", BOARD_NM=" + BOARD_NM
				+ ", REPLY_FLG=" + REPLY_FLG + ", FILE_ATTACH_FLG=" + FILE_ATTACH_FLG + ", NOTICE_FLG=" + NOTICE_FLG
				+ ", ACTIVE_FLG=" + ACTIVE_FLG + ", DEL_FLG=" + DEL_FLG + ", CREATED_BY=" + CREATED_BY + ", CREATED="
				+ CREATED + ", UPDATED_BY=" + UPDATED_BY + ", UPDATED=" + UPDATED + ", BOARD_MNG_NM=" + BOARD_MNG_NM
				+ ", CODE_TXT=" + CODE_TXT + ", ACTIVE_FLGSTRING=" + ACTIVE_FLGSTRING + "]";
	}
	
	
	

}
