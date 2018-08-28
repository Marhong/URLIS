/**
 * 
 */
package com.hwadee.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 项目名称: TestMaven
 * 类名称: JDBCUtil
 * 创建人: wangbin
 * 创建时间: 2018年8月10日 下午6:32:42
 */
/**
 * 项目名称: TestMaven
 * 类名称: JDBCUtil
 * 创建人: wangbin
 * 创建时间: 2018年8月10日 下午6:44:30
 */
/**
 * 项目名称: TestMaven 类名称: JDBCUtil 创建人: wangbin 创建时间: 2018年8月10日 下午6:44:32
 */
public class JDBCUtil {

	private static String driver = null;
	private static String username = null;
	private static String password = null;
	private static String url = null;

	static {

		Properties properties = new Properties();

		InputStream in = JDBCUtil.class.getResourceAsStream("/mysql.properties");

		try {
			properties.load(in);

			driver = properties.getProperty("driver");
			username = properties.getProperty("userName");
			password = properties.getProperty("password");
			url = properties.getProperty("url");

			// 加载数据库的驱动
			Class.forName(driver);

		} catch (Exception e) {
			System.out.println("加载数据库的驱动失败.....");
		}
	}

	// 获取数据库的连接
	public static Connection getConnection() {
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		} catch (SQLException e) {
			System.out.println("获取数据库连接失败....");
			return null;
		}
	}

	/*
	 * insert update delete
	 */
	public static boolean insertExecute(String sql, ArrayList<Object> paras) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pst = null;
		// 获取连接
		conn = getConnection();

		// 参数的索引值
		int index = 1;
		// 创建预编译对象
		try {
			pst = conn.prepareStatement(sql);

			// 设置参数
			if (paras != null && paras.size() > 0) {
				// 清空以前的参数
				pst.clearParameters();

				// 添加参数
				for (int i = 0; i < paras.size(); i++) {
					pst.setObject(index++, paras.get(i));
					
				}
			}
			
			// 执行
			int n = pst.executeUpdate(); // 返回所影响的行数
			
			flag = n > 0 ? true : false;
			
		} catch (Exception e) {
			
			flag = false;
			e.printStackTrace();
		} finally {
			// 关闭数据库
			closeDB(conn, pst);
		}
		
		return flag;
	}

	/*
	 * 查询
	 */
	public static <T> List<T> QueryExcuteDataBackList(String sql, ArrayList<Object> paras, Class<T> clazz) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		
		//返回的数据集
		List<T> list= new ArrayList<T>();
		
		// 获取连接
		conn = getConnection();

		// 参数的索引值
		int index = 1;

		T resultObject = null;
		
		try {
			pst = conn.prepareStatement(sql);
			// 设置参数
			if (paras != null && paras.size() > 0) {
				// 清空以前的参数
				pst.clearParameters();

				// 添加参数
				for (int i = 0; i < paras.size(); i++) {
					pst.setObject(index++, paras.get(i));
				}
			}
			
			
			//执行
			rs = pst.executeQuery();
			
			//rs我们只能通过循环来获取每个对象属性的值
			
			//获取rs的源数据
			ResultSetMetaData metaData = rs.getMetaData();
			
			//获取列数
			int length = metaData.getColumnCount();
			
			//循环
			while(rs.next())
			{
				//通过反射机制创建对象
				resultObject=clazz.newInstance();
				
				//循环列数
				for(int i=0;i<length;i++)
				{
					//获取列名
					String columnName = metaData.getColumnName(i+1);
					
					//获取对应列名的值
					Object objectValue = rs.getObject(columnName);
					
					//把对应的值赋给属性
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					
					field.set(resultObject, objectValue);
				}
				
				list.add(resultObject);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	/*
	 * 关闭数据库
	 */
	public static void closeDB(Connection conn, PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
