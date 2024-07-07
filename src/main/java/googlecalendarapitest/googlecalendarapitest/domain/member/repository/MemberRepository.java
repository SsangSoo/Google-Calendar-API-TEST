package googlecalendarapitest.googlecalendarapitest.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import googlecalendarapitest.googlecalendarapitest.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
