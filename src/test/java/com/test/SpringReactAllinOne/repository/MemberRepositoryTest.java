package com.test.SpringReactAllinOne.repository;

import com.test.SpringReactAllinOne.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void afterEach(){
        memberRepository.deleteAll();
    }

    @Test
    public void 회원저장하기(){
        //given
        String memberId = "user";
        String memberPw = "user";

        memberRepository.save(Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .build());
        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getMemberId()).isEqualTo(memberId);
        assertThat(member.getMemberPw()).isEqualTo(memberPw);
    }

    @Test
    public void 회원찾기(){
        //given
        String memberId = "user1";
        String memberPw = "user1";

        memberRepository.save(Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .build());

        //when
        Member member = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw)
                .orElseThrow(() -> new IllegalStateException("해당 회원이 없습니다."));

        //then
        log.info("멤버의 아이디와 비밀번호 : " + member.getMemberId() + ", " + member.getMemberPw());

    }
}
