import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws AWTException, InterruptedException {
        String[] pickuplines = {
            "Can I borrow a kiss? I promise I'll give it back.",
            "Can I take your picture to prove to all my friends that angels do exist?",
            "You're the only girl I love now... but in ten years, I'll love another girl. She'll call you 'Mommy.'",
            "I will stop loving you when an apple grows from a mango tree on the 30th of February",
            "I don't have a library card, but do you mind if I check you out?"
        };
        Robot r = new Robot();
        TimeUnit.SECONDS.sleep(1);
        while(true){
            String text = pickuplines[(int) (Math.random() * pickuplines.length)];
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
