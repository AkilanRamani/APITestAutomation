package APITestAutomation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class URLConnectionTest {

	@Test
	public void display() throws IOException {

		URL obj = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		// con.setRequestProperty("X-Auth-Token",
		// "3EC73865DF968FB408D209545913712F7CB9FD10018689516EF1E89CA32A57F7BA3F07324694448E");
		con.setRequestProperty("Cookie", "JSESSIONID=719CB1B6FDA7BDD05BDAA53ECC662BEE.jvm1");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request did not work.");
		}

	}

	@Test
	public void isValidURL() throws IOException, URISyntaxException {
		String url = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		System.out.println(UtilityTestBaseClass.isValid(url));

	}
	@Test
	public void checkResponseWithInvalidURL() throws IOException, URISyntaxException {
		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/purchaseEntry/columnConfig"); 
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");
		huc.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		huc.setRequestProperty("Cookie", "JSESSIONID=7FAB2FA9D2712820ED32EED7654B82A5.jvm1");
		 
		int responseCode = huc.getResponseCode();
		 
		Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);
		if(responseCode!=200 && responseCode==404) {
			System.out.println("The Given EndPoint URL is incorrect and hence the Resource is Not Found");
			
		}
		else {
			System.out.println("The Given  URL is correct and hence the Resource is  Found");
			
		}
	}
}