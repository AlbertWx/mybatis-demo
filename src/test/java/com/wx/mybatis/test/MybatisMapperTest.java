package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wx.mybatis.entity.QueryVo;
import com.wx.mybatis.entity.User;
import com.wx.mybatis.mapper.UserMapper;

/**
 * 该类为mybatis的接口动态代理测试类
 * 
 * @author Administrator
 *
 */
public class MybatisMapperTest {

	private SqlSession session;
	
	private UserMapper userMapper;
	
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
			this.userMapper = session.getMapper(UserMapper.class); 
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
	public void selectById() {
		// 根据给定的接口生成对应的实现类
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectById(36);
		System.out.println(user.toString());
	}

	@Test
	public void selectAsMapByObject() {
		User user = new User();
		user.setUsername("Ludwig");
		user.setAddress("ER");
		// 根据给定的接口生成对应的实现类
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<Object, Object> map = mapper.selectAsMapByObject(user);
		System.out.println(map.get("username")+"==========="+map.get("address"));
	}
	
	@Test
	public void selectByQueryVo() {
		User user = new User();
		user.setUsername("L");
		QueryVo queryVo = new QueryVo();
		queryVo.setUser(user);
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> list = mapper.selectByQueryVo(queryVo);
		for (User user2 : list) {
			System.out.println(user2.toString());
		}
	}
	
	@Test
	public void sumCount() {
		Integer i = userMapper.sumCount();
		System.out.println("---------i = "+i);
	}
	
	@Test
	public void queryByCondition() {
		User user = new User();
		user.setSex("1");
		user.setUsername("a");
		List<User> list = userMapper.queryByCondition(user);
		for (User user2 : list) {
			System.out.println(user2.toString());
		}
	}
	
	@Test
	public void queryByConditionWhere() {
		User user = new User();
		user.setSex("1");
		user.setUsername("a");
		List<User> list = userMapper.queryByConditionWhere(user);
		for (User user2 : list) {
			System.out.println(user2.toString());
		}
	}
	
	@Test
	public void queryByConditionQueryVoList() {
		List<String> list = new ArrayList<>();
		list.add("16");
		list.add("36");
		list.add("42");
		QueryVo queryVo = new QueryVo();
		queryVo.setIdList(list);
		List<User> users = userMapper.queryByConditionQueryVoList(queryVo);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void queryByConditionQueryVoArray() {
		String[] ids = {"16","36","42"};
		QueryVo queryVo = new QueryVo();
		queryVo.setIds(ids);
		List<User> users = userMapper.queryByConditionQueryVoArray(queryVo);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void queryByArrys() {
		String[] ids = {"16","36","42"};
		List<User> users = userMapper.queryByArrys(ids);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void queryByList() {
		List<String> list = new ArrayList<>();
		list.add("16");
		list.add("36");
		list.add("42");
		List<User> users = userMapper.queryByList(list);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	
}
