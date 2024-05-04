package APITestAutomation;

import java.net.URL;

public class UtilityTestBaseClass {

	public static boolean isValid(String url) {
		try {
			new URL(url).toURI();
			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
}
