package algorithm.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Test")
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Clone yang = new Clone("yang");
        Object clone = yang.clone();
        System.out.println("clone = " + clone);

        int i = 0, j = 0;
        log.info("{}, {}", i++, ++j);

        float max = Long.MAX_VALUE;
        log.info("{}", max);
    }

}

class Clone implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private String name;

    public Clone(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clone{" +
                "name='" + name + '\'' +
                '}';
    }
}
