package unit5;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class DisruptorProducter {
  private final RingBuffer<EventData> ringBuffer;

  public DisruptorProducter(RingBuffer<EventData> ringBuffer) {
    super();
    this.ringBuffer = ringBuffer;
  }

  public void pushData(ByteBuffer bb) {
    long sequence = ringBuffer.next();
    try {
      EventData event = ringBuffer.get(sequence);
      event.setValue(bb.getLong(0));
    } finally {
      ringBuffer.publish(sequence);
    }
  }
}
