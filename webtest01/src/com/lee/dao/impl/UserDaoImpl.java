package com.lee.dao.impl;

import com.lee.dao.BaseDao;
import com.lee.dao.UserDao;
import com.lee.entity.User;

import java.time.LocalDateTime;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int insert(User user) {
        String sql = "insert into user(username, passwordhash, gender, email) values(?,?,?,?)";
        return update(sql,
                user.getUsername(),
                user.getPasswordHash(),
                user.getGender(),
                user.getEmail());
    }


    public static void main(String[] args) {
        UserDao  userDao = new UserDaoImpl();
        User user = userDao.selectByUsername("sdvjfdsadas");
        System.out.println(user);
    }

    @Override
    public User selectByUsername(String username) {
        String sql = "select id, username, passwordhash, gender, email from user where username = ?";
        return getBean(sql,User.class,username);
    }

    @Override
    public int updateLastLogin(long userId, LocalDateTime time, String ip) {
        return 0;
    }

    @Override
    public int updateLoginFail(long userId, int failCount, LocalDateTime lockUntil) {
        return 0;
    }
}
