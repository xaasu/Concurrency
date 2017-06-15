package unit3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The Class TraceThreadPoolExecutor.
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

  /**
   * execute
   * 
   * @see java.util.concurrent.ThreadPoolExecutor#execute(java.lang.Runnable)
   */
  @Override
  public void execute(Runnable command) {
    super.execute(warp(command, clientTrace(), Thread.currentThread().getName()));
  }

  /**
   * submit
   * 
   * @see java.util.concurrent.AbstractExecutorService#submit(java.lang.Runnable)
   */
  @Override
  public Future<?> submit(Runnable task) {
    return super.submit(warp(task, clientTrace(), Thread.currentThread().getName()));
  }

  /**
   * Instantiates a new trace thread pool executor.
   *
   * @param corePoolSize the core pool size
   * @param maximumPoolSize the maximum pool size
   * @param keepAliveTime the keep alive time
   * @param unit the unit
   * @param workQueue the work queue
   * @param handler the handler
   */
  public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
  }

  /**
   * Instantiates a new trace thread pool executor.
   *
   * @param corePoolSize the core pool size
   * @param maximumPoolSize the maximum pool size
   * @param keepAliveTime the keep alive time
   * @param unit the unit
   * @param workQueue the work queue
   * @param threadFactory the thread factory
   * @param handler the handler
   */
  public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
      RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
  }

  /**
   * Instantiates a new trace thread pool executor.
   *
   * @param corePoolSize the core pool size
   * @param maximumPoolSize the maximum pool size
   * @param keepAliveTime the keep alive time
   * @param unit the unit
   * @param workQueue the work queue
   * @param threadFactory the thread factory
   */
  public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
  }

  /**
   * Instantiates a new trace thread pool executor.
   *
   * @param corePoolSize the core pool size
   * @param maximumPoolSize the maximum pool size
   * @param keepAliveTime the keep alive time
   * @param unit the unit
   * @param workQueue the work queue
   */
  public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  /**
   * Client trace.
   *
   * @return the exception
   */
  private Exception clientTrace() {
    return new Exception("Client stack trace");
  }

  /**
   * Warp.
   *
   * @param task the task
   * @param clientStack the client stack
   * @param ThreadName the thread name
   * @return the runnable
   */
  private Runnable warp(final Runnable task, final Exception clientStack, String ThreadName) {
    return new Runnable() {
      @Override
      public void run() {
        try {
          task.run();
        } catch (Exception e) {
          clientStack.printStackTrace();
          throw e;
        }
      }
    };
  }

  public static void main(String[] args) {
    ThreadPoolExecutor pools = new TraceThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
        new SynchronousQueue<Runnable>());
    for (int i = 0; i < 5; i++) {
      pools.execute(new DivTask(100, i));
    }
  }
}
