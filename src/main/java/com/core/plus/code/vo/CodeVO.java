package com.core.plus.code.vo;

public class CodeVO {

	private String code_no;
	private String code;
	private String code_name;
	private String display_yn;
	private String par_code_no;
	private String create_date;
	private String update_date;
	
	public String getCode_no() {
		return code_no; 
	}
	public void setCode_no(String code_no) {
		this.code_no = code_no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getPar_code_no() {
		return par_code_no;
	}
	public void setPar_code_no(String par_code_no) {
		this.par_code_no = par_code_no;
	}
	@Override
	public String toString() {
		return "CodeVO [code_no=" + code_no + ", code=" + code + ", code_name=" + code_name + ", display_yn="
				+ display_yn + ", par_code_no=" + par_code_no + ", create_date=" + create_date + ", update_date="
				+ update_date + "]";
	}
	
}
