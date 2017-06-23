package unit6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient2 {
  public static void main(String[] args) throws Exception {

    DatagramSocket datagramSocket = new DatagramSocket();

    String msg =
        "+YAV:0005AABB,000 000 000 007 001 ,000 000 000 007 001 ,007 001 007 000 000 ,009 001 008 000 000 ,000 000 004 000 000 ,004 000 008 001 003 ,001 005 004 000 002 ,008 00C 00B 008 008 ,0 0,0 0,0 0 0 0,00,FF0203FF,V V V V V V V V,8AD00001,X,EEFF";
    String msg1 =
        "8AD00001,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000;\"x20\"x17\"x06\"x19\"x10\"x38\"x00\"x00\"x00...";
    InetAddress address = InetAddress.getByName("192.168.18.16");

    while (true) {
      // 閸欐垿锟戒焦鏆熼幑锟�
      byte[] buffer = msg.getBytes();
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 808);
      datagramSocket.send(packet);
      System.out.println("send!");
      Thread.sleep(1000);
      // 閹恒儲鏁归弫鐗堝祦
      // DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);
      // datagramSocket.receive(inputPacket);
      // System.out.println(new String(inputPacket.getData(), 0, inputPacket.getLength()));
      // Thread.sleep(100);
      // datagramSocket.close();
    }
  }
}
