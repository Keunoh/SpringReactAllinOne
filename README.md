# SpringReactAllinOne
React and Spring is all in spring container

1. Spring Security에 관한 내용과 각각의 역할
	관련 URL : https://mangkyu.tistory.com/76

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

-----------------------------------------------------------

* HTTP RESPONSE ERROR CODE
1. 400 Bad Request
   : the server cannot or will not procees the request due to
   something that is perceived to be a client error syntax,
   invalid request message framing, or deceptive request routing.

   percieve : come to realize or understand :: 자각하다
   deceptive : making you believe something that is not true
   관련 URL : https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
   (mdn web docs는 웹에대한 docu가 있으니 잘 살펴 볼것)

2. 401 Unauthorized
   : the client request has not been completed because it lacks
   valid authentication credentials for the requested resource.

   lack : the state of being without or not having enough of something
   :: 부족하다

3. 403 Forbidden
   : the server understands the request but refuses to authorize it.
   This status is similar to 401, but for the 403 Forbidden status code
   reauthenticating makes no difference.
   The access is permanently forbidden and tied to the application logic,
   such as insufficient rights to a resource.

   make no difference : have no effect on a person or situation
   :: 차이가 없다
   insufficient : not enought :: 불충분한

4. 404 Not Found
   : the server cannot find the requested resource.
   A 404 status code only indicates that the resource is missing: not whether
   the absence is temporary or permanent.
   If a resource is permanently removed, use the 410 (Gone) status instead.

   absence : the state of being away from a place or person :: 부재

5. 405 Method Not Allowed
   : the server knows the request method, but the target resource
   doesn't support this method.
   The server must generate an [Allow] header field in a 405 status code
   response.
   The field must contain a list of methods that the target resource
   currently supports.

6. 406 Not Acceptable
   : the server cannot produce a response matching the list of
   acceptable values defined in the request's proactive content
   negotiation headers, and that the server is unwilling to supply
   a default respresentation.

   proactive : creating or controlling a situation by causing something
   to happen rather than responding to it after it has happened.
   :: 사전 예방적인