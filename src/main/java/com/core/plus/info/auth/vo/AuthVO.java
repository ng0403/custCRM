package com.core.plus.info.auth.vo;

public class AuthVO{
	
	private String auth_id;		//권한id
	private String auth_nm;		//권한이름
	private String act_yn;		//활성화여부
	private String fin_mdfy_id; //최종수정자id
//	private String crt_id;		//생성자id
//	private String crt_dt;		//생성일시
//	private String mdfy_id;		//수정자id
//	private String mdfy_dt;		//수정일시
	
	private String retrv_auth_yn;     //조회권한여부
	private String create_auth_yn;		//생성권한여부
	private String mdfy_auth_yn;		//수정권한여부
	private String del_auth_yn;		//삭제권한여부
	
	private String user_id;     //사용자id
	private String user_nm;		//사용자이름
	private String cell_ph1;  	//핸드폰1
	private String cell_ph2;  	//핸드폰2
	private String cell_ph3;  	//핸드폰3
	private String cell_ph;  	//핸드폰1 + 핸드폰2 + 핸드폰3
	private String home_ph1;	//집전화1
	private String home_ph2;	//집전화2
	private String home_ph3;	//집전화3
	private String home_ph;		//집전화1 + 집전화2 + 집전화3
	private String com_ph1;		//회사전화1
	private String com_ph2;		//회사전화2
	private String com_ph3;		//회사전화3
	private String com_ph;		//회사전화1 + 회사전화2 + 회사전화3
	private String email_id;	//메일id
	private String email_dm;	//메일주소
	private String email;	    //메일id + 메일주소
	private String org_id;		//부서id
	
	public String getFin_mdfy_id() {
		return fin_mdfy_id;
	}
	public void setFin_mdfy_id(String fin_mdfy_id) {
		this.fin_mdfy_id = fin_mdfy_id;
	}
	public String getRetrv_auth_yn() {
		return retrv_auth_yn;
	}
	public void setRetrv_auth_yn(String retrv_auth_yn) {
		this.retrv_auth_yn = retrv_auth_yn;
	}
	public String getCreate_auth_yn() {
		return create_auth_yn;
	}
	public void setCreate_auth_yn(String create_auth_yn) {
		this.create_auth_yn = create_auth_yn;
	}
	public String getMdfy_auth_yn() {
		return mdfy_auth_yn;
	}
	public void setMdfy_auth_yn(String mdfy_auth_yn) {
		this.mdfy_auth_yn = mdfy_auth_yn;
	}
	public String getDel_auth_yn() {
		return del_auth_yn;
	}
	public void setDel_auth_yn(String del_auth_yn) {
		this.del_auth_yn = del_auth_yn;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public String getAuth_nm() {
		return auth_nm;
	}
	public void setAuth_nm(String auth_nm) {
		this.auth_nm = auth_nm;
	}
	public String getAct_yn() {
		return act_yn;
	}
	public void setAct_yn(String act_yn) {
		this.act_yn = act_yn;
	}
//	public String getCrt_id() {
//		return crt_id;
//	}
//	public void setCrt_id(String crt_id) {
//		this.crt_id = crt_id;
//	}
//	public String getCrt_dt() {
//		return crt_dt;
//	}
//	public void setCrt_dt(String crt_dt) {
//		this.crt_dt = crt_dt;
//	}
//	public String getMdfy_id() {
//		return mdfy_id;
//	}
//	public void setMdfy_id(String mdfy_id) {
//		this.mdfy_id = mdfy_id;
//	}
//	public String getMdfy_dt() {
//		return mdfy_dt;
//	}
//	public void setMdfy_dt(String mdfy_dt) {
//		this.mdfy_dt = mdfy_dt;
//	}
//	public String getRtrv_yn() {
//		return rtrv_yn;
//	}
//	public void setRtrv_yn(String rtrv_yn) {
//		this.rtrv_yn = rtrv_yn;
//	}
//	public String getCrt_yn() {
//		return crt_yn;
//	}
//	public void setCrt_yn(String crt_yn) {
//		this.crt_yn = crt_yn;
//	}
//	public String getMdfy_yn() {
//		return mdfy_yn;
//	}
//	public void setMdfy_yn(String mdfy_yn) {
//		this.mdfy_yn = mdfy_yn;
//	}
//	public String getDel_yn() {
//		return del_yn;
//	}
//	public void setDel_yn(String del_yn) {
//		this.del_yn = del_yn;
//	}
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
	public String getCell_ph1() {
		return cell_ph1;
	}
	public void setCell_ph1(String cell_ph1) {
		this.cell_ph1 = cell_ph1;
	}
	public String getCell_ph2() {
		return cell_ph2;
	}
	public void setCell_ph2(String cell_ph2) {
		this.cell_ph2 = cell_ph2;
	}
	public String getCell_ph3() {
		return cell_ph3;
	}
	public void setCell_ph3(String cell_ph3) {
		this.cell_ph3 = cell_ph3;
	}
	public String getHome_ph1() {
		return home_ph1;
	}
	public void setHome_ph1(String home_ph1) {
		this.home_ph1 = home_ph1;
	}
	public String getHome_ph2() {
		return home_ph2;
	}
	public void setHome_ph2(String home_ph2) {
		this.home_ph2 = home_ph2;
	}
	public String getHome_ph3() {
		return home_ph3;
	}
	public void setHome_ph3(String home_ph3) {
		this.home_ph3 = home_ph3;
	}
	public String getCom_ph1() {
		return com_ph1;
	}
	public void setCom_ph1(String com_ph1) {
		this.com_ph1 = com_ph1;
	}
	public String getCom_ph2() {
		return com_ph2;
	}
	public void setCom_ph2(String com_ph2) {
		this.com_ph2 = com_ph2;
	}
	public String getCom_ph3() {
		return com_ph3;
	}
	public void setCom_ph3(String com_ph3) {
		this.com_ph3 = com_ph3;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getEmail_dm() {
		return email_dm;
	}
	public void setEmail_dm(String email_dm) {
		this.email_dm = email_dm;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getCell_ph() {
		return cell_ph;
	}
	public void setCell_ph(String cell_ph) {
		this.cell_ph = cell_ph;
	}
	public String getHome_ph() {
		return home_ph;
	}
	public void setHome_ph(String home_ph) {
		this.home_ph = home_ph;
	}
	public String getCom_ph() {
		return com_ph;
	}
	public void setCom_ph(String com_ph) {
		this.com_ph = com_ph;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
