package kream.clone.member.service;

import kream.clone.common.exception.ErrorCode;
import kream.clone.common.exception.KreamException;
import kream.clone.common.jwt.JwtUtil;
import kream.clone.member.dto.request.MemberSignUpRequest;
import kream.clone.member.entity.Member;
import kream.clone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    //private final JwtUtil jwtUtil;

    @Transactional
    public void signUp(MemberSignUpRequest request){
        if(memberRepository.existsByUsername(request.getUsername())){
            throw new KreamException(ErrorCode.ALREADY_EXIST_USERNAME);
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Member member = request.toMember(encryptedPassword);

        memberRepository.save(member);
    }
}
