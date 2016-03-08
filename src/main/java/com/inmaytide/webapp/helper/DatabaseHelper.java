/**
 * 
 */
package com.inmaytide.webapp.helper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.webapp.utils.CollectionUtil;
import com.inmaytide.webapp.utils.PropsUtil;

/**
 * @author Administrator
 *
 */
public final class DatabaseHelper {
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner queryRunner = new QueryRunner();
	private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	
	static {
		Properties props = PropsUtil.loadProps("config.properties");
		DRIVER = props.getProperty("jdbc.driver");
		URL = props.getProperty("jdbc.url");
		USERNAME = props.getProperty("jdbc.username");
		PASSWORD = props.getProperty("jdbc.password");
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("Can not load jdbc driver.", e);
		}
	}
	
	public static Connection getConnection() {
		Connection con = connectionHolder.get();
		if (null == con) {
			try {
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				logger.error("Get connection failure!!!", e);
				throw new RuntimeException(e);
			} finally {
				connectionHolder.set(con);
			}
		}
		return con;
	}
	
	public static void closeConnection() {
		Connection conn = connectionHolder.get();
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Close connection failure!!!", e);
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
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
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
}
