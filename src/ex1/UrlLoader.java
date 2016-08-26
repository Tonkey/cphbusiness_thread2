package ex1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Nicklas Molving
 */
public class UrlLoader extends Thread {

    private final String url;
    private long sum;

    private byte[] byteArr;
    TotalBytes tb = new TotalBytes();
    public UrlLoader(String url, TotalBytes tb) {
        this.url = url;
        this.tb = tb;
    }

    
    public void run() {
        try {
            byteArr=getBytesFromUrl(url);
            for(int i = 0; i < byteArr.length; i++){
                sum += byteArr[i];
            }
            tb.addToSum(sum);
        } catch (IOException ex) {
            System.out.println("something went wrong!");
            ex.printStackTrace();
        }
        System.out.println("amount of bytes from URL: " + sum);
    }

    public static byte[] getBytesFromUrl(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.connect();
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
            InputStream is = connection.getInputStream();
            byte[] bytebuff = new byte[4096];
            int read;
            while ((read = is.read(bytebuff)) > 0) {
                bis.write(bytebuff, 0, read);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return bis.toByteArray();
    }

}
