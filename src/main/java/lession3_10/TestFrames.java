package lession3_10;

public class TestFrames {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                method(20);
            }
        };
        t.setName("t");
        t.start();
        method(10);

    }

    private static void method(int i) {
        int j = i + 1;
        Object m = method2();
        System.out.println("m = " + m);

    }

    private static Object method2() {
        Object n = new Object();
        return n;
    }
}
