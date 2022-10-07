package com.test.SpringReactAllinOne.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long memberPrimaryKey;

    @Column(name = "member_id", length = 12, nullable = false)
    private String memberId;

    @Column(name = "member_pw", length = 12, nullable = false)
    private String memberPw;

    @Builder
    public Member(String memberId, String memberPw){
        this.memberId = memberId;
        this.memberPw = memberPw;
    }
}
