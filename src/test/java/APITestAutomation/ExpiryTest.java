package APITestAutomation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class ExpiryTest {

	public static void main(String[] args) throws IOException {
		
			
		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
	    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

	    long date = httpCon.getExpiration();
	    if (date == 0)
	      System.out.println("No expiration information.");
	    else
	      System.out.println("Expires: " + new Date(date));
	}

}
