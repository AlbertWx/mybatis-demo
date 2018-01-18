package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wx.mybatis.entity.User;

public class MybatisTest {

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
	
	/**
	 * 根据ID查询单个
	 */
	@Test
	public void selectById(){
		User user = session.selectOne("User.selectById", 1);
		System.out.println(user.toString());
	}
	
	/**
	 * 查询所有
	 */
	@Test
	public void selectAll() {
		List<User> list = session.selectList("User.selectAll");
		System.out.println("----------------"+list.size());
		for (User user : list) {
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 查询结构为map
	 */
	@Test
	public void selectListMap() {
		List<HashMap> list = session.selectList("User.selectMap");
		for (HashMap hashMap : list) {
			System.out.println("==========="+hashMap.get("username")+"==========="+hashMap.get("address"));
		}
	}
	
//	@Test
//	public void selectMap() {
//		Map<Object, Object> map = session.selectMap("User.selectMap", "username");
//		Map<Object, Object> result = (Map<Object, Object>) map.get("username");
//		System.out.println("==========="+result.get("username")+"==========="+result.get("address"));
//	}
	/**
	 * 以map为查询参数
	 */
	@Test
	public void selectByMap() {
		Map<Object,Object> map = new HashMap<>();
		map.put("username", "Andy");
		map.put("address", "hongkong");
		User user = session.selectOne("User.selectByMap", map);
		System.out.println(user.toString());

	}
	
	@Test
	public void selectByList() {
		List<String> list = new ArrayList<>();
		list.add("36");
		list.add("41");
		List<Map<Object,Object>> lists = session.selectList("User.selectByList", list);
		for (Map<Object, Object> map : lists) {
			System.out.println(map.get("username")+"----"+map.get("sex")
			+"----"+map.get("address")+"----"+map.get("birthday"));
		}
	}
	
	/**
	 * 模糊查询
	 */
	@Test
	public void selectLike() {
		List<User> list = session.selectList("User.selectLike" ,"王");
//		List<User> list = session.selectList("User.selectLike" ,"王");
		System.out.println("----------------"+list.size());
		for (User user : list) {
			System.out.println(user.toString());
		}
	}
	
	/**
	 * 以map为参数模糊查询
	 */
	@Test
	public void selectLikeByMap() {
		Map<Object,Object> mapc = new HashMap<Object,Object>();
		mapc.put("username", "A");
		List<Map<Object,Object>> list = session.selectList("User.selectLikeByMap", mapc);
		for (Map<Object, Object> map : list) {
			System.out.println(map.get("username")+"----"+map.get("sex")
			+"----"+map.get("address")+"----"+map.get("birthday"));
		}
	}
	
	@Test
	public void selectAsMapByObject() {
		User user = new User();
		user.setUsername("Ludwig");
		user.setAddress("ER");
		List<Map<Object,Object>> list = session.selectList("User.selectAsMapByObject", user);
		for (Map<Object, Object> map : list) {
			System.out.println(map.get("username")+"----"+map.get("sex")
			+"----"+map.get("address")+"----"+map.get("birthday"));
		}
	}
	

	@Test
	public void selectAsMapByMap() {
		Map<Object,Object> mapc = new HashMap<Object,Object>();
		mapc.put("username","Ludwig");
		mapc.put("address","ER");
		List<Map<Object,Object>> list = session.selectList("User.selectAsMapByMap", mapc);
		for (Map<Object, Object> map : list) {
			System.out.println(map.get("username")+"----"+map.get("sex")
			+"----"+map.get("address")+"----"+map.get("birthday"));
		}
	}
	
	@Test
	public void selectByArrayAsObject() {
		String[] arrs = {"36" , "41"};
		List<User> list = session.selectList("User.selectByArrayAsObject", arrs);
		for (User user : list) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void selectByArrayAsMap() {
		String[] arrs = {"36" , "41"};
		List<Map<Object, Object>> list = session.selectList("User.selectByArrayAsMap", arrs);
		for (Map<Object, Object> map : list) {
			System.out.println(map.get("username")+"----"+map.get("sex")
			+"----"+map.get("address")+"----"+map.get("birthday"));
		}
	}
	
	@Test
	public void insertUser() {
		User user = new User();
		user.setUsername("tt");
		user.setAddress("china");
		session.insert("User.insertUser", user);
		//需手动提交事物
		session.commit();
	}
	
	@Test
	public void insertByHashMap() {
		Map<Object,Object> map = new HashMap<>();
		map.put("username", "Ludwig");
		map.put("sex", "男");
		map.put("address", "ER");
		map.put("birthday", new Date());
		session.insert("User.insertByHashMap", map);
		session.commit();
	}
	
	/**
	 * 已Map为传输传入时，需要迭代，但是顺序需要确定
	 */
	@Test
	public void insertByMap() {
		Map<Object,Object> map = new HashMap<>();
		map.put("username", "Andy");
		map.put("sex", "1");
		map.put("address", "hongkong");
		map.put("birthday", new Date());
		Map<String,Map<Object,Object>> params = new HashMap<>();
		params.put("params",map);
		session.insert("User.insertByMap", params);
		session.commit();
	}
	
}
