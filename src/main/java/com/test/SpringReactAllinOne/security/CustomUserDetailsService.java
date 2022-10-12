package com.test.SpringReactAllinOne.security;

import com.test.SpringReactAllinOne.domain.User;
import com.test.SpringReactAllinOne.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //UserDetailsService에는 loadUserById 메서드가 있는데 이는
    //userId 이것과 혼동될 수도 있다.
    //즉, 회원이 어떻게 하여 로그인을 할 수 있게 하는가?
    //클린코드의 문제? 혹은 정책의 문제일 수도 있다.
    //목적은 어떻게하여 코드의 혼동을 피할 것 인가

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with usernameOrEmail : " + usernameOrEmail));
        return CustomUserDetails.createCustomUserDetails(user);
    }

    public UserDetails loadUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User is not found :  " + id)
        );

        return CustomUserDetails.createCustomUserDetails(user);
    }

}
