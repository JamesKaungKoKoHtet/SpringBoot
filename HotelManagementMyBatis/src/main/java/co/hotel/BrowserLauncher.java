package co.hotel;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class BrowserLauncher {
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void launchBrowser() {
//		System.setProperty("java.awt.headless", "false");
//		Desktop desktop = Desktop.getDesktop();
//		try {
//			desktop.browse(new URI("http://localhost:8080/"));
//		} catch (IOException | URISyntaxException e) {
//			e.printStackTrace();
//		}
//	}

}