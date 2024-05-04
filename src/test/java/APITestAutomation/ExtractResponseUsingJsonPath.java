package APITestAutomation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ExtractResponseUsingJsonPath {
	
	@Test
	public void responseExtraction() {
		
		Response res = RestAssured.given().when().get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		System.out.println(res.asPrettyString());
		JsonPath jsonpath = new JsonPath(res.asString());
		System.out.println(jsonpath.getString("page"));
	}
	
	@Test
	public void checkDownloadStatus() throws IOException {
		String url = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		con.setRequestProperty("Cookie", "JSESSIONID=74BD1BF950C44A04D2027AFB86932B44.jvm1");
		int responseCode = con.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			System.out.println("Server returned response code " + responseCode + ". Download failed.");
			System.exit(0);
		} else {
			System.out.println("The Download is Successfull with the status code" + responseCode);
		}
	}
	
	

	@Test
	public void readData1() throws IOException {
		String url = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		URL urlObj = new URL(url);
		URLConnection urlCon = urlObj.openConnection();

		Map<String, List<String>> map = urlCon.getHeaderFields();

		for (String key : map.keySet()) {
			System.out.println(key + ":");

			List<String> values = map.get(key);

			for (String aValue : values) {
				System.out.println("\t" + aValue);
			}
		}
	}

	
	
	@Test
	public void readResponseData() throws IOException {
		String url = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Cookie", "JSESSIONID=2B2BDB6FB981DD0DB8EAB7B89C03E318.jvm1");
        
		int responseCode = con.getResponseCode();
		String responseMessage = con.getResponseMessage();
		String contentType = con.getContentType();
		String contentEncoding = con.getContentEncoding();
		int contentLength = con.getContentLength();

		long date = con.getDate();
		long expiration = con.getExpiration();
		long lastModified = con.getLastModified();

		System.out.println("Response Code: " + responseCode);
		System.out.println("Response Message: " + responseMessage);
		System.out.println("Content Type: " + contentType);
		System.out.println("Content Encoding: " + contentEncoding);
		System.out.println("Content Length: " + contentLength);
		System.out.println("Date: " + new Date(date));
		System.out.println("Expiration: " + new Date(expiration));
		System.out.println("Last Modified: " + new Date(lastModified));
	}   
	@Test
	public void readConnection1() throws IOException {

		int size;
		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Cookie", "JSESSIONID=2B2BDB6FB981DD0DB8EAB7B89C03E318.jvm1");
		
		size = con.getContentLength();
		if (size < 0) // 4039<0
			System.out.println("Could not determine file size.");
		else
			System.out.println(size);
		

	}
}
