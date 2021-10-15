package test.testJUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class TestFactorial {
    // 计算n的阶乘
    static long fact(int n) {
        if (n < 0)
            throw new IllegalArgumentException("数值无效...");
        long r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    // 把字符串的第一个字符大写，其他后续全小写
    String toCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }


}

class TestTestFactorial {
    @Test
    void testFact() {
        Assertions.assertEquals(1, TestFactorial.fact(1));

        Assertions.assertThrows(IllegalArgumentException.class, () -> TestFactorial.fact(-1));
    }

    private TestFactorial factorial;
    // 每个test方法之前和之后都会执行
    @BeforeEach
    void init() {
        factorial = new TestFactorial();
    }
    @AfterEach
    void destroy() {
        factorial = null;
    }

    // 第一种方式，通过MethodSource注解进行参数化测试
    @ParameterizedTest
    @MethodSource
    void testToCase(String input, String result) {
        Assertions.assertEquals(result, factorial.toCase(input));
    }
    // 同名方法必须是静态的
    static List<Arguments> testToCase() {
        return Arrays.asList(
                Arguments.arguments("abc", "Abc"),
                Arguments.arguments("ABC", "Abc"),
                Arguments.arguments("AbC", "Abc"));
    }

    // 第二种方式，利用CsvSource注解进行参数化测试
    @ParameterizedTest
    @CsvSource({"apple, Apple", "gooD, Good", "GOD, God"})
    void testToCase2(String input, String result) {
        Assertions.assertEquals(result, factorial.toCase(input));
    }



}
