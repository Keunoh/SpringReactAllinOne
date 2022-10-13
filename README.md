# SpringReactAllinOne
React and Spring is all in spring container

2. 순수 signin은 컨트롤러에서 (permitAll이므로 필터없음)

유저의 Principal(아이디), 유저의 Credential(비밀번호)를 가지고 토큰을 생성한다.

그러면 Manager는 해당 토큰을 가지고 메서드를 호출하게 된다.

Manager는 Config에서 커스터마이징한 UserDetailsService객체를 주입할 수 있는데
이때 주입한 객체의 loadUserByUsername 메서드를 사용한다.

Override된 loadUserByUsername 메서드는 UserDetails객체를 반환해야 하는데
이것으로 나의 DB에 접근하여 해당 사용자가 맞는 로직을 구현할 수 있다.

UserDetails객체를 상속받는 커스텀UserDetails객체를 생성하고 반환하면
이를 근거로 해당 유저가 존재하는지 AuthenticationManager가 판단한다.

만약 해당회원이 존재하면 객체가 Principal, Credential, Role의 정보를 출력하며
아닐시 null을 반환하게 된다.

-----------------------------------------------------------
실패하면 여기서 로직이 종료되고 Exception발생한다.
-----------------------------------------------------------

만약 해당 회원이 존재하여 객체를 반환하면 
SecurityContextHolder.getContext().setAuthentication(나의 authentication);
메서드를 실행하여 authentication을 등록해 준다.

그리고 해당 authentication을 이용해 tokenProvider를 이용해서
토큰을 생성해준다. 

(커스터마이징도 가능해 보인다)
나의 authentication에서 principal을 추출해서 JWT를 생성해주고 반환한다. 

그리고 사용자에게 토큰을 발급해준다.

* 봐야할 클래스들[SpringReactAllinOne Project] 
SecurityConfig, UserController, 
AuthenticationManager(스프링 자체), 
UsernamePasswordAuthenticationToken(스프링 자체)
CustomUserDetails, CustomUserDetails, TokenPorvider
