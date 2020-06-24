import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static final String F_PICKUPLINES = "./pickuplines.txt";
    static final String F_SALUTES = "./salutes.txt";
    static final String F_NICKNAMES = "./nicknames.txt";
    static final int INTERVAL = 1; // in seconds

    static final float SALUTE_POSSIBILITY = 0.1f;
    static final float NICKNAME_POSSIBILITY = 0.5f;

    public static void main(String[] args) throws AWTException, InterruptedException, FileNotFoundException {
        ArrayList<String> pickuplines = new ArrayList<String>();
        ArrayList<String> salutes = new ArrayList<String>();
        ArrayList<String> nicknames = new ArrayList<String>();
        populate(pickuplines, salutes, nicknames);

        Robot r = new Robot();
        // Time gap before starting to work.
        TimeUnit.SECONDS.sleep(1);
        while(true){
            // Picks a random pick up line
            String line = pickuplines.get((int) (Math.random() * pickuplines.size()));
            // Picks a random salute [Hi, Hello etc..]
            String salute = salutes.get((int) (Math.random() * salutes.size()));
            // Picks a random nickname
            String nickname = nicknames.get((int) (Math.random() * nicknames.size()));

            String text = (Math.random() < SALUTE_POSSIBILITY ? (salute + " ") : "")
                    + (Math.random() < NICKNAME_POSSIBILITY ? (nickname + ", ") : "") + line;

            // Copies the text to clipboard
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);

            // Pastes the text in the input box
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);

            // Press enter to send
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);

            // Waits for the specified interval to send again
            TimeUnit.SECONDS.sleep(INTERVAL);
        }
    }

    // Populate the ArrayLists from files
    private static void populate(ArrayList<String> pickuplines, ArrayList<String> salutes, ArrayList<String> nicknames) throws FileNotFoundException {
        File file;
        Scanner sc;
        file = new File(F_PICKUPLINES);
        sc = new Scanner(file);
        while(sc.hasNextLine()){
            pickuplines.add(sc.nextLine());
        }
        file = new File(F_SALUTES);
        sc = new Scanner(file);
        while(sc.hasNextLine()){
            salutes.add(sc.nextLine());
        }
        file = new File(F_NICKNAMES);
        sc = new Scanner(file);
        while(sc.hasNextLine()){
            nicknames.add(sc.nextLine());
        }
    }
}
