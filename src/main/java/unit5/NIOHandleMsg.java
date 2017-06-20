package unit5;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;


public class NIOHandleMsg implements Runnable {
  SelectionKey sk;
  ByteBuffer bb;

  public NIOHandleMsg(SelectionKey sk, ByteBuffer bb) {
    super();
    this.sk = sk;
    this.bb = bb;
  }

  @Override
  public void run() {
    NIOEchoClient echoClient = (NIOEchoClient) sk.attachment();
    echoClient.enqueue(bb);
    sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    try {
      Selector.open().wakeup();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
