package ex1;

/**
 *
 * @author Nicklas Molving
 */
public class TotalBytes {
    
    private long sum;
    
    public TotalBytes(){
        sum = 0;
    }
    
    public synchronized void addToSum(long amount){
        sum += amount;
    }
    
    public long getSum(){
        return sum;
    }
}
