package unit5;

import com.lmax.disruptor.WorkHandler;

public class DisruptorConsumer implements WorkHandler<EventData> {

  @Override
  public void onEvent(EventData event) throws Exception {
    System.out
        .println(Thread.currentThread().getId() + ":Event : --" + event.get() * event.get() + "--");
  }

}
