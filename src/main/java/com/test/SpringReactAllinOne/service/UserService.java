package com.test.SpringReactAllinOne.service;

import com.test.SpringReactAllinOne.domain.User;
import com.test.SpringReactAllinOne.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void simpleCreateUser(User user){
        /**
         * 수정 필요한 부분 isExistUser에는 Exception Message
         * 있을 것으로 사료됨 즉, if문은 계속 true일 수 밖에 없다.
         * 중복 체크에 대한 로직 재 설정 필요
         *
         * 추가로 이 아래 부분의 코드를 작성 후 서비스를 실행하면
         * 401 Unauthorized 에러가 발생한다. 이유를 찾아야 한다.
         */
//        User isExistUser = userRepository.findByUsername(user.getUsername())
//                        .orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));
//
//        log.error("what is in this user data : " + isExistUser.toString());

//        if(isExistUser == null){
//            userRepository.save(user);
//        }
        userRepository.save(user);
    }
}
