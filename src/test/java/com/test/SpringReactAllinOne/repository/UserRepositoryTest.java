package com.test.SpringReactAllinOne.repository;

import com.test.SpringReactAllinOne.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Slf4j
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void afterEach(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원저장하기(){
        //given
        String userId = "user";
        String userPw = "user";

        userRepository.save(User.builder()
                .username(userId)
                .password(userPw)
                .build());
        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo(userId);
        assertThat(user.getPassword()).isEqualTo(userPw);
    }

    @Test
    public void 회원찾기(){
        //given
        String userId = "user1";
        String userPw = "user1";

        userRepository.save(User.builder()
                .username(userId)
                .password(userPw)
                .build());

        //when
        User user = userRepository.findByUsernameOrEmail(userId, userId)
                .orElseThrow(() -> new IllegalStateException("해당 회원이 없습니다."));

        //then
        log.info("멤버의 아이디와 비밀번호 : " + user.getUsername() + ", " + user.getPassword());

    }
}
