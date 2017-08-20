package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取连接或释放连接的工具类
 * 
 * @author HanYanBing
 *
 */
public class JDBCUtils {

	// 创建数据源
	private static DataSource dataSource = new ComboPooledDataSource();
	
	// 创建ThreadLocal对象 该对象是和线程绑定的连接
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	/**
	 * 获取连接的方法
	 * 
	 * @return
	 */
	/* public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	} */
	public static Connection getConnection() {
		// 从线程中获取连接
		Connection connection = threadLocal.get();
		if(connection == null) {
			// 从连接池中获取一个连接
			try {
				connection = dataSource.getConnection();
				// 将连接与当前线程绑定
				threadLocal.set(connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * 释放连接的方法
	 * 
	 * @param connection
	 */
	/* public static void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} */
	
	public static void releaseConnection() {
		// 获取当前线程中的连接
		Connection connection = threadLocal.get();
		if(connection != null) {
			try {
				connection.close();
				// 将已经关闭的连接从当前线程中移除
				threadLocal.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
