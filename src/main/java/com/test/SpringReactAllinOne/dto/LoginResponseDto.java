package com.test.SpringReactAllinOne.dto;

import com.test.SpringReactAllinOne.domain.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String userId;
    private String userPw;

    public LoginResponseDto(User userData){
        this.userId = userData.getUserId();
        this.userPw = userData.getUserPw();
    }
}
