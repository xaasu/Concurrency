package unit5;

public class Client {
  public Data request(final String queryStr) {
    final FutureData future = new FutureData();
    new Thread() {
      public void run() {
        RealData real = new RealData(queryStr);
        future.setRealData(real);
      };
    }.start();
    return future;
  }

  public static void main(String[] args) {
    Client client = new Client();
    Data data = client.request("123");
    System.out.println("请求完毕");
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("数据:" + data.getResult());
  }
}
