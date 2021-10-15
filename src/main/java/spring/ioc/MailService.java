package spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MailService {
    // 表示是非必须的，如果找不到这个bean，也不会报错
    @Autowired(required = false)
    // 当存在多个相同类型的bean时，使用这个注解可以准确定位到具体的某一个bean
    @Qualifier("utc8")
    private ZoneId zoneId = ZoneId.systemDefault();

    // 初始化
    @PostConstruct
    public void init() {
        System.out.println("Init mail service with zoneId = " + this.zoneId);
    }

    // 销毁
    @PreDestroy
    public void shutdown() {
        System.out.println("Shutdown mail service");
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    // 登陆邮件
    public void sendLoginMail(User user) {
        System.err.println(String.format("Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    // 注册邮件
    public void sendRegistrationMail(User user) {
        System.err.println(String.format("Welcome, %s!", user.getName()));

    }
}
