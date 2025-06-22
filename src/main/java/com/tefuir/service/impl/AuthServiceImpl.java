package com.tefuir.service.impl;

import com.tefuir.mapper.UserMapper;
import com.tefuir.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserMapper userMapper;
    public boolean validateCredentials(String account, String inputPassword){
        String storedPassword = userMapper.selectPasswordByAccount(account);
        if(storedPassword == null){
            return false;
        }
        return storedPassword.equals(inputPassword);
    }

}
