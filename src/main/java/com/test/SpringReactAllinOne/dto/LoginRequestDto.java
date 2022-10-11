package com.test.SpringReactAllinOne.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String userId;
    private String userPw;

    public LoginRequestDto(){}
}
