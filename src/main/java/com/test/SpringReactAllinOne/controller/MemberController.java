package com.test.SpringReactAllinOne.controller;

import com.test.SpringReactAllinOne.dto.LoginRequestDto;
import com.test.SpringReactAllinOne.dto.LoginResponseDto;
import com.test.SpringReactAllinOne.rvo.RVO;
import com.test.SpringReactAllinOne.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public RVO<LoginResponseDto> memberLogin(@RequestBody LoginRequestDto loginRequestDto){
        return RVO.<LoginResponseDto>builder()
                .message("로그인 되었습니다.")
                .checkCode("체크 코드 생성")
                .data(memberService.getMemberIdAndPassword(loginRequestDto))
                .build();
    }
}
