package com.teboz.biz.web.admin.bean;

import com.teboz.biz.page.QueryBean;

public class PartnerQueryBean extends QueryBean{
	private String nickName;
	private String email;
	private String mobile;
	private String jobName;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
