package com.lee.service;

import com.lee.dto.LoginResponseDTO;
import com.lee.dto.LoginRequestDTO;
import com.lee.dto.RegisterRequestDTO;

public interface UserService {

    long register(RegisterRequestDTO registerDTO);

    LoginResponseDTO login(LoginRequestDTO loginDTO);

}
