package api.readmeshop.service.member;

import api.readmeshop.domain.user.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
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
        assertThat("a@naver.com").isEqualTo(required.getUseremail());
        assertThat("pwd").isEqualTo(required.getUserpassword());
    }

    /********************************************************************************************/

    @AfterAll
    static void afterAll(){
        log.info("웹 서비스 테스트 끝");

    }
}
