package com.test.SpringReactAllinOne.repository;

import com.test.SpringReactAllinOne.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Slf4j
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void afterEach(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원저장하기(){
        //given
        String userId = "user2";
        String userPw = "user2";
        String encodedUserPw = passwordEncoder.encode(userPw);

        log.info("인코딩 된 패스워드 : " + encodedUserPw);

        userRepository.save(User.builder()
                .username(userId)
                .password(encodedUserPw)
                .build());
        //when
        User user = userRepository.findByUsername(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 회원이 없습니다."));

        //then
        assertThat(user.getUsername()).isEqualTo(userId);
        assertThat(user.getPassword()).isEqualTo(encodedUserPw);
    }

    @Test
    public void 회원찾기(){
        //given
        String userId = "user3";
        String userPw = "user3";
        String encodedUserPw = passwordEncoder.encode(userPw);

        userRepository.save(User.builder()
                .username(userId)
                .password(encodedUserPw)
                .build());

        //when
        User user = userRepository.findByUsername(userId)
                .orElseThrow(() -> new IllegalStateException("해당 회원이 없습니다."));

        //then
        log.info("멤버의 아이디와 비밀번호 : " + user.getUsername() + ", " + user.getPassword());
        assertThat(user.getUsername()).isEqualTo(userId);
        assertThat(user.getPassword()).isEqualTo(encodedUserPw);

    }
}
