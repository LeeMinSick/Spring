package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //스캔 권장 방법 - 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것. 스프링 부트도 이 방법을 기본으로 제공.

        /*위의 내용 때문에 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication(컴포넌트 스캔 있음)을
        프로젝트 시작 루트 위치에 두는 것이 관례*/

        //컴포넌트 스캔 범위를 아래 패키지부터 시작 {"위치", "위치"}처럼 여러 시작 위치를 지정할 수도 있다.
        basePackages = "hello.core.member",
        //지정한 클래스의 패키지부터 시작할 수 있다. (default = @ComponentScan이 붙은 클래스의 패키지부터 시작)
        basePackageClasses = AutoAppConfig.class,
        //@Configuration안에 @Component 존재 컴포넌트 스캔할 때 모든 설정들 컨테이너에 등록을 막기 위해 아래와 같이 설정.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
