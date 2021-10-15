package spring.ioc;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String name;

    public User(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
