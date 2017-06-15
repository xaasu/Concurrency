package unit5;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<EventData> {

  @Override
  public EventData newInstance() {
    return new EventData();
  }

}
