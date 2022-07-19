package api.readmeshop.service.literature.poetry;

import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.domain.contents.literature.poetry.Poetry;
import api.readmeshop.domain.contents.literature.poetry.PoetryRepository;
import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.domain.user.member.MemberType;
import api.readmeshop.service.literature.LiteratureResponse;
import api.readmeshop.service.literature.PostLiteratureRequired;
import api.readmeshop.service.policies.posting.PostedPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private static final SecureRandom random = new SecureRandom();

    @BeforeAll
    static void beforeAll(){
      log.info("포이트리 서비스 시작");
    }

    @BeforeEach
    void beforeEach(){
        literatureRepository.deleteAll();
        memberRepository.deleteAll();
    }

    /*************************** static methods ************************/

    static Member memberBuild(String email, String password, MemberType type){
        return Member.builder()
                .type(type)
                .password(password)
                .email(email)
                .build();
    }

    static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /*******************************************************************/

    @Test
    @DisplayName("시 작성 테스트")
    @Transactional
    void test(){
        //given
        PostLiteratureRequired poetry = new PostLiteratureRequired("a@naver.com","first", "뜯겨져나가기", PoetryShape.FREE);

        Member member = memberBuild("a@naver.com", "123", MemberType.WRITER);

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

    @Test
    @DisplayName("시 1페이지 목록 조회")
    void test2(){
        //given
        Member member = memberBuild("a@naver.com", "123", MemberType.WRITER);
        memberRepository.save(member);

        List<Poetry> collect = IntStream.rangeClosed(0, 20)
                .mapToObj(count -> Poetry.builder()
                        .member(member)
                        .shape(randomEnum(PoetryShape.class))
                        .contents(count + "번째 내용")
                        .title("시" + count).build())
                .collect(Collectors.toList());

        poetryRepository.saveAll(collect);

        PostedPolicy policy = new PostedPolicy(1, 10);

        //when
        List<LiteratureResponse> list = poetryService.getList(policy);

        //then
        assertThat(10L).isEqualTo(list.size());
        assertThat("시20").isEqualTo(list.get(0).getTitle());
        assertThat("11번째 내용").isEqualTo(list.get(9).getContents());
    }













    /*******************************************************************/

    @AfterAll
    static void afterAll(){
      log.info("포이트리 서비스 끝");
    }

}