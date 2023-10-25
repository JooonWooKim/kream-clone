package kream.clone.member.service;

import jakarta.servlet.http.HttpServletResponse;
import kream.clone.common.exception.KreamException;
import kream.clone.common.jwt.JwtUtil;
import kream.clone.member.dto.request.MemberLoginRequest;
import kream.clone.member.dto.request.MemberSignUpRequest;
import kream.clone.member.entity.Member;
import kream.clone.member.fixture.MemberFixture;
import kream.clone.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static kream.clone.member.fixture.MemberFixture.PASSWORD;
import static kream.clone.member.fixture.MemberFixture.USERNAME;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;
    @Mock
    private JwtUtil jwtUtil;
    final String ENCRYPTED_PASSWORD = "encryptedPassword";
    @Test
    public void 정상_회원가입(){
        //given
        MemberSignUpRequest request = new MemberSignUpRequest(USERNAME, PASSWORD);

        when(memberRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn(ENCRYPTED_PASSWORD);

        //when
        memberService.signUp(request);

        //then
        verify(memberRepository, times(1)).existsByUsername(request.getUsername());

        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository, times(1)).save(memberArgumentCaptor.capture());

        Member savedMember = memberArgumentCaptor.getValue();
        assertThat(savedMember.getUsername()).isEqualTo(request.getUsername());
        assertThat(savedMember.getPassword()).isEqualTo(ENCRYPTED_PASSWORD);
    }
    @Test
    public void 오류_회원가입_중복_아이디(){
        //given
        MemberSignUpRequest request = new MemberSignUpRequest(USERNAME, PASSWORD);

        //when
        when(memberRepository.existsByUsername(request.getUsername())).thenReturn(true);

        //then
        assertThatThrownBy(()-> memberService.signUp(request))
                .isInstanceOf(KreamException.class);
    }
    @Test
    public void 오류_로그인_없는_아이디(){
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);
        
        //when
        when(memberRepository.findByUsername(request.getUsername())).thenReturn(Optional.empty());

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        //then
        assertThatThrownBy(()-> memberService.login(request, response))
                .isInstanceOf(KreamException.class);
    }
    @Test
    public void 오류_로그인_다른_비밀번호(){
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);
        Member member = MemberFixture.toMember(USERNAME, PASSWORD);

        //when
        when(memberRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(request.getPassword(), member.getPassword())).thenReturn(false);

        //then
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        assertThatThrownBy(()-> memberService.login(request, response))
                .isInstanceOf(KreamException.class);
    }
    @Test
    public void 정상_로그인(){
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);
        Member member = MemberFixture.toMember(USERNAME, PASSWORD);

        //when
        when(memberRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(request.getPassword(), member.getPassword())).thenReturn(true);
        when(jwtUtil.createAccessToken(request.getUsername())).thenReturn("token");

        //then
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        memberService.login(request, response);

        verify(response).addHeader(Mockito.eq("AccessToken"), Mockito.anyString());
    }
}
