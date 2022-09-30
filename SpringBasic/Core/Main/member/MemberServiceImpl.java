package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //MemberRepository 인터페이스와 MemoryMemberRepository 클래스 둘 다 의존한다. SOLID원칙 어긋남.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findByID(memberId);
    }
}
