package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wx.mybatis.entity.Orders;
import com.wx.mybatis.entity.User;
import com.wx.mybatis.mapper.OrdersMapper;
import com.wx.mybatis.mapper.UserMapper;

public class OrdersMapperTest {

	private SqlSession session;

	private OrdersMapper ordersMapper;

	/**
	 * 运行测试方法前创建SqlSession
	 */
	@Before
	public void getSqlSession() {
		try {
			// 1.加载核心配置文件
			String resource = "SqlMapConfig.xml";
			InputStream stream;
			stream = Resources.getResourceAsStream(resource);
			// 2.创建SqlSessionFactory
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);
			// 2.获取SqlSession
			SqlSession session = sessionFactory.openSession();
			this.session = session;
			this.ordersMapper = session.getMapper(OrdersMapper.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 运行测试方法结束后关闭SqlSession
	 */
	@After
	public void closeSqlSession() {
		this.session.close();
	}
	
	@Test
	public void queryAllOrder() {
		List<Orders> list = ordersMapper.queryAllOrder();
		for (Orders orders : list) {
			System.out.println(orders.toString());
		}
	}
	
	@Test
	public void queryOrderAndUser() {
		List<Orders> list = ordersMapper.queryOrderAndUser();
		for (Orders orders : list) {
			System.out.println("------" + orders.toString() + "------" + orders.getUser().toString());
		}
	}
	
	@Test
	public void queryUserAndOrder() {
		List<User> list = ordersMapper.queryUserAndOrder();
		for (User user : list) {
			System.out.println("----"+user.toString()+user.getListOrders());
			for (Orders order : user.getListOrders()) {
				System.out.println(order.toString());
			}
			System.out.println("------------------------");
		}
	}
}
