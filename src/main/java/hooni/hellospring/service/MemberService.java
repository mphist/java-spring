package hooni.hellospring.service;

import java.util.List;
import java.util.Optional;
import hooni.hellospring.domain.Member;
import hooni.hellospring.repository.MemberRepository;

public class MemberService {
  private final MemberRepository repository;

  public MemberService(MemberRepository repository) {
    this.repository = repository;
  }

  /**
   * Register a member
   */
  public Long register(Member member) {
    // duplicate names not allowed
    checkForDuplication(member);

    repository.save(member);
    return member.getId();
  }

  private void checkForDuplication(Member member) {
    repository.findByName(member.getName()).ifPresent(m -> {
      throw new IllegalStateException("This name already exists");
    });
  }

  /**
   * Get all members
   */
  public List<Member> findMembers() {
    return repository.findAll();
  }

  /**
   * Get by member id
   */
  public Optional<Member> findById(Long id) {
    return repository.findById(id);
  }
}
