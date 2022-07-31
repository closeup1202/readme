package api.readmeshop.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    public String createJwtToken(Authentication authenticate) {
        User user = (User) authenticate.getPrincipal();
        String rolesString = user.getAuthorities().stream()   //ROLE_USER ~
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.joining());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(Map.of("roles", rolesString))
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

}
