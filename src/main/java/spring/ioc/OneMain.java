package spring.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 使用xml的方式注入
@Slf4j
public class OneMain {
    public static void main(String[] args) {
        // 读取配置文件，获取到所有的bean，BeanFactory用来一次性获取，一般情况使用ApplicationContext
        // context实际上就是IoC容器的引用
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        // 从IoC容器中获取到我们所需要的bean
        UserService userService = context.getBean(UserService.class);
        // 调用bean的方法
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());

        log.info("1111");
    }
}
