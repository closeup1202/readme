package api.readmeshop.service.member;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.service.user.member.MemberService;
import api.readmeshop.service.user.member.SignUpRequired;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeAll
    static void beforeAll(){
        log.info("웹 서비스 테스트 시작");
    }
    
    @BeforeEach
    void beforeEach(){
        memberRepository.deleteAll();
    }

    /********************************************************************************************/

    @Test
    @DisplayName("회원가입 테스트")
    void test(){
        //given
        SignUpRequired required = new SignUpRequired("a@naver.com", "pwd");

        //when
        memberService.signUp(required);

        //then
        assertThat(1L).isEqualTo(memberRepository.count());
        assertThat("a@naver.com").isEqualTo(required.getEmail());
        assertThat("pwd").isEqualTo(required.getPassword());
    }

    @Test
    @DisplayName("회원 탈퇴 테스트")
    void test2(){
        //given
        Member member = Member.builder()
                .email("a@naver.com")
                .type(null)
                .password("aaaaa@@")
                .build();

        //when
        memberRepository.save(member);
        memberService.resign(member.getId());

        //then
        assertThat(0L).isEqualTo(memberRepository.count());
    }

    /********************************************************************************************/

    @AfterAll
    static void afterAll(){
        log.info("웹 서비스 테스트 끝");

    }
}
