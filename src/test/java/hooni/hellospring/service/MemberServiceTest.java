package hooni.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hooni.hellospring.domain.Member;
import hooni.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

  private MemoryMemberRepository repository;
  private MemberService service;

  @BeforeEach
  void beforeEach() {
    repository = new MemoryMemberRepository();
    service = new MemberService(repository);
  }

  @AfterEach
  void afterEach() {
    repository.resetStore();
  }

  @Test
  void testFindById() {

  }

  @Test
  void testFindMembers() {

  }

  @Test
  void testRegister() {
    Member member = new Member();
    member.setName("spring");

    Long memberId = service.register(member);
    Member newMember = service.findById(memberId).get();
    assertThat(newMember.getId()).isEqualTo(memberId);
    assertThat(newMember.getName()).isEqualTo(member.getName());
  }

  @Test
  void testDuplicateMemberValidation() {
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    service.register(member1);
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> service.register(member2));
    assertThat(e.getMessage()).isEqualTo("This name already exists");
  }
}
