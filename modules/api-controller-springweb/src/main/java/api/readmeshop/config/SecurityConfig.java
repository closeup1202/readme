package api.readmeshop.config;

import api.readmeshop.jwt.JwtFilter;
import api.readmeshop.jwt.JwtExceptionHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtExceptionHandling jwtExceptionHandling;

    @Autowired
    private UserDetailsService memberDetailsService;

    private static final String[] PERMIT_PATTERNS = {"/signup", "/account", "/signin", "/logout", "/h2-console/**", "/favicon.ico"};

    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return authentication -> {
            String principal = authentication.getPrincipal() + "";
            UserDetails user = memberDetailsService.loadUserByUsername(principal);
            return new UsernamePasswordAuthenticationToken(principal, null, user.getAuthorities());
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않음
            .and()
            .exceptionHandling()
                .accessDeniedHandler(jwtExceptionHandling::handle)
                .authenticationEntryPoint(jwtExceptionHandling::commence)
            .and()
            .authorizeHttpRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근 제한을 설정하겠다는 의미
            .antMatchers(PERMIT_PATTERNS).permitAll() // api 에 대한 요청은 인증없이 접근을 허용하겠다는 의미
            .anyRequest().authenticated() // 나머지 요청들은 인증을 받아야 한다는 의미
            .and()
            .addFilterAt(jwtFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
