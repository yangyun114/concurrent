package web.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 向服务器发送请求，接收服务器的处理
 * 客户端对应的类Socket
 * */
public class Client {
    public static void main(String[] args) throws IOException {
//        // 1、创建一个Socket
//        // 注意：创建Socket对象时，如果服务器没有启动，就会抛出异常；并且，
//        // 在创建Socket对象时，就会尝试与服务器进行三次握手建立连接
////        Socket socket = new Socket("127.0.0.1", 8888);
//        Socket socket = new Socket("192.168.10.44", 8888);
//
//        // 2、获取Socket中的字节输出流
//        // 注意：必须使用Socket中的流，而不能自己创建流
//        OutputStream os = socket.getOutputStream();
//
//        // 3、使用流中的write方法给服务器写数据
//        os.write("你好服务器".getBytes());
//
//        // 4、获取Socket中的字节输入流
//        InputStream is = socket.getInputStream();
//
//        // 5、使用流中的read方法读取从服务器传输过来的数据
//        byte[] arr = new byte[1024];
//        int len = is.read(arr);
//        System.out.println(new String(arr, 0, len));
//
//        // 6、释放资源
//        socket.close();


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            try (Socket socket = new Socket("192.168.10.44", 8888)) {
                OutputStream output = socket.getOutputStream();
                output.write(s.getBytes());

                // 强制把缓冲区数据发送出去
                output.flush();
            }
        }


    }
}
