package com.example.springsecurity.config;




import com.example.springsecurity.security.CustomSecurityFilter;
import com.example.springsecurity.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor// 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @secured 어노테이션 활성화 인가된 사용자만 접근 가능함.
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    private final AuthenticationEntryPoint customAuthenticationEntryPoint;

    private final AccessDeniedHandler customAccessDeniedHandler;

    @Bean//비밀번호 암호화 기능 등록
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/api/user/**").permitAll()
              /* .antMatchers("/h2-console").permitAll()*/
                /*.antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()*/
                .anyRequest().authenticated();   //인증처리를 하겠다!

                //추가로 .antMatchers API지정한 방식으로 get이나ㅏ 그런 url지정 + .접근사용자지정 가능



        // 로그인 사용  //시큐리티에서적용
        // 스프링시큐리티는 기본적으로 세션 방식을 이용 ..세션값을 통해 인증이 처리됨 ! 즉 F12눌러서 세션이 인증정보가 사라지기 전까지 유효함
        /*http.formLogin();*/  //기본로그인
        http.formLogin().loginPage("/api/user/login-page").permitAll();
        //커스텀한 로그인페이지 ~ 받아주는 컨트롤러단도 필요!

        http.addFilterBefore(new CustomSecurityFilter(userDetailsService,passwordEncoder()), UsernamePasswordAuthenticationFilter.class);
        // 왼쪽파라미터 수행후..오른쪽 파라미터수행함..
        //뭘 커스텀 할꺼냐면... 토큰검사하는걸 .? ..인증검사

        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint); //401에러... 인증과정실패시...

      /*  http.exceptionHandling().accessDeniedPage("/api/user/forbidden");//403뜰대 ..권한에대한 에러페이지*/

        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);//403에러처리... 인증과는별개로... 추가적인 권한이 충족되지 않은경우

        return http.build();
    }

}
// 45분..!`