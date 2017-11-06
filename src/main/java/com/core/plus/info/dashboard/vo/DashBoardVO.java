package com.core.plus.info.dashboard.vo;

import java.util.List;

public class DashBoardVO {
	
	private int cust_count;			//고객 수
	private int latent_cust_count;	//잠재고객 수
	private int induce_cust_count;	//고객유도 수
	
	private List<String> date; 			//날짜
	private List<String> cust_change;	//고객 증감
	
	private List<String> cust_increase; //고객 증가수
	
	private String new_cust;				//신규 고객수
	private String new_latent_cust;		//신규 잠재고객수
	private String leave_cust;				//해지수
	private String new_lead;				//신규 고객리드발굴 수
	private String lead_success;			//성공한 고객리드 수
	private String lead_fail;				//실패한 고객리드 수
	private String task_count;				//상담 수
	private String new_oppty;				//신규 영업기회 수
	private String oppty_success;			//성공한 영업기회 수
	private String oppty_fail;				//실패한 영업기회 수

	private String new_cust_avg;			//신규 고객수 평균
	private String new_latent_cust_avg;	//신규 잠재고객수 평균
	private String leave_cust_avg;			//해지수 평균
	private String new_lead_avg;			//신규 고객리드발굴 수 평균
	private String lead_success_avg;		//성공한 고객리드 수 평균
	private String lead_fail_avg;			//실패한 고객리드 수 평균
	private String task_count_avg;			//상담 수 평균
	private String new_oppty_avg;			//신규 영업기회 수 평균
	private String oppty_success_avg;		//성공한 영업기회 수 평균
	private String oppty_fail_avg;			//실패한 영업기회 수 평균
	
	public String getNew_cust() {
		return new_cust;
	}
	public void setNew_cust(String new_cust) {
		this.new_cust = new_cust;
	}
	public String getNew_latent_cust() {
		return new_latent_cust;
	}
	public void setNew_latent_cust(String new_latent_cust) {
		this.new_latent_cust = new_latent_cust;
	}
	public String getLeave_cust() {
		return leave_cust;
	}
	public void setLeave_cust(String leave_cust) {
		this.leave_cust = leave_cust;
	}
	public String getNew_lead() {
		return new_lead;
	}
	public void setNew_lead(String new_lead) {
		this.new_lead = new_lead;
	}
	public String getLead_success() {
		return lead_success;
	}
	public void setLead_success(String lead_success) {
		this.lead_success = lead_success;
	}
	public String getLead_fail() {
		return lead_fail;
	}
	public void setLead_fail(String lead_fail) {
		this.lead_fail = lead_fail;
	}
	public String getTask_count() {
		return task_count;
	}
	public void setTask_count(String task_count) {
		this.task_count = task_count;
	}
	public String getNew_oppty() {
		return new_oppty;
	}
	public void setNew_oppty(String new_oppty) {
		this.new_oppty = new_oppty;
	}
	public String getOppty_success() {
		return oppty_success;
	}
	public void setOppty_success(String oppty_success) {
		this.oppty_success = oppty_success;
	}
	public String getOppty_fail() {
		return oppty_fail;
	}
	public void setOppty_fail(String oppty_fail) {
		this.oppty_fail = oppty_fail;
	}
	public String getNew_cust_avg() {
		return new_cust_avg;
	}
	public void setNew_cust_avg(String new_cust_avg) {
		this.new_cust_avg = new_cust_avg;
	}
	public String getNew_latent_cust_avg() {
		return new_latent_cust_avg;
	}
	public void setNew_latent_cust_avg(String new_latent_cust_avg) {
		this.new_latent_cust_avg = new_latent_cust_avg;
	}
	public String getLeave_cust_avg() {
		return leave_cust_avg;
	}
	public void setLeave_cust_avg(String leave_cust_avg) {
		this.leave_cust_avg = leave_cust_avg;
	}
	public String getNew_lead_avg() {
		return new_lead_avg;
	}
	public void setNew_lead_avg(String new_lead_avg) {
		this.new_lead_avg = new_lead_avg;
	}
	public String getLead_success_avg() {
		return lead_success_avg;
	}
	public void setLead_success_avg(String lead_success_avg) {
		this.lead_success_avg = lead_success_avg;
	}
	public String getLead_fail_avg() {
		return lead_fail_avg;
	}
	public void setLead_fail_avg(String lead_fail_avg) {
		this.lead_fail_avg = lead_fail_avg;
	}
	public String getTask_count_avg() {
		return task_count_avg;
	}
	public void setTask_count_avg(String task_count_avg) {
		this.task_count_avg = task_count_avg;
	}
	public String getNew_oppty_avg() {
		return new_oppty_avg;
	}
	public void setNew_oppty_avg(String new_oppty_avg) {
		this.new_oppty_avg = new_oppty_avg;
	}
	public String getOppty_success_avg() {
		return oppty_success_avg;
	}
	public void setOppty_success_avg(String oppty_success_avg) {
		this.oppty_success_avg = oppty_success_avg;
	}
	public String getOppty_fail_avg() {
		return oppty_fail_avg;
	}
	public void setOppty_fail_avg(String oppty_fail_avg) {
		this.oppty_fail_avg = oppty_fail_avg;
	}
	public List<String> getCust_increase() {
		return cust_increase;
	}
	public void setCust_increase(List<String> cust_increase) {
		this.cust_increase = cust_increase;
	}
	public int getCust_count() {
		return cust_count;
	}
	public void setCust_count(int cust_count) {
		this.cust_count = cust_count;
	}
	public int getLatent_cust_count() {
		return latent_cust_count;
	}
	public void setLatent_cust_count(int latent_cust_count) {
		this.latent_cust_count = latent_cust_count;
	}
	public int getInduce_cust_count() {
		return induce_cust_count;
	}
	public void setInduce_cust_count(int induce_cust_count) {
		this.induce_cust_count = induce_cust_count;
	}
	public List<String> getDate() {
		return date;
	}
	public void setDate(List<String> date) {
		this.date = date;
	}
	public List<String> getCust_change() {
		return cust_change;
	}
	public void setCust_change(List<String> cust_change) {
		this.cust_change = cust_change;
	}
}
