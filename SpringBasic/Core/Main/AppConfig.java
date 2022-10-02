package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 1.AppConfig에서 실제 동작에 필요한 구현 객체를 생성한다.
    // 2.생성자를 통해서 주입(연결)한다. 생성자 주입으로 인해 OCP, DIP 원칙을 지킬 수 있다.
    
    //생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정
    //Service 구현 객체는 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.(관심사의 분리)
    
    //관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리

//    AppConfig는 사용 영역으로써 사용되며, 수정은 AppConfig에서만 일어난다.
    
    //생성자 주입
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
