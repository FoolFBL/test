import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1.创建DatagramSocket对象 准备发送和接受数据
        //发送端也可以接受数据 所以它也可以指定接受端口
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        //2.将需要发送的数据装包DatagramPacket对象中
        byte[] bytes = "hello 明天吃火锅".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.43.53"), 9999);
        //3.发送数据报
        datagramSocket.send(packet);
        //4.接受数据
        byte[] buf =new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(packet);
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data = packet.getData();//接收到数据
        String s = new String(data, 0, length);
        System.out.println(s);
        //4.关闭资源
        datagramSocket.close();
    }
}