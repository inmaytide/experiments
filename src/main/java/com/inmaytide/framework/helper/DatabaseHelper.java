/**
 * 
 */
package com.inmaytide.framework.helper;

import java.io.Serializable;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.framework.utils.CollectionUtil;

/**
 * 
 * @author Administrator
 */
public final class DatabaseHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner queryRunner;
	private static final ThreadLocal<Connection> connectionHolder;
	private static final BasicDataSource dataSource;
	
	static {
		queryRunner = new QueryRunner();
		connectionHolder = new ThreadLocal<Connection>();
		
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(ConfigHelper.getJdbcDriver());
		dataSource.setUrl(ConfigHelper.getJdbcUrl());
		dataSource.setUsername(ConfigHelper.getJdbcUsername());
		dataSource.setPassword(ConfigHelper.getJdbcPassword());
	}
	
	public static Connection getConnection() {
		Connection con = connectionHolder.get();
		if (null == con) {
			try {
				con = dataSource.getConnection();
			} catch (SQLException e) {
				logger.error("Get connection failure!!!", e);
				throw new RuntimeException(e);
			} finally {
				connectionHolder.set(con);
			}
		}
		return con;
	}
	
	
	/**
	 * 开始事务
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				logger.error("begin transacation failure", e);
				throw new RuntimeException(e);
			} finally {
				connectionHolder.set(conn);
			}
		}
	}
	
	public static void commitTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				logger.error("commit transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				connectionHolder.remove();
			}
		}
	}
	
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				logger.error("rollback transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				connectionHolder.remove();
			}
		}
	}
	
	
	public static <T> List<T> queryEntityList(Class<T> cls, String sql, Object... params) {
		List<T> list = null;
		Connection con = getConnection();
		try {
			list = queryRunner.query(con, sql, new BeanListHandler<T>(cls), params);
		} catch (SQLException e) {
			logger.error("Query entity list failure", e);
			throw new RuntimeException(e);
		}
		return list;
	}
	
	public static <T> T queryEntity(Class<T> cls, String sql, Object... params) {
		T t = null;
		Connection conn = getConnection();
		try {
			t = queryRunner.query(conn, sql, new BeanHandler<T>(cls), params);
		} catch (SQLException e) {
			logger.error("Query entity failure", e);
			throw new RuntimeException(e);
		}
		return t;
	}
	
	public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
		List<Map<String, Object>> result = null;
		Connection conn = getConnection();
		try {
			result = queryRunner.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			logger.error("Execute query failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		Connection conn = getConnection();
		try {
			rows = queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			logger.error("Execute update failure", e);
			throw new RuntimeException(e);
		}
		return rows;
	}
	
	public static <T> boolean insertEntity(Map<String, Object> fieldMap, Class<T> cls) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			logger.error("Can not insert entity: fieldMap is empty");
			return false;
		}
		StringBuilder sql = new StringBuilder("insert into ");
		sql.append(getTableName(cls));
		StringBuilder columns = new StringBuilder(" (");
		StringBuilder values = new StringBuilder(" (");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(","), columns.length(), ") ");
		values.replace(values.lastIndexOf(","), values.length(), ") ");
		sql.append(columns).append("values").append(values);
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql.toString(), params) == 1;
	}
	
	public static <T> boolean updateEntity(Map<String, Object> fieldMap, Class<T> cls, Serializable id) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			logger.error("Can not update entity: fieldMap is empty");
			return false;
		}
		StringBuilder sql = new StringBuilder("update ");
		sql.append(getTableName(cls)).append(" set ");
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql.append(columns.substring(0, columns.lastIndexOf(", "))).append(" where id = ?");
		
		List<Object> parameters = new ArrayList<Object>();
		parameters.addAll(fieldMap.values());
		parameters.add(id);
		Object[] params = parameters.toArray();
		return executeUpdate(sql.toString(), params) == 1;
	}
	
	public static <T> boolean deleteEntity(Serializable id, Class<T> cls) {
		String sql = "delete from " + getTableName(cls) + " where id = ?";
		return executeUpdate(sql, id) == 1;
	}

	private static String getTableName(Class<?> cls) {
		return cls.getSimpleName();
	}

	public static void executeSqlFile(String filePath) {
		try {
			URI uri = Thread.currentThread().getContextClassLoader().getResource(filePath).toURI();
			Files.lines(Paths.get(uri)).forEach((sql) -> {
				executeUpdate(sql);
			});
		} catch (Exception e) {
			logger.error("Execute sqlfile failure", e);
			throw new RuntimeException(e);
		} 
		
	}
}
