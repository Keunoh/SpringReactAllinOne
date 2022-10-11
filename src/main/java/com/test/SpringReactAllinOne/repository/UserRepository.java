package com.test.SpringReactAllinOne.repository;

import com.test.SpringReactAllinOne.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberIdAndMemberPw(String memberId, String memberPw);
}
