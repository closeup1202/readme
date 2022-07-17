package api.readmeshop.service.literature.poetry;

import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.domain.contents.literature.poetry.Poetry;
import api.readmeshop.domain.contents.literature.poetry.PoetryRepository;
import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.domain.user.member.MemberType;
import api.readmeshop.service.literature.PostLiteratureRequired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PoetryServiceTest {

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    private PoetryRepository poetryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PoetryService poetryService;

    @BeforeAll
    static void beforeAll(){
      log.info("포이트리 서비스 시작");
    }

    @BeforeEach
    void beforeEach(){
        literatureRepository.deleteAll();
    }

    /*******************************************************************/

    @Test
    @DisplayName("시 작성 테스트")
    @Transactional
    void test(){
        //given
        PostLiteratureRequired poetry = new PostLiteratureRequired("a@naver.com","first", "뜯겨져나가기", PoetryShape.FREE);

         Member member = Member.builder()
                 .email("a@naver.com")
                 .password("123")
                 .type(MemberType.WRITER)
                 .build();

        //when
        memberRepository.save(member);
        poetryService.write(poetry);
        Poetry savedPoetry = poetryRepository.findAll().get(0);

        //then
        assertThat(1L).isEqualTo(poetryRepository.count());
        assertThat(PoetryShape.FREE).isEqualTo(savedPoetry.getShape());
        assertThat("a@naver.com").isEqualTo(savedPoetry.getMember().getEmail());
        assertThat("first").isEqualTo(savedPoetry.getTitle());
        assertThat("뜯겨져나가기").isEqualTo(savedPoetry.getContents());
    }













    /*******************************************************************/

    @AfterAll
    static void afterAll(){
      log.info("포이트리 서비스 끝");
    }

}