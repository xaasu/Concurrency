package unit5;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorMain {
  @SuppressWarnings({"unchecked", "deprecation"})
  public static void main(String[] args) throws InterruptedException {
    Executor executor = Executors.newCachedThreadPool();
    PCDataFactory factory = new PCDataFactory();
    int bufferSize = 1024;
    Disruptor<EventData> disruptor = new Disruptor<>(factory, bufferSize, executor,
        ProducerType.MULTI, new BlockingWaitStrategy());
    disruptor.handleEventsWithWorkerPool(new DisruptorConsumer(), new DisruptorConsumer(),
        new DisruptorConsumer());
    disruptor.start();

    RingBuffer<EventData> ringBuffer = disruptor.getRingBuffer();
    DisruptorProducter producter = new DisruptorProducter(ringBuffer);
    ByteBuffer bb = ByteBuffer.allocate(8);
    for (long l = 0; l < 100; l++) {
      bb.putLong(0, l);
      producter.pushData(bb);
      Thread.sleep(100);
      System.out.println("add data :" + l);
    }
  }
}
