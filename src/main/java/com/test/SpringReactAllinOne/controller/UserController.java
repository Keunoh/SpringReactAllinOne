package com.test.SpringReactAllinOne.controller;

import com.test.SpringReactAllinOne.domain.User;
import com.test.SpringReactAllinOne.dto.JwtAuthenticationResponse;
import com.test.SpringReactAllinOne.dto.LoginRequestDto;
import com.test.SpringReactAllinOne.dto.LoginResponseDto;
import com.test.SpringReactAllinOne.dto.SignUpRequestDto;
import com.test.SpringReactAllinOne.repository.UserRepository;
import com.test.SpringReactAllinOne.rvo.RVO;
import com.test.SpringReactAllinOne.security.JwtTokenProvider;
import com.test.SpringReactAllinOne.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );
            //authenticate메서드는
            log.info("what is in authentication ? : " + authentication);
        } catch (Exception e){
            log.error("exception from controller - {}", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());

        User user = new User(signUpRequestDto.getUsername(), password);

        userService.simpleCreateUser(user);

        return ResponseEntity.ok("create success!");

    }
}
