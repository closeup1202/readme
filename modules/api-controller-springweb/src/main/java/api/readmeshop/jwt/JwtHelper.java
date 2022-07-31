package api.readmeshop.jwt;

import api.readmeshop.request.user.member.SignInRequest;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtHelper {

    private final Key key;

    public JwtHelper(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String sub, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(sub)
                .addClaims(claims)
                .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                .signWith(key)
                .compact();
    }

    //토큰으로 클레임을 만들기 :  이를 이용해서 유저 객체를 만들어서 최종적으로 Authentication 객체를 리턴하기 위함
    public Map<String, Object> parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createJwtToken(Authentication authenticate) {
        User user = (User) authenticate.getPrincipal();
        String rolesString = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return createToken(user.getUsername(), Map.of("roles", rolesString));
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            log.info("error : {}", e.getClaims() + "만료된 JWT 토큰입니다");
        } catch (UnsupportedJwtException e){
            log.info("error : {}", e.getMessage() + " : 지원되지 않는 JWT 토큰입니다" );
        } catch (IllegalArgumentException e) {
            log.info("error : {}", e.getMessage() + " : JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
