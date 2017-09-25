package com.core.plus.info.menu.vo;

public class MenuVo {
	
	private String menu_id;		//메뉴id
	private String menu_nm;		//메뉴이름
	private String menu_url;	//메뉴url
	private String menu_lev;	//메뉴레벨
	private String menu_lev_cd_nm; //메뉴레벨이름
	private String up_menu_id;	//상위메뉴id
	private String act_yn;		//활성화여부
//	private String crt_id;		//생성자id
	private String create_date;		//생성일시
	private String fin_mdfy_id;		//수정자id
//	private String mdfy_dt;		//수정일시
	
	private String retrv_auth_yn;     //조회권한여부
	private String create_auth_yn;		//생성권한여부
	private String mdfy_auth_yn;		//수정권한여부
	private String del_auth_yn;		//삭제권한여부
	
	private String auth_id;     //권한id
	private String up_id;       //상위메뉴id로 출력
	
	private String user_id;     //사용자id
	private int seq_no;     //사용자id
	
	private String mi;

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_nm() {
		return menu_nm;
	}

	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_lev() {
		return menu_lev;
	}

	public void setMenu_lev(String menu_lev) {
		this.menu_lev = menu_lev;
	}

	public String getMenu_lev_cd_nm() {
		return menu_lev_cd_nm;
	}

	public void setMenu_lev_cd_nm(String menu_lev_cd_nm) {
		this.menu_lev_cd_nm = menu_lev_cd_nm;
	}

	public String getUp_menu_id() {
		return up_menu_id;
	}

	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}

	public String getAct_yn() {
		return act_yn;
	}

	public void setAct_yn(String act_yn) {
		this.act_yn = act_yn;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

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

	public void setCrt_auth_yn(String crt_auth_yn) {
		this.create_auth_yn = crt_auth_yn;
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

	public String getUp_id() {
		return up_id;
	}

	public void setUp_id(String up_id) {
		this.up_id = up_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}

	public String getMi() {
		return mi;
	}

	public void setMi(String mi) {
		this.mi = mi;
	}
	

}
	
	