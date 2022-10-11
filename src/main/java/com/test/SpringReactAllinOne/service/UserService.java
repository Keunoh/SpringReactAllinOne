package com.test.SpringReactAllinOne.service;

import com.test.SpringReactAllinOne.domain.Member;
import com.test.SpringReactAllinOne.dto.LoginRequestDto;
import com.test.SpringReactAllinOne.dto.LoginResponseDto;
import com.test.SpringReactAllinOne.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public LoginResponseDto getMemberIdAndPassword(LoginRequestDto loginRequestDto){

        String memberId = loginRequestDto.getMemberId();
        String memberPw = loginRequestDto.getMemberPw();

        Member memberData = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return new LoginResponseDto(memberData);
    }
}
