package com.wx.mybatis.mapper;

import java.util.List;

import com.wx.mybatis.entity.Orders;
import com.wx.mybatis.entity.User;

public interface OrdersMapper {
	
	public List<Orders> queryAllOrder();
	
	public List<Orders> queryOrderAndUser();
	
	public List<User> queryUserAndOrder();
	
}
