package spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.aop.MetricTime;

import java.util.Arrays;
import java.util.List;

// 使用xml方式时，不需要这个注解
// 使用自动注入时，这个注解用来表示这是一个组件，可以被扫描进入IoC容器
// 相当于xml中的<bean>节点
@Component
// 作用域注解，默认是单例，可以把它修改为原型
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService {

    // 使用xml方式时，不需要这个注解
    // 使用自动注入时，这个注解用来自动注入，从IoC容器中拿到这个bean
    // 相当于xml中的<property>节点
    @Autowired
    private MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    // 模拟数据库
    private List<User> users = Arrays.asList( // users:
            new User(1, "bob@example.com", "password", "Bob"), // bob
            new User(2, "alice@example.com", "password", "Alice"), // alice
            new User(3, "tom@example.com", "password", "Tom")); // tom

    // 用户登陆操作
    // 使用注解来监控login方法运行时间
    @MetricTime("login")
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed.");
    }

    // 根据id获取到用户
    public User getUser(int id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("没有找到用户..."));
    }

    // 注册
    public User register(String email, String password, String name) {
        // 用户已经存在了
        users.forEach((user) -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        });
        // 创建用户
        User user = new User(users.stream().mapToInt(u -> u.getId()).max().getAsInt() + 1, email, password, name);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}
