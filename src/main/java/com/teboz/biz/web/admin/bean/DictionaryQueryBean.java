package com.teboz.biz.web.admin.bean;

import com.teboz.biz.page.QueryBean;

public class DictionaryQueryBean extends QueryBean{
	
	private String groupId;
	
	private String groupName;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}