package api.readmeshop.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;

    //토큰의 인증 정보를 현재 실행 중인 Security Context 에 저장하는 역할
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().startsWith("signin")){
            String token = getToken(request);
            Map<String, Object> claims = jwtHelper.parseClaims(token);
            SecurityContextHolder.getContext().setAuthentication(createAuthentication(claims));
        }

        filterChain.doFilter(request, response);
    }

    // Authentication 객체를 만드는 것인데 claims 를 받음
    private Authentication createAuthentication(Map<String, Object> claims) {
        //클레임에서 권한 정보(roles) 들을 빼내오기
        List<SimpleGrantedAuthority> roles =
                Arrays.stream(claims.get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // claims.get(Claims.SUBJECT) = 유저 객체 | 토큰 | 권한 정보들 = roles 를 이용해서 authentication 객체 반환
        return new UsernamePasswordAuthenticationToken(claims.get(Claims.SUBJECT), null, roles);
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        return Optional.ofNullable(header)
                .filter(auth -> auth.startsWith("Bearer "))
                .map(auth -> auth.replace("Bearer", ""))
                .orElseThrow( () -> new BadCredentialsException("Invalid Token"));
    }
}
