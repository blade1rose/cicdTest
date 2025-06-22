package com.tefuir.controller;

import com.tefuir.pojo.dto.UserDTO;
import com.tefuir.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    //登录校验
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        boolean isValid = authService.validateCredentials(userDTO.getAccount(), userDTO.getPassword());
        if(isValid){
            return ResponseEntity
                    .ok() //1. 创建一个状态码为200 OK的ResponseEntity构建器
                    .body(Collections.singletonMap("status", "success"));//2.设置响应体内容 创建一个单键值对的不可变Map
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", "Invalid credentials"));
    }
}
