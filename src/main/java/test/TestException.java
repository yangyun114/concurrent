package test;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

// 异常
@Slf4j(topic = "TestExce" +
        "ption")
public class TestException {
    public static void main(String[] args) {
//        one();
        BigDecimal a = null;
        BigDecimal b = new BigDecimal(0);
        log.info("{}", b.compareTo(a));
    }

    static Result<Integer> one() {
        Result<Integer> result = new Result<>();
        two();
        return result;
    }

    static void two() {
        throw new MyException();
    }
}

class Result<T> {
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result() {
    }
}

// 一般来讲是继承运行时异常
class MyException extends RuntimeException {
    public MyException() {
    }

    public MyException(String s) {
        super(s);
    }

    public MyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MyException(Throwable throwable) {
        super(throwable);
    }
}
