package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
/*
    메인 데이터베이스의 커넥션을 흭득하는 스프링 빈은 @Primary를 적용해서 조회하는 곳에서 @Qualifier 지정 없이 편리하게
    조회하고, 서브 데이터베이스 커넥션 빈을 흭득할 때는 @Qualifier를 지정해서 명시적으로 흭득 하는 방식으로 사용하면 코드를
    깔끔하게 유지할 수 있다

    @Primary와 @Qualifier 둘 중에 우선 순위는 @Qualifier 높다
*/
//@Qualifier("rateDiscountPolicy")
//@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

    }
}
