package com.lee.dao;

import com.lee.entity.User;

import java.time.LocalDateTime;

/**
 * 后续需要考虑并发注册撞车，审计(更新，锁定，状态等)
 */
public interface UserDao {

    int insert(User user);

    // 登录建议使用自定义的小的user表,减少对用户表整表的查询
    /**
     *
     * @param username
     * @return
     * id
     * password_hash
     * status
     * deleted
     * fail_count
     * lock_until
     * User selectForLoginByUsername(String username);
     *
     */
    User selectByUsername(String username);

    int updateLastLogin(long userId, LocalDateTime time, String ip);

    int updateLoginFail(long userId, int failCount, LocalDateTime lockUntil);

}
