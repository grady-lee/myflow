package com.lee.dao;

import com.lee.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BaseDao {

    private final QueryRunner queryRunner = new QueryRunner();
//    private static final BasicRowProcessor ROW_PROCESSOR =
//            new EnumRowProcessor();

    public int update(String sql, Object... params) {
        try {
            return queryRunner.update(JDBCTools.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getList(String sql, Class<T> clazz, Object... params) {
        try {
            return queryRunner.query(JDBCTools.getConnection(),
                    sql,
                    new BeanListHandler<>(clazz),
                    params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getBean(String sql, Class<T> clazz, Object... params) {
        try {
            return queryRunner.query(JDBCTools.getConnection(),
                    sql,
                    new BeanHandler<>(clazz),
                    params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getValue(String sql, Object... params) {
        try {
            return queryRunner.query(JDBCTools.getConnection(),
                    sql,
                    new ScalarHandler<>(),
                    params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
