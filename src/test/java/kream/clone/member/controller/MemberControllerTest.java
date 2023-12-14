package kream.clone.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kream.clone.member.dto.request.MemberLoginRequest;
import kream.clone.member.dto.request.MemberSignUpRequest;
import kream.clone.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static kream.clone.member.fixture.MemberFixture.PASSWORD;
import static kream.clone.member.fixture.MemberFixture.USERNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
    @Mock
    private MemberService memberService;
    @InjectMocks
    private MemberController memberController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }
    @Test
    public void 정상_회원가입() throws Exception{
        //given
        MemberSignUpRequest request = new MemberSignUpRequest(USERNAME, PASSWORD);
        //when
        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("회원가입성공"));
    }

    @Test
    public void 정상_로그인() throws Exception{
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("로그인성공"));
    }
}
