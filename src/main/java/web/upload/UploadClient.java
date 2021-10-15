package web.upload;

import java.io.*;
import java.net.Socket;

public class UploadClient {
    public static void main(String[] args) throws IOException {
        // 1、创建本地输入流获取本地文件
        FileInputStream inputStream = new FileInputStream("~/Documents/a.txt");

        // 2、创建客户端的Socket，传入服务器的ip和端口号
//        Socket socket = new Socket("192.168.10.44", 8888);
        Socket socket = new Socket("127.0.0.1", 8888);

        // 3、获取Socket中的输出流
        OutputStream os = socket.getOutputStream();

        // 4、使用本地输入流的read方法读取数据
        int len;
        byte[] arr = new byte[1024];
        while ((len = inputStream.read(arr)) != -1) {
            // 5、将读取到的数据使用write方法，写入到网络输出流
            os.write(arr, 0, len);
        }

        // 6、获取socket中的网络输入流
        InputStream is = socket.getInputStream();

        // 7、使用read方法，读取网络输入流中的服务器的信息
        while ((len = inputStream.read(arr)) != -1) {
            System.out.println(new String(arr, 0, len));
        }

        // 8、关闭Socket和本地流
        inputStream.close();
        socket.close();
    }
}
