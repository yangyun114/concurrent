package test.testStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

// 基本输入输出流
@Slf4j(topic = "TestStream")
public class TestStream {
    public static void main(String[] args) throws IOException {
        copy();
    }

    static void testOutput() throws IOException {
        try (FileOutputStream output = new FileOutputStream("/Users/yangyun/my.txt")) {
            output.write("hello".getBytes("UTF-8"));
        }

        // 模拟输出流
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            output.write("hello".getBytes("UTF-8"));

            // 可以转化为array打印出来，进行验证
            log.info("{}", new String(output.toByteArray(), "UTF-8"));
        }
    }

    static void testInput() throws IOException {
        // Java7新增的写法，编译器会在后面自动加上finally，并且关闭流
        try (FileInputStream input = new FileInputStream("/Users/yangyun/my.txt")) {
            while (true) {
                // 读到的值是1个字节，并且返回字节的值0～255，如果为-1，说明读取完了
                int read = input.read();
                log.info("{}", read);
                if (read == -1)
                    break;
            }
            // 使用完成后需要关闭流
            // input.close();
        }

        // 带缓冲区的读
        try (FileInputStream input = new FileInputStream("/Users/yangyun/my.txt")) {
            while (true) {
                byte[] arr = new byte[100];
                int read = input.read(arr);
                log.info("{}", read);
                log.info("{}", new String(arr, "UTF-8"));
                if (read == -1)
                    break;
            }
        }

        // 使用ByteArrayInputStream模拟输入流
        byte[] data = {1, 2};
        InputStream inputStream = new ByteArrayInputStream(data);
    }

    static void testFile() throws IOException {
        File file = new File("/Users/yangyun/my.txt");
        log.info("{}", file);

        // 判断是否存在
        log.info("{}", file.isFile());
        log.info("{}" ,file.isDirectory());

        // 判断是否可读可写可执行
        log.info("{}", file.canRead());
        log.info("{}", file.canWrite());
        log.info("{}", file.canExecute());

        // 文件字节大小
        log.info("{}", file.length());

        // 创建文件
        file.createNewFile();
        // 删除文件
        file.delete();
    }

    // copy一份文件
    @SneakyThrows
    static void copy() {
        try (FileInputStream input = new FileInputStream("/Users/yangyun/my.txt")) {
            // 创建一个file
            File file = new File("/Users/yangyun/myCopy.txt");
            if (!file.exists())
                file.createNewFile();

            try(FileOutputStream output = new FileOutputStream("/Users/yangyun/myCopy.txt")) {
                while (true) {
                    byte[] arr = new byte[100];
                    // 此时read返回的是数组的长度，而不再是下一个字节的值，所以直接截断数组，就是当前的值
                    int read = input.read(arr);
                    if (read == -1)
                        break;
                    final String value = new String(arr, 0, read);
                    log.info("{}", value);
                    // 值写入到输出流
                    output.write(value.getBytes("UTF-8"));
                }
            }
        }
    }
}
