package com.core.plus.lead.vo;

public class InterestItemVO {
	
	private String lead_no;
	private String main_cate_cd;
	private String mid_cate_cd;
	private String small_cate_cd;
	private String create_date; 	// 등록일시
	private String update_date; 	// 수정일시
	
	private int qty; 				// 수량
	private int list_price; 		// 단가
	private int total_price;		//총 금액
	
	
	private String main_cate_name;
	private String mid_cate_name;
	private String small_cate_name;
	
	
	
	public String getMain_cate_name() {
		return main_cate_name;
	}
	public void setMain_cate_name(String main_cate_name) {
		this.main_cate_name = main_cate_name;
	}
	public String getMid_cate_name() {
		return mid_cate_name;
	}
	public void setMid_cate_name(String mid_cate_name) {
		this.mid_cate_name = mid_cate_name;
	}
	public String getSmall_cate_name() {
		return small_cate_name;
	}
	public void setSmall_cate_name(String small_cate_name) {
		this.small_cate_name = small_cate_name;
	}
	public String getLead_no() {
		return lead_no;
	}
	public void setLead_no(String lead_no) {
		this.lead_no = lead_no;
	}
	public String getMain_cate_cd() {
		return main_cate_cd;
	}
	public void setMain_cate_cd(String main_cate_cd) {
		this.main_cate_cd = main_cate_cd;
	}
	public String getMid_cate_cd() {
		return mid_cate_cd;
	}
	public void setMid_cate_cd(String mid_cate_cd) {
		this.mid_cate_cd = mid_cate_cd;
	}
	public String getSmall_cate_cd() {
		return small_cate_cd;
	}
	public void setSmall_cate_cd(String small_cate_cd) {
		this.small_cate_cd = small_cate_cd;
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getList_price() {
		return list_price;
	}
	public void setList_price(int list_price) {
		this.list_price = list_price;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "InterestItemVO [lead_no=" + lead_no + ", main_cate_cd=" + main_cate_cd + ", mid_cate_cd=" + mid_cate_cd
				+ ", small_cate_cd=" + small_cate_cd + ", create_date=" + create_date + ", update_date=" + update_date
				+ ", qty=" + qty + ", list_price=" + list_price + ", total_price=" + total_price + ", main_cate_name="
				+ main_cate_name + ", mid_cate_name=" + mid_cate_name + ", small_cate_name=" + small_cate_name + "]";
	}

}
