package hello.core.member;

public class MemberServiceImpl implements MemberService{

    /*MemberRepository 인터페이스와 MemoryMemberRepository 클래스 둘 다 의존한다. SOLID원칙 어긋남.

    기존 방법
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    */

    //현재 방법 : 생성자 메서드를 추가하고 인터페이스만 의존한다. (생성자 주입)
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findByID(memberId);
    }
}
