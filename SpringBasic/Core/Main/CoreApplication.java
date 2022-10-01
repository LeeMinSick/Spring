package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
	/* 요구사항 Core Principle #1
	회원
		회원을 가입하고 조회할 수 있다.
		회원은 일반과 VIP 두 가지 등급이 있다.
		회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다.(미확정)

	주문과 할인 정책
		회원은 상품을 주문할 수 있다.
		회원 등급에 따라 할인 정책을 적용할 수 있다.
		할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액할인을 적용해달라.(나중에 변경 될 수 있다.)
		할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다.
		최악의 경우 할인을 적용하지 않을 수도 있다.
	*/

	/* 새로운 할인 정책 개발 Core Principle #2
	"새로운 할인 정책을 확장해보자."
	"악덕 기획자" : 서비스 오픈 직전에 할인 정책을 지금처럼 고정금액 할인이 아니라 좀 더 합리적인 주문 금액당 할인하는
				  정률% 할인으로 변경하고 싶어요. 예를 들어 기존 정책은 VIP가 10000원을 주문하든 20000원을 주문하든
				  항상 1000원을 할인했는데, 이번에 새로 나온 정책은 10%로 지정해두면 고객이 10000원 주문시 1000원을
				  할인해주고, 20000원 주문시에 2000원을 할인해주는 거예요!

	"순진 개발자" : 제가 처음부터 고정 금액 할인은 아니라고 했잖아요.

	"악덕 기획자" : 애자일 소프트웨어 개발 선언 몰라요? "계획을 따르기보다 변화에 대응하기를"

	"순진 개발자" : ...(하지만 난 유연한 설계가 가능하도록 객체지향 설계 원칙을 준수했지 후후)
	*/
}
