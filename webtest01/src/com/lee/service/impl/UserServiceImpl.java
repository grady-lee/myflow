package com.lee.service.impl;

import com.lee.dao.UserDao;
import com.lee.dao.impl.UserDaoImpl;
import com.lee.dto.LoginResponseDTO;
import com.lee.dto.LoginRequestDTO;
import com.lee.dto.RegisterRequestDTO;
import com.lee.entity.User;
import com.lee.service.UserService;
import com.lee.utils.MD5Util;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public long register(RegisterRequestDTO registerDTO) {
        String username = registerDTO.getUsername();
        // 判断用户是否存在
        if(username == null || username.isBlank()){
            throw new RuntimeException("用户名为空.");
        }
        User user = userDao.selectByUsername(username);
        // 判断用户是否存在
        if(user != null){
            throw new RuntimeException("用户已经存在.");
        }
        String rawPassword = registerDTO.getRawPassword();
        String email = registerDTO.getEmail();
        Integer gender = registerDTO.getGender();
        int genderCode = gender == null ? 0 : gender;
        if(genderCode != 0 && genderCode != 1){
            genderCode = 0;
        }

        if(rawPassword == null || rawPassword.isBlank()){
            throw new RuntimeException("密码为空.");
        }
        User registerUser = new User(username,
                MD5Util.encode(rawPassword),
                email,
                genderCode);
        try {
            int insert = userDao.insert(registerUser);
            if(insert <= 0){
                throw new RuntimeException("内部问题，注册失败.");
            }
            user = userDao.selectByUsername(username);
            return user.getId();
        } catch (Exception e) {
            throw new RuntimeException("内部问题，注册失败.");
        }
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        String username = loginDTO.getUsername();
        String rawPassword = loginDTO.getRawPassword();
        if(username == null || username.isBlank()){
            throw new RuntimeException("用户名为空.");
        }
        if(rawPassword == null || rawPassword.isBlank()){
            throw new RuntimeException("密码为空.");
        }
        User user = userDao.selectByUsername(username);
        if(user == null){
            throw new RuntimeException("用户名或者密码错误.");
        }
        String passwordHash = user.getPasswordHash();
        if(!passwordHash.equals(MD5Util.encode(rawPassword))){
            throw new RuntimeException("用户名或者密码错误.");
        }
        return new LoginResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getGender(),
                user.getEmail()
        );
    }

}
