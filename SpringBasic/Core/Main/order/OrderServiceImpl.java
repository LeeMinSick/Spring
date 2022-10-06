package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor//파이널이 붙은 필드의 생성자를 자동으로 만들어줌
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

    /*필드 주입 - 필드에 final 예약어 없어야 한다
        코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다
        DI 프레임워크가 없으면 아무것도 할 수 없다 - 순수한 자바코드로 테스트 불가능 스프링이 무조건 존재해야 함
        사용하지말자
            애플리케이션의 실제 코드와 관계 없는 테스트 코드
            스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용

    @Autowired
    private DiscountPolicy discountPolicy;
    @Autowired
    private MemberRepository memberRepository;
    */

    /*생성자 주입(우선순위1) - 빈 생성시 주입
        생성자 호출시점에 딱 1번만 호츨되는 것이 보장
        불변, 필수 의존관계에 사용  - 셋겟메서드를 만들지 않기 때문에 불변
        생성자가 하나 일경우 @Autowried를 생략해도 자동 주입
    */

    @Autowired
    public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    /*수정자 주입(setter 우선순위2) - 생성된 이후 주입 - 필드에 final 예약어 없어야 한다
        선택, 변경 가능성이 있는 의존관계에 사용
        자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
        선택적으로 사용할때는 @Autowired(required = false)로 필수 값이 아니라는 것을 명시

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*일반 메서드 주입 - 인스턴스 필드에 final 예약어가 없어야 한다
        한번에 여러 필드를 주입 받을 수 있다
        일반적으로 잘 사용하지 않는다

    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
