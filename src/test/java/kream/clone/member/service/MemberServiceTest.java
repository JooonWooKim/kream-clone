package kream.clone.member.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static kream.clone.member.fixture.MemberFixture.PASSWORD;
import static kream.clone.member.fixture.MemberFixture.USERNAME;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;
    final String ENCRYPTED_PASSWORD = "encryptedPassword";
    @Test
    @DisplayName("회원가입")
    public void signUp(){
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
}
