package com.test.SpringReactAllinOne.service;

import com.test.SpringReactAllinOne.domain.User;
import com.test.SpringReactAllinOne.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void simpleCreateUser(User user){
        userRepository.save(user);
    }
}
