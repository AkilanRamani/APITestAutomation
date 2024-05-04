package APITestAutomation;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.not;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class APITest {
	RequestSpecification httpRequest;
	Response response;
	ValidatableResponse validatableResponse;

	@Test
	public void extractFullResponse() {
		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1");
		response = httpRequest.request(Method.GET, "");
		System.out.println("Status received" + response.getStatusLine());
		System.out.println("Response" + response.prettyPrint());

	}

	@Test
	public void verifyStatusCode() {
		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1");
		response = httpRequest.get();
		String resString = response.prettyPrint();
		System.out.println("Response Details : " + resString);
		int statuscode = response.getStatusCode();
		if (statuscode == 200) {
			System.out.println("The Given Rest End Points have been passed with the successfull Response status code");
		} else {

			System.out
					.println("The Test Have been Failed and Response status code doesnot matches with our Expectation");
		}
		validatableResponse = response.then();
		Assert.assertEquals(statuscode, 200);
		validatableResponse.assertThat().statusCode(200);
		validatableResponse.assertThat().statusLine("HTTP/1.1 200 ");

	}

	@Test
	public void assertionValidationUsingHamcrestMatchers() {
		RestAssured.given().when().cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().statusCode(200)
				.statusLine("HTTP/1.1 200 ").body("status", equalTo("success"))
				.body("message", equalTo("PO Column Config fetched successfully"))
				.body("data[0].isEnable", equalTo("true"));
	}

	@Test
	public void whenValidateResponseTime_thenSuccess() {
		RestAssured.given().when().cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().time(lessThan(5000L));
	}

	@Test
	public void assertionDataValidation() {
		RestAssured.given().when().cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().assertThat()
				.body("status", equalTo("success"), "message", equalTo("PO Column Config fetched successfully"),
						"data[0].orderNo", equalTo(1), "data[0].columnName", equalTo("slNo"), "data[0].columnCaption",
						equalTo("Sl No"), "data[0].isEnable", equalTo("true"), "data[0].isEdit", equalTo("false"),
						"data[0].max", equalTo("0"), "data[0].type", equalTo("text"), "data[0].isReadOnly",
						equalTo("true"));

	}

	@Test
	public void getALlHeadersFromResponse() {
		response = RestAssured.given().when().cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();

		System.out.println("All Headers of response are :- ");
		Headers allHeaders = response.getHeaders();
		for (Header header : allHeaders) {
			System.out.print(header.getName() + " : ");
			System.out.println(header.getValue());
		}
		System.out.println("Value of Header Content-Type : " + response.getHeader("Content-Type"));
		Headers allValue = response.getHeaders();
		List<Header> header = allValue.getList("Content-Type");
		System.out.println(header);
		for (Header headers : header) {
			System.out.print(headers.getName() + " : ");
			System.out.println(headers.getValue());
		}

		List<String> allValue1 = response.getHeaders().getValues("Content-Type");
		for (String value : allValue1) {
			System.out.println(value);
		}
	}

	@Test
	public void extractResponseBodyUsinJsonPath() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1");
		response = httpRequest.get();
		JsonPath jsonpath = response.jsonPath();
		HashMap<?, ?> path = jsonpath.get("data[0]");
		String resmessage = jsonpath.get("status");
		System.out.println(resmessage);
		String resmsg = jsonpath.get("message");
		System.out.println(resmsg);
		System.out.print(path);
	}

	@Test
	public void extractArraySizeFromResponse() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1");
		response = httpRequest.get();
		JsonPath json = new JsonPath(response.asString());
		int arraysize = json.getInt("data.size()");
		System.out.println(arraysize);
		Assert.assertEquals(arraysize, 27);
		if (arraysize == 27) {
			System.out.println("The Array length matches");
		} else {
			System.out.println("The Array length does not matches");
		}
	}

	@Test
	public void validateJSONSchemaUsingFile() {
		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "1B1B28B6BEBA1D2158B059EA91FD315D.jvm1");
		httpRequest.get().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\schemavalidation.json")));
	}

	@Test
	public void validateJSONSchemaUsingJsonSchemClassPath() {

		String baseUri = "http://cloudtest5:8181/RayMedi_HQ";

		Response response = RestAssured.given().baseUri(baseUri).when().get("/rest/purchaseEntry/columnConfig").then()
				.assertThat().statusCode(200).extract().response();

		MatcherAssert.assertThat("Validate json schema", response.getBody().asString(),
				JsonSchemaValidator.matchesJsonSchemaInClasspath(
						"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\schemavalidation.json"));
	}

	@Test
	public void readingDataFromJsonFile() throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		FileReader r = new FileReader(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\val.json");
		Object obj = parser.parse(r);
		JSONObject jsonObject = (JSONObject) obj;
		String name = (String) jsonObject.get("status");
		String course = (String) jsonObject.get("message");

		System.out.println("Name: " + name);
		System.out.println("Course: " + course);
		System.out.println("Subjects:");
		JSONArray subjects = (JSONArray) jsonObject.get("data");
		System.out.println("Name: " + name);
		System.out.println("Course: " + course);
		System.out.println("Subjects:");
		@SuppressWarnings("rawtypes")
		Iterator iterator = subjects.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}

	@Test
	public void readingDataFromJsonFileUsingFileReader() throws IOException {
		FileReader fr = new FileReader(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\val.json");
		int i;
		while ((i = fr.read()) != -1)
			System.out.print((char) i);
		fr.close();

	}

	@Test
	public void testNumberAssertions() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1");
		response = httpRequest.get();
		response.then().assertThat().body("data[0].orderNo", equalTo(1), "data[0].orderNo", greaterThan(0),
				"data[0].orderNo", greaterThanOrEqualTo(50), "data[0].orderNo", lessThan(5), "data[0].orderNo",
				lessThanOrEqualTo(1), "data[0].max", equalTo(0), "data[0].max", greaterThan(-1), "data[0].max",
				greaterThanOrEqualTo(0), "data[0].max", lessThan(6), "data[0].max", lessThanOrEqualTo(0));

	}

	@Test
	public void testStringAssertions() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1");
		response = httpRequest.get();
		response.then().assertThat().body("data[0].isEnable", containsString("true"), "data[0].isEdit",
				equalToIgnoringCase("false"), "data[0].type", containsString("text"), "data[0].isReadOnly",
				startsWith("t"), "data[0].isReadOnly", endsWith("e"), "data[0].columnName", containsString("slNo"),
				"data[0].columnCaption", containsString("Sl No"), "data[0].isReadOnly", startsWith("true"),
				"data[0].columnCaption", startsWith("Sl No"), "data[0].isReasOnly",
				equalToIgnoringWhiteSpace("   true "));
	}

	@Test
	public void testNullFieldAssertions() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1");
		response = httpRequest.get();
		response.then().assertThat().body("status1", is(nullValue()), "data1[0].isEnable", is(nullValue()));
	}

	@Test
	public void testKeyAssertions() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1");
		response = httpRequest.get();
		response.then().assertThat().body("data[0]", hasKey("isReadOnly"), "data[0]", hasKey("columnName"), "data[0]",
				hasKey("type"), "data[0]", hasKey("max"), "data[0]", hasKey("columnCaption"), "data[0]",
				hasKey("isEdit"), "data[0]", hasKey("isEnable"), "data[0]", hasKey("orderNo"));
	}

	@Test
	public void negativeAssertionsTest() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1");
		response = httpRequest.get();
		response.then().assertThat().body("data[0].orderNo", not(equalTo(874)), "data[0].isEnable",
				not(equalTo("false")), "data[0].columnCaption", not(equalTo("Test")), "data[0].isEdit",
				not(equalTo("Test")), "data[0].type", not(equalTo("True")), "data[0].columnName",
				not(equalTo("config")), "data[0].max", not(equalTo(874)), "data[0].max", not(equalTo(6)),
				"data[0].isReadOnly", not(equalTo("False")), "data[0].columnCaption", not(equalTo("Test")));

	}

	@Test
	public void multipleAssertions() {

		RestAssured.baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie("JSESSIONID", "DE11462C955D69955E57EABB834283D7.jvm1");
		response = httpRequest.get();
		response.then().assertThat().body("status", equalTo("success"), "message",
				equalTo("PO Column Config fetched successfully"), "status", containsString("success"), "status",
				startsWith("succ"), "message", containsString("PO Column Config fetched"));

	}

	@Test
	public void urlTestConnection() throws IOException {
		URL obj = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		// con.setRequestProperty("X-Auth-Token",
		// "3EC73865DF968FB408D209545913712F7CB9FD10018689516EF1E89CA32A57F7BA3F07324694448E");
		con.setRequestProperty("Cookie", "JSESSIONID=9B19C5DFCE73E3348445BE752BDAAA17.jvm1");
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
	public void readDataFromConfigPropertiesFile() throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		fis = new FileInputStream(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\data.properties");
		prop = new Properties();
		prop.load(fis);
		String name = prop.getProperty("url");
		System.out.println(name);
		URL url = new URL(name);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setRequestMethod("GET");
		httpCon.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		// con.setRequestProperty("X-Auth-Token",
		// "3EC73865DF968FB408D209545913712F7CB9FD10018689516EF1E89CA32A57F7BA3F07324694448E");
		httpCon.setRequestProperty("Cookie", "JSESSIONID=719CB1B6FDA7BDD05BDAA53ECC662BEE.jvm1");
		int responseCode = httpCon.getResponseCode();

		/*
		 * long date = httpCon.getLastModified(); if (date == 0)
		 * System.out.println("No last-modified information."); else
		 * System.out.println("Last-Modified: " + new Date(date));
		 */
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
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
	public void expiryAPITest() throws IOException {
		URL url = null;
		try {
			url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		long date = httpCon.getExpiration();
		if (date == 0)
			System.out.println("No expiration information.");
		else
			System.out.println("Expires: " + new Date(date));

	}

	@Test
	public void readLastModifiedDate() throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		fis = new FileInputStream(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\data.properties");
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
	public void readResponseUsingHTTPConnection() throws IOException { // Completed

		URL obj = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("Cookie", "JSESSIONID=8D8C8C8ADF8679FC92D4571E77623440.jvm1");
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);

	}

	@Test
	public void readWebPage() throws IOException {

		String url = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		String filePath = "C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\webdata.json";

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		// con.setRequestProperty("X-Auth-Token",
		// "3EC73865DF968FB408D209545913712F7CB9FD10018689516EF1E89CA32A57F7BA3F07324694448E");
		con.setRequestProperty("Cookie", "JSESSIONID=17323E05D1DA31D6B2F952FED53C0613.jvm1");

		InputStream inputStream = con.getInputStream();
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
			// System.exit(0);
		} else {
			System.out.println("The Download is Successfull with the status code" + responseCode);
		}
	}

	@Test
	public void readResponseHeaders() throws IOException {
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
	public void readTypeOfMethod() throws IOException {

		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		System.out.println("Request method is " + httpCon.getRequestMethod());

	}

	@Test
	public void readLength() throws IOException {
		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setRequestMethod("GET");
		httpCon.setRequestProperty("Cookie", "JSESSIONID=2B2BDB6FB981DD0DB8EAB7B89C03E318.jvm1");

		int len = httpCon.getContentLength();
		if (len == -1)
			System.out.println("Content length unavailable.");
		else
			System.out.println("Content-Length: " + len);
	}

	@Test
	public void readFileSize() throws IOException {

		int size;
		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Cookie", "JSESSIONID=2B2BDB6FB981DD0DB8EAB7B89C03E318.jvm1");

		size = con.getContentLength();
		if (size < 0)
			System.out.println("Could not determine file size.");
		else
			System.out.println(size);

	}

	@Test
	public void encodingAPI() throws IOException {
		URL url = new URL("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setRequestMethod("GET");
		httpCon.setRequestProperty("Cookie", "JSESSIONID=2B2BDB6FB981DD0DB8EAB7B89C03E318.jvm1");
		System.out.println(
				URLEncoder.encode("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig", "UTF-8"));

	}

	@Test
	public void readDecodeAPI() throws IOException {
		try {
			String url = "http%3A%2F%2Fcloudtest5%3A8181%2FRayMedi_HQ%2Frest%2FpurchaseEntry%2FcolumnConfig";
			String decoded = URLDecoder.decode(url, "UTF-8");
			System.out.println(decoded);
			URL url1 = new URL(decoded);
			HttpURLConnection httpCon = (HttpURLConnection) url1.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("Cookie", "JSESSIONID=2B2BDB6FB981DD0DB8EAB7B89C03E318.jvm1");
			InputStream inputStream = httpCon.getInputStream();
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
	public void readDate() throws IOException {
		URL url = new URL("http://10.63.39.201:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		long date = httpCon.getDate();
		if (date == 0)
			System.out.println("No date information.");
		else
			System.out.println("Date: " + new Date(date));

	}

	@Test
	public void responseExtractionUsingJsonPath() {

		Response res = RestAssured.given().cookie("JSESSIONID", "71B1DDDD64B8AF701B34DC4EAF1058C2.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		System.out.println(res.asPrettyString());
		JsonPath jsonpath = new JsonPath(res.asString());
		System.out.println(jsonpath.getString("data"));

		System.out.println(jsonpath.getString("data[0].orderNo"));

		List<Object> obj = jsonpath.getList("data.orderNo");
		System.out.println(obj);
		int count = obj.size();
		for (int i = 0; i < count; i++) {
			System.out.println("Column name is : " + jsonpath.get("data.columnName[" + i + "]") + "\t"
					+ "Max Value is  : " + jsonpath.get("data.max[" + i + "]"));

		}
	}

	@Test
	public void responseExtractionUsingGroovy() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		// System.out.println(res.asPrettyString());
		Map<String, ?> responseExtractionUsingGroovy = res.path("data.find { it.columnName == 'netCost' }");
		System.out.println(responseExtractionUsingGroovy);
	}

	@Test
	public void responseExtractionUsingGroovyWithSingleValue() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String responseExtractionUsingGroovyWithSingleValue = res.path("data.find { it.orderNo == 16 }.columnName");
		System.out.println(responseExtractionUsingGroovyWithSingleValue);

	}

	@Test
	public void extractListOfValueUsingFindAll() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		List<String> extractListOfValueUsingFindAll = res.path("data.findAll { it.orderNo > 10 }.max");
		System.out.println(extractListOfValueUsingFindAll);
	}

	@Test
	public void extractValueUsingMax() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String highestNumberPlayer = res.path("data.max { it.orderNo }.columnName");
		System.out.println(highestNumberPlayer);
	}

	@Test
	public void extractValueUsingMin() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String extractValueUsingMax = res.path("data.min { it.orderNo }.columnName");
		System.out.println(extractValueUsingMax);
	}

	@Test
	public void extractValueUsingCollect() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		int extractValueUsingCollect = res.path("data.collect { it.orderNo }.sum() ");
		System.out.println(extractValueUsingCollect);
	}

	@Test
	public void extractValueUsingFindAndFindAll() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		Map<String, ?> extractValueUsingFindAndFindAll = res
				.path("data.findAll { it.columnName == \"cessAmount\" }.find { it.columnCaption == \"CESS Amt\" }");

		System.out.println(extractValueUsingFindAndFindAll);

	}

	@Test
	public void extractValueUsingParam() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String columnName = "cessAmount";
		String columnCaption = "CESS Amt";
		Map<String, ?> extractValueUsingParam = res.path(
				"data.findAll { it.columnName == '%s' }.find { it.columnCaption == '%s' }", columnName, columnCaption);
		System.out.println(extractValueUsingParam);

	}

	@Test
	public void extractValueUsingParam12() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String columnName = "cessAmount";
		String columnCaption = "CESS Amt";
		ArrayList<Map<String, ?>> extractValueUsingParam12 = res.path(
				"data.findAll { it.columnName == '%s' }.findAll { it.columnCaption == '%s' }", columnName,
				columnCaption);
		System.out.println(extractValueUsingParam12);

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
		if (responseCode != 200 && responseCode == 404) {
			System.out.println("The Given EndPoint URL is incorrect and hence the Resource is Not Found");

		} else {
			System.out.println("The Given  URL is correct and hence the Resource is  Found");

		}
	}

	@Test(dataProvider = "authorizationCookies")
	public void dataDrivenTesting(String cookiename, String cookieValue) {
		RestAssured.baseURI = "http://10.63.39.141:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		httpRequest = RestAssured.given();
		httpRequest.cookie(cookiename, cookieValue);
		response = httpRequest.request(Method.GET, "");
		System.out.println("Status received" + response.getStatusLine());
		System.out.println("Response" + response.prettyPrint());
	}

	@DataProvider(name = "authorizationCookies")
	public Object[][] createAuthCookie() {
		return new Object[][] { { "JSESSIONID", "444D96EB65A33A2FC70C2A94A087C331.jvm1" },

		};
	}

	@Test
	public void validProtocolValidation() throws IOException {
		String baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		if (baseURI.contains("http")) {
			URL urlObj = new URL(baseURI);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", "JSESSIONID=B9C8F4BCD8CA773EE565651B675A02FB.jvm1");
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
				System.out.println(response.toString());
			}
		} else {

			System.out.println(
					"The Given Protocol is an invalid.Verify SSL certificates when sending a request. Verification failures will result in the request being aborted.");
		}

	}

	@Test
	public void validateResponseUsingOkHttpClient() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request getRequest = new Request.Builder()
				.url("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig")
				.addHeader("Cookie",
						"JSESSIONID=E2BF75C7DECD5D7CFD80300E989FFD18.jvm1; _zcsr_tmp=ae1e1426efcac4b64c63db7eadd80209e9b05e26f0c9b9e1ebc6d48e174f13340710bc7de8cf76173fd0f5547201ab731eb5e6b4fc902ce531266775a6bb29a7; csrfCookie=ae1e1426efcac4b64c63db7eadd80209e9b05e26f0c9b9e1ebc6d48e174f13340710bc7de8cf76173fd0f5547201ab731eb5e6b4fc902ce531266775a6bb29a7")
				.build();

		try {
			okhttp3.Response response = client.newCall(getRequest).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validateResponseUsingHttpClient() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://10.63.39.201:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig"))
				.header("Cookie", "JSESSIONID=010759436E53CC3435FBCFAD5CA6502A.jvm1").build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());

	}

	@Test
	public void validateResponseUsingCloseableHttpClient() throws IOException, InterruptedException {

		String USER_AGENT = "PostmanRuntime/7.37.0";

		String GET_URL = "http://10.63.39.201:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(GET_URL);
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Cookie", "JSESSIONID=010759436E53CC3435FBCFAD5CA6502A.jvm1");
		@SuppressWarnings("deprecation")
		org.apache.hc.client5.http.impl.classic.CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		// <T> T org.apache.hc.client5.http.impl.classic.CloseableHttpClient.
		// execute(ClassicHttpRequest request, HttpClientResponseHandler<? extends T>
		// responseHandler) throws IOException

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		System.out.println(response.toString());
		httpClient.close();
	}

	@Test
	public void serializationResponse() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		ObjectMapper mapper = new ObjectMapper();
		FileReader reader = new FileReader(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITesting\\src\\test\\resources\\DataRead\\jsondata.json");
		Object obj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) obj;
		String jsondata = jsonObject.toJSONString();

		try {

			// serialize json response into json string
			String stdString1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsondata);

			System.out.println("The json data is of " + stdString1);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void checkBrokenLinkStatusUrl() throws IOException, URISyntaxException {
		URL url = new URL("http://10.63.39.141:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig");

		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
	
		huc.setRequestMethod("GET");
		huc.setRequestProperty("User-Agent", "PostmanRuntime/7.36.3");
		huc.setRequestProperty("Cookie", "JSESSIONID=9A3675748E0BE1CA05375DB8F3CB411B.jvm1");
		int rescode = huc.getResponseCode();
		System.out.println(rescode);
		if (rescode >= 400 && rescode!=200) {
			System.out.println("The Given " + url + "is an invalid and a Broken Link");
		} else {
			System.out.println("The Given " + url + "is an valid and not a Broken Link ");

		}

	}
}