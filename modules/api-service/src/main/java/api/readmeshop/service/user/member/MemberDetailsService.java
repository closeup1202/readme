package api.readmeshop.service.user.member;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {  //UserDetailsService 의 구현체

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) { //email 을 받아서 UserDetails 객체 (User) 를 리턴
        return memberRepository.findByEmail(email)
                .map(member -> createUser(email, member))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    private User createUser(String email, Member member){
        if(!member.isActivated()){
            throw new RuntimeException("활성화되어 있지 않습니다");
        }

        //활성화가 트루면 member 의 권한 정보들 authorities (Set)을 돌려서 문자열 리스트로 만듦. (ROLE_ADMIN, ROLE_USER) 이걸 가지고 User 객체를 만듦.
        List<SimpleGrantedAuthority> grantedAuthorities =
                member.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                        .collect(Collectors.toList());

        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
