package com.test.SpringReactAllinOne.dto;

import com.test.SpringReactAllinOne.domain.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    /**
     * Getter가 없으면 서버에서 리스폰스 할 때
     * 멤버 변수 값들을 읽어올 수 없다.
     * 406 Not Acceptable HTTP ERROR 발생한다.
     */
    private final String SUCCESS_MESSAGE = "Hello! ";

    private String responseMessage;

    public SignUpResponseDto(User user){
        this.responseMessage = SUCCESS_MESSAGE + user.getUsername();
    }
}
