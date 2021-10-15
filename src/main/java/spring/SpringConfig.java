package spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import spring.ioc.User;
import spring.ioc.UserService;

import java.time.ZoneId;

// 使用非xml的方式注入
// 注意此时的目录，必须在one的目录之上
@Configuration
@ComponentScan
// 启用切面自动代理
@EnableAspectJAutoProxy
public class SpringConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }

    // 这个注解，用来创建第三方的bean
    // IoC容器调用这个方法之后，把创建生成的bean放到容器中，其中bean的名称就是方法名，可以通过name来赋予
    // 当存在多个同类型的bean时，可以通过Primary注解来指定一个主要类型，经常使用在主从数据库中
    // 因为自动注入的限制是，配置文件必须位于所有bean的上层文件夹
    // 使用xml也可以实现第三方的bean注入
    @Bean(name = "z")
    @Primary
    ZoneId createZoneId() {
        return ZoneId.of("Z");
    }

    // Qualifier注解作用跟上面的name是一致的，也是用来指定bean的name
    @Bean
    @Qualifier("utc8")
    ZoneId createZoneIdOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
