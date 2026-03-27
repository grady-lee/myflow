package com.lee.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTools {

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try (InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("druid.properties")) {
            if (in == null) {
                throw new RuntimeException("加载druid文件失败，请正确放置资源文件.");
            }
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final ThreadLocal<Connection> tl = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection conn = tl.get();
        try {
            // 这里判断是否关闭是为了避免在threadLocal中的连接已经被关闭
            if (conn == null || conn.isClosed()) {
                conn = dataSource.getConnection();
                tl.set(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException("获取连接失败", e);
        }
        return conn;
    }

    public static void closeConnection() {
        Connection conn = tl.get();
        if (conn != null) {
            try {
                // 先移除再处理，避免别的地方又 get 到旧引用
                tl.remove();
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭连接失败", e);
            }
        }
    }

    public static void begin(){
        Connection conn = getConnection();
        try {
            if(!conn.getAutoCommit()){
                throw new RuntimeException("请勿重复开启事务!");
            }
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("开启事务失败", e);
        }
    }

    public static void commit(){
        Connection conn = getConnection();
        try {
            if(conn.getAutoCommit()){
                throw new RuntimeException("事务还未开启");
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("提交事务失败", e);
        }finally {
            closeConnection();
        }
    }

    public static void rollback(){
        Connection conn = getConnection();
        try {
            if(conn.getAutoCommit()){
                throw new RuntimeException("事务还未开启");
            }
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("回滚事务失败", e);
        }finally {
            closeConnection();
        }
    }

}
