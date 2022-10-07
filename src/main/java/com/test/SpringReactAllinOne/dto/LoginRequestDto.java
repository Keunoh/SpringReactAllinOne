package com.test.SpringReactAllinOne.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String memberId;
    private String memberPw;

    public LoginRequestDto(){}
}
