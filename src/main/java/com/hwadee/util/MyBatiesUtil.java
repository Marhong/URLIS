package com.hwadee.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * 工具类：
 *   1、加载 mybatis的配置文件
 *   
 *   2、sqlSession
 *   
 *   3、关闭会话
 * 
 */
public class MyBatiesUtil {

	private static SqlSessionFactory sqlSessionFactory = null;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	/*
	 * 加载 mybatis的配置文件
	 */
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public MyBatiesUtil() {
	};

	/*
	 * 获取sqlSession
	 */
	public static SqlSession getSqlSession() {
		// 从线程池中获取sqlsession对象
		SqlSession sqlSession = threadLocal.get();

		// 如果sqlsession不存在
		if (sqlSession == null) {
			sqlSession = sqlSessionFactory.openSession(true); // 在sqlSessionFactory获取一个sqlSession
			threadLocal.set(sqlSession);
		}
		// 返回当前sqlSession
		return sqlSession;
	}

	/*
	 * 关闭
	 */
	public static void closeSqlSession() {
		// 从线程池中获取sqlsession对象
		SqlSession sqlSession = threadLocal.get();
		
		if(sqlSession!=null)
		{
			sqlSession.close();
			threadLocal.remove();
		}
	}

}
