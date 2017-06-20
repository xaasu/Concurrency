package unit5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientDemo {
  public static void main(String[] args) {
    Socket client = null;
    PrintWriter writer = null;
    BufferedReader reader = null;
    try {
      client = new Socket();
      client.connect(new InetSocketAddress("localhost", 8000));
      writer = new PrintWriter(client.getOutputStream(), true);
      writer.println("Hello!");
      writer.flush();
      reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      System.out.println("from server : " + reader.readLine());

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (client != null) {
          client.close();
        }
        if (writer != null) {
          writer.close();
        }
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
