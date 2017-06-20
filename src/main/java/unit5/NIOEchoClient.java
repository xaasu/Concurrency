package unit5;

import java.nio.ByteBuffer;
import java.util.LinkedList;

public class NIOEchoClient {
  private LinkedList<ByteBuffer> outq;

  public NIOEchoClient() {
    super();
    this.outq = new LinkedList<>();
  }

  public LinkedList<ByteBuffer> getOutQueue() {
    return outq;
  }

  public void enqueue(ByteBuffer bb) {
    outq.addFirst(bb);
  }
}
