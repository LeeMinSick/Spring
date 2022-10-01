package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{


    /*
    아래 FixDiscountPolicy에서 RateDiscountPolicy로 객체를 변경하고 싶을때는 코드를 수정할 수 밖에 없다.
    이것은 SOLID원칙의 OCP를 위반한 것이며, 인터페이스와 구현체 둘 다 의존하고 있기 때문에 DIP 또한 위반한 것이다.

    해결 방법 : DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다. -> 어떻게??

    방법 : 초기화 하지 않고 선언만 한다(인터페이스에만 의존).
          다른 곳에서 객체를 생성해서 discountPolicy 변수에 주입한다. (생성자 주입)
          Ex) AppConfig.java에서처럼 구현 객체를 생성 후 주입한다.

    기존 방법
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    */

    //해결 방법
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
