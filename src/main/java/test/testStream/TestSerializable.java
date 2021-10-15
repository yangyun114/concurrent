package test.testStream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;

// 实现序列化和反序列化
@Slf4j
public class TestSerializable {
    public static void main(String[] args) throws IOException {
        final ByteArrayOutputStream buffer = serialize();
        deserialize(buffer);

        serialize("/Users/yangyun/serial.txt");
        deserialize("/Users/yangyun/serial.txt");
    }

    // 序列化，把Java对象转化为字节流(byte[])的过程，使用ObjectOutputStream
    // 输入参数为OutputStream
    @SneakyThrows
    static ByteArrayOutputStream serialize() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            // 写入int
            output.writeInt(12345);
            // 写入utf-8string
            output.writeUTF("你好");
            // 写入object
            output.writeObject(Double.valueOf(12.34));
        }
//        log.info("{}", new String(buffer.toByteArray(), "UTF-8"));
        log.info("{}", Arrays.toString(buffer.toByteArray()));
        return buffer;
    }

    // 反序列化，关键类ObjectInputStream，及其方法readObject，
    // ObjectInputStream的输入参数为InputStream
    @SneakyThrows
    static void deserialize(ByteArrayOutputStream buffer) {
//        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(buffer.toByteArray());
//        try (ObjectInputStream input = new ObjectInputStream(arrayInputStream)) {
//            final int i = input.readInt();
//            log.info("{}", i);
//            String s = input.readUTF();
//            log.info("{}", s);
//            final Double aDouble = (Double) input.readObject();
//            log.info("{}", aDouble);
//        }

        // 这个地方会读取一个header，所以不能是任意的流
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream("hello".getBytes());
        try (ObjectInputStream input = new ObjectInputStream(arrayInputStream)) {
            final Object object = input.readObject();
        }
    }

    // 尝试把一个java对象序列化保存到本地文件
    static void serialize(String path) throws IOException {
        // 创建文件
        File file = new File(path);
        file.createNewFile();

        User user = new User("1", "yang");

        // 获取文件输出流和序列化流
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try (ObjectOutputStream output = new ObjectOutputStream(fileOutputStream)) {
            output.writeObject(user);
        }
    }

    // 把本地文件读取，并反序列化
    @SneakyThrows
    static void deserialize(String path) {
        User user = null;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        try (ObjectInputStream input = new ObjectInputStream(fileInputStream)) {
            user = (User) input.readObject();
        }
        log.info("{}", user);
    }


}

@Data
@EqualsAndHashCode(callSuper = false)
class User implements Serializable{
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
