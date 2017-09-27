package com.core.plus.login.vo; 

public class LoginVO {
	
	String user_id;
	String user_nm;
	String pwd;
	String act_yn;
	int pwd_err_cnt;
	
	public LoginVO(){}
	
	public LoginVO(String user_id, String user_nm, String pwd, String act_yn, int pwd_err_cnt) {
		super();
		this.user_id = user_id;
		this.user_nm = user_nm;
		this.pwd = pwd;
		this.act_yn = act_yn;
		this.pwd_err_cnt = pwd_err_cnt;
	}

	@Override
	public String toString() {
		return "LoginVO [user_id=" + user_id + ", user_nm=" + user_nm
				+ ", pwd=" + pwd + ", act_yn=" + act_yn
				+ ", pwd_err_cnt=" + pwd_err_cnt + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAct_yn() {
		return act_yn;
	}

	public void setAct_yn(String act_yn) {
		this.act_yn = act_yn;
	}

	public int getPwd_err_cnt() {
		return pwd_err_cnt;
	}

	public void setPwd_err_cnt(int pwd_err_cnt) {
		this.pwd_err_cnt = pwd_err_cnt;
	}

}

