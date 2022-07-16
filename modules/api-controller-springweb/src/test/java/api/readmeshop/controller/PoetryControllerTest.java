package api.readmeshop.controller;

import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.request.literature.poetry.PostPoetryRequest;
import api.readmeshop.service.literature.PostLiteratureRequired;
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
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(){
        log.info("포이트리 컨트롤러 시작");
    }

    @BeforeEach
    void beforeEach(){
        literatureRepository.deleteAll();
    }

    @Test
    @DisplayName("시를 작성하면 DB에 시가 저장된다")
    void test() throws Exception {
        //given
        PostLiteratureRequired poetry = PostPoetryRequest.builder()
                .email("a@naver.com")
                .shape(new Shape(PoetryShape.FREE))
                .title("first")
                .contents("뜯겨져나가기")
                .build();

        String json = objectMapper.writeValueAsString(poetry);

        //when
        mockMvc.perform(post("/poetry").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        Assertions.assertThat(1L).isEqualTo(literatureRepository.count());
    }


    @AfterAll
    static void afterAll(){
        log.info("포이트리 컨트롤러 끝");

    }
}
