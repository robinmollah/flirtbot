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
    public static void main(String[] args) throws AWTException, InterruptedException, FileNotFoundException {
        File fPickupLines = new File("./pickuplines.txt");
        File fSalutes = new File("./salutes.txt");
        File fNicknames = new File("./nicknames.txt");
        Scanner sc = new Scanner(fPickupLines);

        ArrayList<String> pickuplines = new ArrayList<String>();
        while(sc.hasNextLine()){
            pickuplines.add(sc.nextLine());
        }

        Robot r = new Robot();
        TimeUnit.SECONDS.sleep(1);
        while(true){
            String text = pickuplines.get((int) (Math.random() * pickuplines.size()));
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);

            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
