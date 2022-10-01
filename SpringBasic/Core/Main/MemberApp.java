package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {

        /*기존 방법(생성자 주입 X)
        MemberService memberService = new MemberServiceImpl();*/

        //현재 방법
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
