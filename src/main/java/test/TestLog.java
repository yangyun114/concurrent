package test;

import java.util.logging.Logger;

// 日志工具
public class TestLog {
    public static void main(String[] args) {
        Logger log = Logger.getLogger("TestLog");
        log.info("start process...");
        log.warning("memory is running out...");
        log.severe("service will terminate...");


    }
}
