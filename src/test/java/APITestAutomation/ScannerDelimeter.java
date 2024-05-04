package APITestAutomation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerDelimeter {

	public static void main(String[] args) {
		Scanner scan = new Scanner("JavaTpoint/Abhishek/Male/22");  
        //Initialize the string delimiter  
        scan.useDelimiter("/");  
      Pattern a =  scan.delimiter();
      System.out.println(a);
        //Printing the tokenized Strings  
        while(scan.hasNext()){  
            System.out.println(scan.next());  
        }  
        scan.close();   
      }    
	}

