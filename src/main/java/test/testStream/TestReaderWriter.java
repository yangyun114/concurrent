package test.testStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 字符输入输出流
@Slf4j
public class TestReaderWriter {
    static File file = new File("/Users/yangyun/testReaderWriter.txt");
    static {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
//        write();
//        read();
        testFiles();
    }

    @SneakyThrows
    static void write() {
        Writer writer = new FileWriter(file);
        writer.write("测试字符输入输出流...");
        writer.close();
    }

    @SneakyThrows
    static void read() {
        FileReader reader = new FileReader(file);

        char[] arr = new char[100];

        while (true) {
            int read = reader.read(arr);
            if (read == -1)
                break;
            log.info("{}", new String(arr, 0, read));
        }
    }

    // jdk7中新增了一个Files类和Paths类来处理一些简单的输入输出流
    @SneakyThrows
    static void testFiles() {
        Path path = Paths.get(file.toURI());
        byte[] bytes = Files.readAllBytes(path);
        log.info("{}", new String(bytes));

        Files.write(path, "测试Files".getBytes());
    }
}
