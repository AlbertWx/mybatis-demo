package com.wx.mybatis.mapper;

import java.util.List;

import com.wx.mybatis.entity.Orders;

public interface OrdersMapper {
	
	public List<Orders> queryAllOrder();
	
}
