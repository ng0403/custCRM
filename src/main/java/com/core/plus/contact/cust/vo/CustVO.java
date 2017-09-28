package com.core.plus.contact.cust.vo;

public class CustVO {
//	cust
	private String cust_no;
	private String create_date;
	private String update_date;
	private String cust_name;
	private String resident_no;
	private String chart_no;
	private String cust_id;
	private String emp_no;
	private String emp_name;
	private String user_no;
	private String user_nm;
	private String cust_type;
	private String cust_rank;
	private String visit_cd;
	private String visit_dtl_cd;
	private String visit_cn;
	private String rec_per;
	private String remark_cn;
	private String del_yn;
	
//	custphone
	private String phone_type_cd;
	private String phone_country_cd;
	private String phone_area_no;
	private String phone_no;
	private String primary_yn;
	
//	custaddr
	private String addr_type_cd;
	private String road_yn;
	private String zip_no;
	private String main_address;
	private String detail_address;
	
	
	//	selectKsy
	private String cust_key;
	
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
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
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getResident_no() {
		return resident_no;
	}
	public void setResident_no(String resident_no) {
		this.resident_no = resident_no;
	}
	public String getChart_no() {
		return chart_no;
	}
	public void setChart_no(String chart_no) {
		this.chart_no = chart_no;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getVisit_cd() {
		return visit_cd;
	}
	public void setVisit_cd(String visit_cd) {
		this.visit_cd = visit_cd;
	}
	public String getVisit_dtl_cd() {
		return visit_dtl_cd;
	}
	public void setVisit_dtl_cd(String visit_dtl_cd) {
		this.visit_dtl_cd = visit_dtl_cd;
	}
	public String getVisit_cn() {
		return visit_cn;
	}
	public void setVisit_cn(String visit_cn) {
		this.visit_cn = visit_cn;
	}
	public String getRec_per() {
		return rec_per;
	}
	public void setRec_per(String rec_per) {
		this.rec_per = rec_per;
	}
	public String getRemark_cn() {
		return remark_cn;
	}
	public void setRemark_cn(String remark_cn) {
		this.remark_cn = remark_cn;
	}
	public String getPhone_type_cd() {
		return phone_type_cd;
	}
	public void setPhone_type_cd(String phone_type_cd) {
		this.phone_type_cd = phone_type_cd;
	}
	public String getPhone_country_cd() {
		return phone_country_cd;
	}
	public void setPhone_country_cd(String phone_country_cd) {
		this.phone_country_cd = phone_country_cd;
	}
	public String getPhone_area_no() {
		return phone_area_no;
	}
	public void setPhone_area_no(String phone_area_no) {
		this.phone_area_no = phone_area_no;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getPrimary_yn() {
		return primary_yn;
	}
	public void setPrimary_yn(String primary_yn) {
		this.primary_yn = primary_yn;
	}
	public String getAddr_type_cd() {
		return addr_type_cd;
	}
	public void setAddr_type_cd(String addr_type_cd) {
		this.addr_type_cd = addr_type_cd;
	}
	public String getRoad_yn() {
		return road_yn;
	}
	public void setRoad_yn(String road_yn) {
		this.road_yn = road_yn;
	}
	public String getZip_no() {
		return zip_no;
	}
	public void setZip_no(String zip_no) {
		this.zip_no = zip_no;
	}
	public String getMain_address() {
		return main_address;
	}
	public void setMain_address(String main_address) {
		this.main_address = main_address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getCust_key() {
		return cust_key;
	}
	public void setCust_key(String cust_key) {
		this.cust_key = cust_key;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	public String getCust_rank() {
		return cust_rank;
	}
	public void setCust_rank(String cust_rank) {
		this.cust_rank = cust_rank;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	@Override
	public String toString() {
		return "CustVO [cust_no=" + cust_no + ", create_date=" + create_date + ", update_date=" + update_date
				+ ", cust_name=" + cust_name + ", resident_no=" + resident_no + ", chart_no=" + chart_no + ", cust_id="
				+ cust_id + ", emp_no=" + emp_no + ", emp_name=" + emp_name + ", user_no=" + user_no + ", user_nm="
				+ user_nm + ", cust_type=" + cust_type + ", cust_rank=" + cust_rank + ", visit_cd=" + visit_cd
				+ ", visit_dtl_cd=" + visit_dtl_cd + ", visit_cn=" + visit_cn + ", rec_per=" + rec_per + ", remark_cn="
				+ remark_cn + ", del_yn=" + del_yn + ", phone_type_cd=" + phone_type_cd + ", phone_country_cd="
				+ phone_country_cd + ", phone_area_no=" + phone_area_no + ", phone_no=" + phone_no + ", primary_yn="
				+ primary_yn + ", addr_type_cd=" + addr_type_cd + ", road_yn=" + road_yn + ", zip_no=" + zip_no
				+ ", main_address=" + main_address + ", detail_address=" + detail_address + ", cust_key=" + cust_key
				+ ", getCust_no()=" + getCust_no() + ", getCreate_date()=" + getCreate_date() + ", getUpdate_date()="
				+ getUpdate_date() + ", getCust_name()=" + getCust_name() + ", getResident_no()=" + getResident_no()
				+ ", getChart_no()=" + getChart_no() + ", getCust_id()=" + getCust_id() + ", getVisit_cd()="
				+ getVisit_cd() + ", getVisit_dtl_cd()=" + getVisit_dtl_cd() + ", getVisit_cn()=" + getVisit_cn()
				+ ", getRec_per()=" + getRec_per() + ", getRemark_cn()=" + getRemark_cn() + ", getPhone_type_cd()="
				+ getPhone_type_cd() + ", getPhone_country_cd()=" + getPhone_country_cd() + ", getPhone_area_no()="
				+ getPhone_area_no() + ", getPhone_no()=" + getPhone_no() + ", getPrimary_yn()=" + getPrimary_yn()
				+ ", getAddr_type_cd()=" + getAddr_type_cd() + ", getRoad_yn()=" + getRoad_yn() + ", getZip_no()="
				+ getZip_no() + ", getMain_address()=" + getMain_address() + ", getDetail_address()="
				+ getDetail_address() + ", getCust_key()=" + getCust_key() + ", getDel_yn()=" + getDel_yn()
				+ ", getCust_type()=" + getCust_type() + ", getCust_rank()=" + getCust_rank() + ", getEmp_no()="
				+ getEmp_no() + ", getEmp_name()=" + getEmp_name() + ", getUser_no()=" + getUser_no()
				+ ", getUser_nm()=" + getUser_nm() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}