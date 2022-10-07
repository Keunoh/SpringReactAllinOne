package com.test.SpringReactAllinOne.dto;

import com.test.SpringReactAllinOne.domain.Member;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String memberId;
    private String memberPw;

    public LoginResponseDto(Member memberData){
        this.memberId = memberData.getMemberId();
        this.memberPw = memberData.getMemberPw();
    }
}
