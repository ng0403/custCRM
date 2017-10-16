package com.core.plus.board.vo;

public class ReplyVO {
	
	private String BOARD_MNG_NO;
	private Integer BOARD_NO;
	private String REPLY_NO;
	private String REPLY_CONTENT;
	private String CREATED_BY;
	private String CREATED;
	private String UPDATED_BY;
	private String UPDATED;
	 
	public String getBOARD_MNG_NO() {
		return BOARD_MNG_NO;
	}

	public void setBOARD_MNG_NO(String bOARD_MNG_NO) {
		BOARD_MNG_NO = bOARD_MNG_NO;
	}

	public Integer getBOARD_NO() {
		return BOARD_NO;
	}
 
	public void setBOARD_NO(Integer bOARD_NO) {
		BOARD_NO = bOARD_NO;
	}  

	public String getREPLY_NO() {
		return REPLY_NO;
	} 
	public void setREPLY_NO(String rEPLY_NO) {
		REPLY_NO = rEPLY_NO;
	} 
	public String getREPLY_CONTENT() {
		return REPLY_CONTENT;
	} 
	public void setREPLY_CONTENT(String rEPLY_CONTENT) {
		REPLY_CONTENT = rEPLY_CONTENT;
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
		return "ReplyVO [BOARD_MNG_NO=" + BOARD_MNG_NO + ", BOARD_NO=" + BOARD_NO + ", REPLY_NO=" + REPLY_NO
				+ ", REPLY_CONTENT=" + REPLY_CONTENT + ", CREATED_BY=" + CREATED_BY + ", CREATED=" + CREATED
				+ ", UPDATED_BY=" + UPDATED_BY + ", UPDATED=" + UPDATED + "]";
	}
 

}
