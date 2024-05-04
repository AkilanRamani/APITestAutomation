package APITestAutomation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;



public class CharConversion {
	@Test
	public void readConnection() {
		try {
			URL url = new URL("http://www.java2s.com/");
			InputStream inputStream = url.openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readConnection1() throws IOException {

		int size;
		URL url = new URL("https://reqres.in/api/users/2");
		URLConnection conn = url.openConnection();
		size = conn.getContentLength();
		if (size < 0)
			System.out.println("Could not determine file size.");
		else
			System.out.println(size);
		conn.getInputStream().close();

	}

	@Test
	public void readConnection12() throws IOException {
		URL url = new URL("https://reqres.in/api/users/2");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		long date = httpCon.getDate();
		if (date == 0)
			System.out.println("No date information.");
		else
			System.out.println("Date: " + new Date(date));

	}

	@Test
	public void readConnection122() throws IOException {
		URL url = new URL("https://reqres.in/api/users/2");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		System.out.println(URLEncoder.encode("http://java2s.com/", "UTF-8"));

	}

	@Test
	public void readConnection1225() throws IOException {
		try {
			String url = "http%3A%2F%2Fjava2s.com%2F";
			String decoded = URLDecoder.decode(url, "UTF-8");
			System.out.println(decoded);
			URL url1 = new URL(decoded);
			InputStream inputStream = url1.openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Test
	public void readConnection12257() throws IOException {

		String input = "http%3A%2F%2Fjava2s.com%2F";
		System.out.println(input);
		try {
			String output = URLDecoder.decode(input, "UTF8");
			System.out.println(output);
		} catch (Exception e) {
			System.err.println("Malformed URL");
		}
	}

	@Test
	public void readConnection122576() throws IOException {

		URL url = new URL("http://www.java2s.com");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		long date = httpCon.getExpiration();
		if (date == 0)
			System.out.println("No expiration information.");
		else
			System.out.println("Expires: " + new Date(date));

	}

	@Test
	public void readConnection1225768() throws IOException {

		URL url = new URL("http://www.java2s.com");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		long date = httpCon.getLastModified();
		if (date == 0)
			System.out.println("No last-modified information.");
		else
			System.out.println("Last-Modified: " + new Date(date));

	}

	@Test
	public void read() throws IOException {

		URL url = new URL("http://www.google.com");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		System.out.println("Content-Type: " + httpCon.getContentType());

	}

	@Test
	public void readLength() throws IOException {
		URL url = new URL("http://www.google.com");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		int len = httpCon.getContentLength();
		if (len == -1)
			System.out.println("Content length unavailable.");
		else
			System.out.println("Content-Length: " + len);
	}

	@Test
	public void readLength1() throws IOException {

		URL url = new URL("http://www.java2s.com");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		System.out.println("Request method is " + httpCon.getRequestMethod());

	}

	@Test
	public void readCode() throws IOException {

		URL url = new URL("http://www.java2s.com");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		System.out.println("Response Message is " + httpCon.getResponseMessage());

		System.out.println("Response code is " + httpCon.getResponseCode());
	}

	@Test
	public void readCode1() throws IOException {
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection con = (HttpURLConnection) new URL("http://www.google.com").openConnection();
		con.setRequestMethod("GET");
		System.out.println(con.getResponseCode() == HttpURLConnection.HTTP_OK);
	}

	@Test
	public void readData() throws IOException {
		String url = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";

		URL urlObj = new URL(url);
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();

		int responseCode = httpCon.getResponseCode();
		String responseMessage = httpCon.getResponseMessage();
		String contentType = httpCon.getContentType();
		String contentEncoding = httpCon.getContentEncoding();
		int contentLength = httpCon.getContentLength();

		long date = httpCon.getDate();
		long expiration = httpCon.getExpiration();
		long lastModified = httpCon.getLastModified();

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
	public void readData1() throws IOException {
		String url = "https://reqres.in/api/users/2";
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
	public void readData11() throws IOException {
		String url = "https://reqres.in/api/users/2";
		URL urlObj = new URL(url);
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();

		int responseCode = httpCon.getResponseCode();

		if (responseCode != HttpURLConnection.HTTP_OK) { // 200 ! =
			System.out.println("Server returned response code " + responseCode + ". Download failed.");
			// System.exit(0);
		}
	}

	@Test
	public void readWebPage() throws IOException {

		String url = "https://reqres.in/api/users/2";
		String filePath = "C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\webpage1.json";

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		URLConnection urlCon = urlObj.openConnection();

		InputStream inputStream = urlCon.getInputStream();
		BufferedInputStream reader = new BufferedInputStream(inputStream);

		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = reader.read(buffer)) != -1) { // -1 != -1
			writer.write(buffer, 0, bytesRead);
		}

		writer.close();
		reader.close();
	}
	
	@Test
	public void readDataFromConfigFile() throws IOException {
		FileInputStream fis = null;
	      Properties prop = null;
	         fis = new FileInputStream("C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\data.properties");
	         prop = new Properties();
	         prop.load(fis);
	         String name = prop.getProperty("url");
	         System.out.println(name);
	         URL url = new URL(name);
	 		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

	 		long date = httpCon.getLastModified();
	 		if (date == 0)
	 			System.out.println("No last-modified information.");
	 		else
	 			System.out.println("Last-Modified: " + new Date(date));
	         
	}     
	
	@Test
	public void readDataFromConfigFile1() throws IOException { // Completed
	
		URL obj = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 System.out.println("Content-type: " + con.getContentType());
		    
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		//con.setRequestProperty("X-Auth-Token", "3EC73865DF968FB408D209545913712F7CB9FD10018689516EF1E89CA32A57F7BA3F07324694448E");
		con.setRequestProperty("Cookie","JSESSIONID=719CB1B6FDA7BDD05BDAA53ECC662BEE.jvm1");
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		
}
	
	
}