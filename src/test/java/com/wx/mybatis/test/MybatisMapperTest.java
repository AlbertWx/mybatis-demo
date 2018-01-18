package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

}
