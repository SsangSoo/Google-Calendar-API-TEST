package googlecalendarapitest.googlecalendarapitest.domain.member.repository;

import googlecalendarapitest.googlecalendarapitest.domain.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void memberSaveTest() {
        Member member = Member.of("성수", "쌩수");
        Member savedMember = memberRepository.save(member);

//        assertThat(member.getName()).isEqualTo(savedMember.getName());
//        assertThat(member.getNickname()).isEqualTo(savedMember.getNickname());

        assertThat(savedMember).extracting("name", "nickname")
                .containsExactlyInAnyOrder( "쌩수", "성수");
    }


}
