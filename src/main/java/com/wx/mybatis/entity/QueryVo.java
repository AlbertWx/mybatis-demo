package com.wx.mybatis.entity;

import java.io.Serializable;
import java.util.List;

public class QueryVo implements Serializable {
	
	private User user;
	
	private List<String> idList;
	
	private String[] ids;

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
