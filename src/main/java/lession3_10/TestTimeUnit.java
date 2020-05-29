package lession3_10;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "TestTimeUnit")
public class TestTimeUnit {

    public static void main(String[] args) throws InterruptedException {
        log.info("enter");
        TimeUnit.SECONDS.sleep(5);
        log.info("end");
    }
}
