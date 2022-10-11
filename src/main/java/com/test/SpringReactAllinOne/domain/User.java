package com.test.SpringReactAllinOne.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long userPrimaryKey;

    @Column(name = "user_id", length = 12, nullable = false)
    private String userId;

    @Column(name = "user_pw", length = 12, nullable = false)
    private String userPw;

    private Set<Role> roles = new HashSet<>();

    @Builder
    public User(String userId, String userPw){
        this.userId = userId;
        this.userPw = userPw;
    }
}
