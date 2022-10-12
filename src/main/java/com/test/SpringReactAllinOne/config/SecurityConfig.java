package com.test.SpringReactAllinOne.config;

import com.test.SpringReactAllinOne.security.CustomUserDetailsService;
import com.test.SpringReactAllinOne.security.JwtAuthenticationEntryPoint;
import com.test.SpringReactAllinOne.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //보안 resource에 인증되지 않은 접근 발생시 401 에러 return
    //spring security의 AuthenticationEntryPoint interface를 implements
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 이 부분이 만약 Component로 주입되었을 때 오류가 발생하는지 알아보기로 한다.
     * @return
     */
    @Bean
    public JwtAuthenticationFilter JwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/v1/user/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        //Add out custom JWT security filter
        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
