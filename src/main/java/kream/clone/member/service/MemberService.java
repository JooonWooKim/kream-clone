package kream.clone.member.service;

import jakarta.servlet.http.HttpServletResponse;
import kream.clone.common.exception.ErrorCode;
import kream.clone.common.exception.KreamException;
import kream.clone.common.jwt.JwtUtil;
import kream.clone.member.dto.request.MemberLoginRequest;
import kream.clone.member.dto.request.MemberSignUpRequest;
import kream.clone.member.entity.Member;
import kream.clone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kream.clone.common.jwt.JwtUtil.AUTHORIZATION_ACCESS;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signUp(MemberSignUpRequest request){
        if(memberRepository.existsByUsername(request.getUsername())){
            throw new KreamException(ErrorCode.ALREADY_EXIST_USERNAME);
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Member member = request.toMember(encryptedPassword);

        memberRepository.save(member);
    }

    public void login(MemberLoginRequest request, HttpServletResponse response) {
        Member member = memberRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new KreamException(ErrorCode.NOT_FOUND_MEMBER));

        validatePasswordMatch(request.getPassword(), member.getPassword());

        issueTokens(response, member.getUsername());
    }

    private void validatePasswordMatch(String encryptedPassword, String inputPassword) {
        if(passwordEncoder.matches(encryptedPassword, inputPassword)){
            return;
        }
        throw new KreamException(ErrorCode.NOT_VALID_PASSWORD);
    }

    private void issueTokens(HttpServletResponse response, String username) {
        String accessToken = jwtUtil.createAccessToken(username);
        response.addHeader(AUTHORIZATION_ACCESS, accessToken);
    }

}
