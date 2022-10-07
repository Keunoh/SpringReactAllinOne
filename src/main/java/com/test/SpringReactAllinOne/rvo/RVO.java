package com.test.SpringReactAllinOne.rvo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RVO<T> {
    //@Getter를 사용하지 않으면 에러를 만난다.
    //DefaultHandlerExceptionResolver에러
    //핸들러가 클라이언트가 요청한 타입으로 응답을 내려줄 수 없음.

    private String message;
    private String checkCode;
    private T data;

    @Builder
    public RVO(String message, String checkCode, T data){
        this.message = message;
        this.checkCode = checkCode;
        this.data = data;
    }

}
