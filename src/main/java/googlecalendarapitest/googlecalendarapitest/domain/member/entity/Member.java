package googlecalendarapitest.googlecalendarapitest.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Builder
    private Member(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public static Member of(String name, String nickname) {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .build();
    }



}
