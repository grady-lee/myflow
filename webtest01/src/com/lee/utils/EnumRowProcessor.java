package com.lee.utils;

import org.apache.commons.dbutils.BasicRowProcessor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumRowProcessor extends BasicRowProcessor {

    @Override
    public <T> T toBean(ResultSet rs, Class<? extends T> type) throws SQLException {
        T bean = super.toBean(rs, type);
        EnumUtils.handleEnum(bean); // 我们自己处理 Enum
        return bean;
    }
}

