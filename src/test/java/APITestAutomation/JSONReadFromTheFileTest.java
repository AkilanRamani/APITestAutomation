package APITestAutomation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReadFromTheFileTest {

	public static void main(String[] args) throws IOException, ParseException {
		
		 FileReader fr=new FileReader("C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\val.json");    
         int i;    
         while((i=fr.read())!=-1)    
         System.out.print((char)i); 
         File f = new File("C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\writedata.json");
         FileWriter  f1 = new FileWriter(f);
         f1.write((char)i);
         f1.flush();
         fr.close();    
		
}
}