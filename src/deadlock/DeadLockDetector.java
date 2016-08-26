package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author Lars Mortensen
 */
class DeadLockDetector implements Runnable {

  ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
  boolean doRun = true;
  
  public void stop() {
    this.doRun = false;
  }
  
  @Override
  public void run() {
    while (doRun) {
      long[] threadIds = tmxb.findDeadlockedThreads();
      
      if(threadIds != null){
          ThreadInfo[] infos = tmxb.getThreadInfo(threadIds);
          
          stop();
          for(ThreadInfo info : infos){
              System.out.println(info);
          }
      }
      
    }
  }
}