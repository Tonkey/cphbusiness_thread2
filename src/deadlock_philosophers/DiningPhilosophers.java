package deadlock_philosophers;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is the classic dining philosophers problem. See the textbook for a
 description of the problem. This version will deadlock. 
 Ref: http://everythingcomputerscience.com/projects/java_programs/DiningPhilosophersSolution.txt
 *
 * @author Barbara Lerner
 * @version Oct 5, 2010
 *
 */
public class DiningPhilosophers {

  // The number of philosophers
  private static final int NUM_PHILOSOPHERS = 5;

  /**
   * Test the dining philosophers solution
   *
   * @param args Not used
   */
  public static void main(String[] args) {
    // Model each chopstick with a lock
    DeadLockDetector dld = new DeadLockDetector();
    new Thread(dld).start();
    Lock[] chopsticks = new ReentrantLock[NUM_PHILOSOPHERS];

    for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
      chopsticks[i] = new ReentrantLock();
    }

    // Create the philosophers and start each running in its own thread.
    Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

    Waiter waiter = new Waiter(philosophers, chopsticks);
    for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
      philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUM_PHILOSOPHERS], waiter);
      new Thread(philosophers[i]).start();
    }
  }

}

