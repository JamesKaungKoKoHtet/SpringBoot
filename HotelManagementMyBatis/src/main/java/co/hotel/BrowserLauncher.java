package co.hotel;

import java.io.IOException;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BrowserLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser() {
        System.setProperty("java.awt.headless", "false");
        try {
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"; // Modify the path accordingly
            String url = "http://localhost:8080/";
            String[] cmd = {chromePath, "--incognito", url};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
