package kream.clone.member.repository;

import kream.clone.member.entity.Member;
import kream.clone.member.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static kream.clone.member.fixture.MemberFixture.PASSWORD;
import static kream.clone.member.fixture.MemberFixture.USERNAME;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 정상_회원가입(){
        //given
        Member member = MemberFixture.toMember(USERNAME, PASSWORD);

        //when
        Member expectedMember = memberRepository.save(member);

        //then
        assertThat(expectedMember.getId()).isNotNull();
        assertThat(expectedMember.getUsername()).isEqualTo(USERNAME);
        assertThat(expectedMember.getPassword()).isEqualTo(PASSWORD);
    }
    @Test
    void 오류_회원가입_아이디_입력안함(){
        //given
        Member member = MemberFixture.toMember(null, PASSWORD);

        //when
        assertThatThrownBy(()-> memberRepository.save(member))
        .isInstanceOf(DataIntegrityViolationException.class)
        .hasMessage("could not execute statement [Column 'username' cannot be null] " +
            "[insert into `member` (`created_at`,`password`,`updated_at`," +
            "`username`) values (?,?,?,?)]; SQL [insert into `member` " +
            "(`created_at`,`password`,`updated_at`,`username`) " +
            "values (?,?,?,?)]; constraint [null]");
    }
    @Test
    public void 오류_회원가입_비밀번호_입력안함(){
        //given
        Member member = MemberFixture.toMember(USERNAME, null);

        //when
        assertThatThrownBy(()-> memberRepository.save(member))
        .isInstanceOf(DataIntegrityViolationException.class)
        .hasMessage("could not execute statement [Column 'password' cannot be null] " +
            "[insert into `member` (`created_at`,`password`,`updated_at`," +
            "`username`) values (?,?,?,?)]; SQL [insert into `member` " +
            "(`created_at`,`password`,`updated_at`,`username`) " +
            "values (?,?,?,?)]; constraint [null]");
    }
}
