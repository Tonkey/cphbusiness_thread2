package ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicklas Molving
 */
public class TaskRunner {

    /**
     * Eplain the result!!!!
     * with the sequential run I got 456724757 nanoseconds
     * with the parallel run I got 2109234 nanoseconds
     * so it is obvious that the sequential run where the threads have to wait for each other
     * to finish, the runtime increases by a significant amount
     * 
     * so it is pretty obvious that the parallel run is way more time-efficient, although this is only true
     * if the threads DO NOT need something from the other Threads, if this is the case it would be 
     * very difficult/impossible to make the threads that depend on one-another run parallel.
     */
    
    private static final String URL1 = "https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png";
    private static final String URL2 = "https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png";
    private static final String URL3 = "https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png";

    private static void executeThreads() {
        TotalBytes tb = new TotalBytes();
        ExecutorService es = Executors.newCachedThreadPool();

        long start = System.nanoTime();
        
        es.execute(new UrlLoader(URL1, tb));
        es.execute(new UrlLoader(URL2, tb));
        es.execute(new UrlLoader(URL3, tb));

        es.shutdown();
        
        long end = System.nanoTime();
        System.out.println("Time Sequental: " + (end - start));
        try {
            es.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(TotalBytes.class.getName()).log(Level.SEVERE, null, ex);
        }

        String res = "total sum of bytes: " + tb.getSum();
        System.out.println(res);
    }

    public static void main(String[] args) {

        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
        
        executeThreads();
//        TotalBytes tb = new TotalBytes();
//        Thread t1 = new UrlLoader(URL1, tb);
//        Thread t2 = new UrlLoader(URL2, tb);
//        Thread t3 = new UrlLoader(URL3, tb);
//        long start = System.nanoTime();
//        t1.run();
//        t2.run();
//        t3.run();
//        System.out.println("bytes : " + tb.getSum());
//        
//        long end = System.nanoTime();
//        System.out.println("Time Sequental: " + (end - start));

    }

}
