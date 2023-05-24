package co.hotel;

//import java.io.IOException;
//
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BrowserLauncher {

//	@EventListener(ApplicationReadyEvent.class)
//	public void launchBrowser() {
//		System.setProperty("java.awt.headless", "false");
//	
//		//Run this for Icognito mode : need to add Google Chrome path
//		try {
//			String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
//			String url = "http://localhost:8080/";
//			String[] cmd = { chromePath, "--incognito", url };
//			Runtime.getRuntime().exec(cmd);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		//Run This for normal browser
//		Desktop desktop = Desktop.getDesktop();
//		try {
//			desktop.browse(new URI("http://localhost:8080/"));
//		} catch (IOException | URISyntaxException e) {
//			e.printStackTrace();
//		}
//	}
	
}
