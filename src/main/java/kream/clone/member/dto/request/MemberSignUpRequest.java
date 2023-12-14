package kream.clone.member.dto.request;

import kream.clone.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpRequest {

    private String username;
    private String password;

    public Member toMember(String encryptedPassword){
        return Member.builder()
                .username(username)
                .password(encryptedPassword)
                .build();
    }

    public MemberSignUpRequest(String username, String password){
        this.username = username;
        this.password = password;
    }
}
