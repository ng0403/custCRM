package com.core.plus.board.vo;

import java.util.Date;

public class BoardVO {
	
	private Integer BOARD_NO;
	private String BOARD_MNG_NO;
	private String FILE_CD;
	private String TITLE;
	private String CONTENT;
	private int VIEW_CNT;
	private String QUESTION_TYPE_CD;
	private String ANSWER_FLG;
	private String ACTIVE_FLG;
	private String DEL_FLG;
	private String CREATED_BY;
	private Date CREATED;
	private String UPDATED_BY;
	private Date UPDATED;
	private String delcode;
	private String BOARD_MNG_CD;
	private String REPLY_FLG;
	private String QUESTION_TITLE;
	private String CD_NM;
 	private int FILE_NO;
	private String FILE_PATH;
	private String FILE_NM;
	private String FILE_EXT;  
 	
	public String getCD_NM() {
		return CD_NM;
	}
	public void setCD_NM(String cD_NM) {
		CD_NM = cD_NM;
	}
	public int getFILE_NO() {
		return FILE_NO;
	}
	public void setFILE_NO(int fILE_NO) {
		FILE_NO = fILE_NO;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public String getFILE_NM() {
		return FILE_NM;
	}
	public void setFILE_NM(String fILE_NM) {
		FILE_NM = fILE_NM;
	}
	public String getFILE_EXT() {
		return FILE_EXT;
	}
	public void setFILE_EXT(String fILE_EXT) {
		FILE_EXT = fILE_EXT;
	}
	 
	public String getQUESTION_TITLE() {
		return QUESTION_TITLE;
	}
	public void setQUESTION_TITLE(String qUESTION_TITLE) {
		QUESTION_TITLE = qUESTION_TITLE;
	}
	public String getREPLY_FLG() {
		return REPLY_FLG;
	}
	public void setREPLY_FLG(String rEPLY_FLG) {
		REPLY_FLG = rEPLY_FLG;
	}
	public String getBOARD_MNG_CD() {
		return BOARD_MNG_CD;
	}
	public void setBOARD_MNG_CD(String bOARD_MNG_CD) {
		BOARD_MNG_CD = bOARD_MNG_CD;
	}
	public String getDelcode() {
		return delcode;
	}
	public void setDelcode(String delcode) {
		this.delcode = delcode;
	}
	public Integer getBOARD_NO() {
		return BOARD_NO;
	}
	public void setBOARD_NO(Integer bOARD_NO) {
		BOARD_NO = bOARD_NO;
	}
	public String getBOARD_MNG_NO() {
		return BOARD_MNG_NO;
	}
	public void setBOARD_MNG_NO(String bOARD_MNG_NO) {
		BOARD_MNG_NO = bOARD_MNG_NO;
	}
	public String getFILE_CD() {
		return FILE_CD;
	}
	public void setFILE_CD(String fILE_CD) {
		FILE_CD = fILE_CD;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public int getVIEW_CNT() {
		return VIEW_CNT;
	}
	public void setVIEW_CNT(int vIEW_CNT) {
		VIEW_CNT = vIEW_CNT;
	}
	public String getQUESTION_TYPE_CD() {
		return QUESTION_TYPE_CD;
	}
	public void setQUESTION_TYPE_CD(String qUESTION_TYPE_CD) {
		QUESTION_TYPE_CD = qUESTION_TYPE_CD;
	}
	public String getANSWER_FLG() {
		return ANSWER_FLG;
	}
	public void setANSWER_FLG(String aNSWER_FLG) {
		ANSWER_FLG = aNSWER_FLG;
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
	 
	public Date getCREATED() {
		return CREATED;
	}
	public void setCREATED(Date cREATED) {
		CREATED = cREATED;
	}
	public String getUPDATED_BY() {
		return UPDATED_BY;
	}
	public void setUPDATED_BY(String uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}
	public Date getUPDATED() {
		return UPDATED;
	}
	public void setUPDATED(Date uPDATED) {
		UPDATED = uPDATED;
	}
	@Override
	public String toString() {
		return "BoardVO [BOARD_NO=" + BOARD_NO + ", BOARD_MNG_NO=" + BOARD_MNG_NO + ", FILE_CD=" + FILE_CD + ", TITLE="
				+ TITLE + ", CONTENT=" + CONTENT + ", VIEW_CNT=" + VIEW_CNT + ", QUESTION_TYPE_CD=" + QUESTION_TYPE_CD
				+ ", ANSWER_FLG=" + ANSWER_FLG + ", ACTIVE_FLG=" + ACTIVE_FLG + ", DEL_FLG=" + DEL_FLG + ", CREATED_BY="
				+ CREATED_BY + ", CREATED=" + CREATED + ", UPDATED_BY=" + UPDATED_BY + ", UPDATED=" + UPDATED
				+ ", delcode=" + delcode + ", BOARD_MNG_CD=" + BOARD_MNG_CD + ", REPLY_FLG=" + REPLY_FLG
				+ ", QUESTION_TITLE=" + QUESTION_TITLE + ", CD_NM=" + CD_NM + ", FILE_NO=" + FILE_NO + ", FILE_PATH="
				+ FILE_PATH + ", FILE_NM=" + FILE_NM + ", FILE_EXT=" + FILE_EXT + "]";
	} 
	 
  
}
