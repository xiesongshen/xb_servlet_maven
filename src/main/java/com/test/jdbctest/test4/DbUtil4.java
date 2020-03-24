package com.test.jdbctest.test4;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @auth wangzy
 * @date 2020/2/19 18:29
 * @Description
 */
public class DbUtil4 {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static DataSource dataSource;

    static {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setCheckoutTimeout(30000);
            cpds.setIdleConnectionTestPeriod(30);
            cpds.setInitialPoolSize(3);
            cpds.setMaxIdleTime(30);
            cpds.setMaxPoolSize(70);
            cpds.setMaxStatementsPerConnection(100);
            cpds.setMinPoolSize(3);
            cpds.setMaxStatements(75);
            cpds.setAcquireIncrement(3);
            cpds.setDriverClass(DRIVER);
            cpds.setJdbcUrl(URL);
            cpds.setUser(USER);
            cpds.setPassword(PASSWORD);

            dataSource = cpds;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            // 每一次从dataSource中获取一个新的连接
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
