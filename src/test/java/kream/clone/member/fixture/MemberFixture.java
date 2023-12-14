package kream.clone.member.fixture;

import kream.clone.member.entity.Member;

public class MemberFixture {

    public static final String USERNAME = "testUsername";
    public static final String PASSWORD = "testPassword";

    public static Member toMember(String username, String password){
        return Member.builder()
                .username(username)
                .password(password)
                .build();
    }
}
