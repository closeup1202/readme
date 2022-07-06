package api.readmeshop.controller;

import api.readmeshop.request.member.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Hello print")
    void test_hello() throws Exception {
        //expected
        mockMvc.perform(get("/test"))
                .andExpect(content().string("Hello"))
                .andDo(print());
    }
    @Test
    @DisplayName("테스트")
    void test2() throws Exception {

        //given
        SignUpRequest request = SignUpRequest.builder()
                .useremail("a@naver.com")
                .username("건홍")
                .userpassword("pwd123@!e")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/signup").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }
}