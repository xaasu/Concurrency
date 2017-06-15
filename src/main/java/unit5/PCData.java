package unit5;

public class PCData {
  private final int data;

  public PCData(int data) {
    super();
    this.data = data;
  }

  public PCData(String d) {
    data = Integer.parseInt(d);
  }

  public int getData() {
    return data;
  }

  @Override
  public String toString() {
    return "PCData [data=" + data + "]";
  }
}
