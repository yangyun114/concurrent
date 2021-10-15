package web.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {
    public static void main(String[] args) throws IOException {
        // 1、创建一个ServerSocket
        ServerSocket serverSocket = new ServerSocket(8888);

        // 2、使用accept获取一个Socket
        Socket socket = serverSocket.accept();

        // 3、使用Socket获取一个输入流
        InputStream is = socket.getInputStream();

        int len;
        byte[] arr = new byte[1024];

        // 4、创建一个本地输出流
        FileOutputStream outputStream = new FileOutputStream("Documents/workspace");

        // 5、读取输入流中的数据
        while ((len = is.read(arr)) != -1) {
            // 6、将网络输入流中的数据，写到本地输出流中
            outputStream.write(arr, 0, len);
        }

        // 7、使用Socket获取一个输出流
        OutputStream os = socket.getOutputStream();

        // 8、使用网络输出流写入信息，告知客户端已经上传成功
        os.write("上传成功".getBytes());

        // 9、关闭本地流和Socket
        outputStream.close();
        socket.close();
    }
}
