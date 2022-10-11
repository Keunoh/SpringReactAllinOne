package com.test.SpringReactAllinOne.service;

import com.test.SpringReactAllinOne.domain.User;
import com.test.SpringReactAllinOne.dto.LoginRequestDto;
import com.test.SpringReactAllinOne.dto.LoginResponseDto;
import com.test.SpringReactAllinOne.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public LoginResponseDto getUserIdAndPassword(LoginRequestDto loginRequestDto){

        String userId = loginRequestDto.getUserId();
        String userPw = loginRequestDto.getUserPw();

        User userData = userRepository.findByUserIdAndUserPw(userId, userPw)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return new LoginResponseDto(userData);
    }
}
