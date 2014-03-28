package com.dataConnectionPool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataConnectionPool {
	private ComboPooledDataSource connectionPool;
	private static DataConnectionPool dataConnectionPool;

	private static Logger log = Logger.getLogger(DataConnectionPool.class);

	private static ResourceBundle bundle = ResourceBundle
			.getBundle("com.bundle.ResourceBundle");

	private DataConnectionPool() throws SQLException, PropertyVetoException {
		log.info("Reading datasource.properties from classpath");
		bundle = ResourceBundle.getBundle("com.bundle.ResourceBundle");
		connectionPool = new ComboPooledDataSource();
		connectionPool.setDriverClass(bundle.getString("driver"));
		connectionPool.setJdbcUrl(bundle.getString("jdbcUrl"));
		connectionPool.setUser(bundle.getString("username"));
		connectionPool.setPassword(bundle.getString("password"));

		connectionPool.setInitialPoolSize(new Integer((String) bundle
				.getString("initialPoolSize")));
		connectionPool.setAcquireIncrement(new Integer((String) bundle
				.getString("acquireIncrement")));
		connectionPool.setMaxPoolSize(new Integer((String) bundle
				.getString("maxPoolSize")));
		connectionPool.setMinPoolSize(new Integer((String) bundle
				.getString("minPoolSize")));

	}

	public static DataConnectionPool getInstanceDataConnectionPool()
			throws SQLException, PropertyVetoException {
		if (dataConnectionPool == null) {
			return dataConnectionPool = new DataConnectionPool();
		} else {
			return dataConnectionPool;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.connectionPool.getConnection();
	}

}
