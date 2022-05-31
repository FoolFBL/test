import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1.创建一个DatagramSocket准备在9999接受数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //2.构建一个DatagramPacket对象 准备接受数据
        //一个数据报最大是64k
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        //3.调用接收方法 将通过网络传输的DatagramPacket对象 填充到packet对象
        System.out.println("接收端等待接收数据");
        datagramSocket.receive(packet);
        //4.可以把packet进行拆包 取出数据
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data = packet.getData();//接收到数据
        String s = new String(data, 0, length);
        System.out.println(s);
        //5.回复消息
        byte[] bytes = "好的 明天见".getBytes();
        DatagramPacket packet1 = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(),9998);
        datagramSocket.send(packet1);
        //6.关闭资源
        datagramSocket.close();
    }
}