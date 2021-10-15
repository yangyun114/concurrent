package web.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端，接收客户端的请求，获取客户端发送的请求，给客户端写回数据
 * 对应的类，ServerSocket
 * */
public class Server {
    public static void main(String[] args) throws IOException {
        // 1、创建一个ServerSocket，向操作系统申请一个端口号
        ServerSocket serverSocket = new ServerSocket(8888);

        // 2、使用accept方法，获取到来自客户端的Socket对象
        Socket socket = serverSocket.accept();

        // 3、获取Socket中的输入流
        InputStream is = socket.getInputStream();

        // 4、读取输入流中的数据
        // read方法读取输入流，传参byte[]表示将读取的字节放到数组中，每次读取10个字节
        // 无参的read方法表示一次只读取一个字节
        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr, 0, len));

        // 5、获取Socket中的输出流
        OutputStream os = socket.getOutputStream();

        // 6、使用输出流回写数据
        os.write("收到谢谢".getBytes());

        // 7、关闭Socket
        socket.close();
    }
}
