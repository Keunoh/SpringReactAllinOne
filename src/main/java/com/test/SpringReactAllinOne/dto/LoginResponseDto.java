package com.test.SpringReactAllinOne.dto;

import com.test.SpringReactAllinOne.domain.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String username;
    private String password;

    public LoginResponseDto(User userData){
        this.username = userData.getUsername();
        this.password = userData.getPassword();
    }
}
