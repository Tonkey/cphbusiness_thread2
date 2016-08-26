package textwriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Nicklas Molving
 */
public class BackupThread extends Thread{

    private List<String> words;
    private String userDir;
    File actualFile;
    JLabel label;
    
    
    
    public BackupThread(List<String> words, JLabel label){
        this.words = words;
        this.label = label;
        userDir = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        System.out.println(userDir);
        
        actualFile = new File(userDir, "backup.txt");
    }
    
    @Override
    public void run() {
        
        while(true){
            
            
            try {
                this.sleep(15000);
            } catch (InterruptedException ex) {
                System.out.println("something went wrong");
                ex.printStackTrace();
            }
            backupWriter();
        }
        
        
    }
    
    /**
     *
     * @param words
     */
    private void backupWriter(){
        label.setText("Saving....");
        System.out.println("writing to file");
        try {
            FileWriter fw = new FileWriter(actualFile, false);
            PrintWriter pw = new PrintWriter(fw);
            for(String s : words){
                pw.println(s);
            }
                pw.close();
            
        } catch (IOException ex) {
            System.out.println("Something went wrong");
            ex.printStackTrace();
        }
        
        System.out.println("done writing to file!");
        label.setText("file saved");
    }
    
    public void newList(List<String> wordList){
        words = wordList;
    }
}
