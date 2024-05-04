package APITestAutomation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class writedata {
	

	public static void main(String[] args) throws IOException, ParseException {

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
	
       @SuppressWarnings("resource")
	FileWriter file = new FileWriter("C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\writedata.json") ;
       String trxt = (String) iterator.next();
       file.write(trxt);
       System.out.println("JSON Object write to a File successfully");
       System.out.println("JSON Object: " + obj);
}
}
}