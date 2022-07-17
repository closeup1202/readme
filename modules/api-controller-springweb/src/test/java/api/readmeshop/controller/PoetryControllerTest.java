package api.readmeshop.controller;

import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.domain.user.member.MemberType;
import api.readmeshop.request.literature.poetry.PostLiteratureRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PoetryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static PostLiteratureRequest postPoetryRequestObject(String poetryType, String title, String contents, String email){
        return PostLiteratureRequest.builder()
                .type(poetryType)
                .email(email)
                .title(title)
                .contents(contents)
                .build();
    }

    static String postPoetryRequestObjectToJson(String poetryType, String title, String contents, String email) throws JsonProcessingException {
        PostLiteratureRequest request = postPoetryRequestObject(poetryType, title, contents, email);
        return objectMapper.writeValueAsString(request);
    }

    /*******************************************************************************/

    @BeforeAll
    static void beforeAll(){
        log.info("포이트리 컨트롤러 시작");
    }

    @BeforeEach
    void beforeEach(){
        literatureRepository.deleteAll();
    }

    /*******************************************************************************/

    @Test
    @DisplayName("시를 작성하면 DB에 시가 저장된다")
    void test() throws Exception {
        //given
        String json = postPoetryRequestObjectToJson("free", "first", "contents", "a@naver.com");
        Member member = Member.builder()
                .email("a@naver.com")
                .password("123")
                .type(MemberType.WRITER)
                .build();

        //when
        memberRepository.save(member);
        //when
        mockMvc.perform(post("/poetry").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        Assertions.assertThat(1L).isEqualTo(literatureRepository.count());
    }

    /*******************************************************************************/

    @AfterAll
    static void afterAll(){
        log.info("포이트리 컨트롤러 끝");

    }
}
