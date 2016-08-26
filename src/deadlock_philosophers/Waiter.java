package deadlock_philosophers;

import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Nicklas Molving
 */
public class Waiter{
    
    Philosopher[] p;
    Lock[] chopsticks;
    
    public Waiter(Philosopher[] p, Lock[] chopsticks){
        this.p = p;
        this.chopsticks = chopsticks;
    }
    
    public boolean request(int ID){
        if(chopsticks[ID-1].tryLock() && chopsticks[ID].tryLock()){
            return true;
        }
        return false;
    }
    
}
