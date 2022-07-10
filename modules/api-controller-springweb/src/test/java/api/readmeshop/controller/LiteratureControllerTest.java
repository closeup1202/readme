package api.readmeshop.controller;

import api.readmeshop.domain.contents.literature.LiteratureRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Slf4j
class LiteratureControllerTest {

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void beforeAll(){
        log.info("Poetry Controller 테스트 시작");
    }

    /**************************************************************************************/

    @BeforeEach
    void beforeEach(){
        literatureRepository.deleteAll();
    }

    @Test
    @DisplayName("")
    void test1(){
        //given


        //when


        //then
    }


    /**************************************************************************************/

    @AfterAll
    static void afterAll(){
        log.info("Poetry Controller 테스트 끝");
    }
}