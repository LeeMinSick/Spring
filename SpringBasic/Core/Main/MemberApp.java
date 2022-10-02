package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        /*기존 방법(생성자 주입 X, 스프링 X)
        MemberService memberService = new MemberServiceImpl();*/

        /*기존 방법(생성자 주입 O, 스프링 X)
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/

        //현재 방법(스프링 O)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
