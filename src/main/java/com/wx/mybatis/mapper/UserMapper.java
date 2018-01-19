package com.wx.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wx.mybatis.entity.QueryVo;
import com.wx.mybatis.entity.User;

public interface UserMapper {
	
	/**
	 * 使用mybatis的动态代理需要遵循以下四种原则
	 * 	1.声明的接口中的方法名需要与对应的XML文件中定义的sql的ID名称一致 
	 * 	2.方法的返回值类型需要与对应的XML文件中对应的sql定义的返回值类型一致
	 * 	3.方法的入参需要与对应的XML文件中对应的sql的定义的参数类型一致
	 * 	4.接口对应的XML文件中namespace命名空间绑定此接口
	 */
	
	/**
	 * Mapper接口开发需要遵循以下规范：
	 *	1、	Mapper.xml文件中的namespace与mapper接口的类路径相同。
	 *	2、	Mapper接口方法名和Mapper.xml中定义的每个statement的id相同 
	 *  3、	Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
	 *	4、	Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
	 * @param id
	 * @return
	 */
	public User selectById(int id);
	
	public Map<Object,Object> selectAsMapByObject(User user);
	
	public List<User> selectByQueryVo(QueryVo queryVo);
	
	public Integer sumCount();
	
	public List<User> queryByCondition(User user);

	public List<User> queryByConditionWhere(User user);
	
	public List<User> queryByArrys(String[] ids);

	public List<User> queryByList(List<String> list);
	
	public List<User> queryByConditionQueryVoList(QueryVo queryVo);
	
	public List<User> queryByConditionQueryVoArray(QueryVo queryVo);
	
}
