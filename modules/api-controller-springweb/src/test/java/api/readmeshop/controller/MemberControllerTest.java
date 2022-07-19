package api.readmeshop.controller;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.request.user.member.SignUpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MemberRepository memberRepository;

    static SignUpRequest signupRequestObject(String email, String name, String password) {
        return SignUpRequest.builder()
                            .email(email)
                            .username(name)
                            .password(password)
                            .build();
    }

    static String signupRequestObjectToJson(String email, String name, String password) throws JsonProcessingException {
        SignUpRequest request = signupRequestObject(email, name, password);
        return objectMapper.writeValueAsString(request);
    }

    /**************************************************************************/

    @BeforeAll
    static void beforeAll(){
        log.info("웹 컨트롤러 테스트 시작");
    }

    @BeforeEach
    void data_clean(){
        memberRepository.deleteAll();
    }

    /**************************************************************************/

    @Test
    @DisplayName("TEST로 접속했을 때, Hello를 출력한다")
    void test_hello() throws Exception {
        //expected
        mockMvc.perform(get("/test"))
                .andExpect(content().string("Hello"))
                .andDo(print());
    }
    @Test
    @DisplayName("회원가입에 성공하는 테스트 : 반환 값은 없다(void)")
    void test2() throws Exception {

        //given
        String json = signupRequestObjectToJson("a@naver.com", "건홍", "pwd123@!e");

        //expected
        mockMvc.perform(post("/signup").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }
    @Test
    @DisplayName("회원가입에 실패하는 테스트 : 빈 값 입력 시 오류를 발생")
    void test3() throws Exception {
        //given
        String json = signupRequestObjectToJson("a@naver.com", "건홍", " ");

        //expected
        mockMvc.perform(post("/signup").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입에 실패하는 테스트 2 : 이름에 특수문자를 포함할 수 없다")
    void test() throws Exception {
        //given
        String json = signupRequestObjectToJson("a@naver.com", "김바보#$%#", "askdkspda");

        //expected
        mockMvc.perform(post("/signup").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입에 성공한다. DB에 값을 저장한다 ")
    void test4() throws Exception {
        //given
        String json = signupRequestObjectToJson("a@naver.com", "건홍", "dsdadsa@@");

        //expected
        mockMvc.perform(post("/signup").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertThat(1L).isEqualTo(memberRepository.count());
    }

    @Test
    @DisplayName("회원 탈퇴하는 테스트")
    void test5() throws Exception {
        //given
        Member member = Member.builder()
                .email("a@naver.com")
                .type(null)
                .password("aasdsa@@")
                .build();

        memberRepository.save(member);

        //expected
        mockMvc.perform(delete("/resign/{memberId}", member.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        assertThat(0L).isEqualTo(memberRepository.count());
    }

    /**************************************************************************/


    @AfterAll
    static void AfterAll(){
        log.info("웹 컨트롤러 테스트 끝");
    }
}
