package mainPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder(); //allows adding to a string
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line; //current line of file
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
			
		}catch(IOException e) {
			e.printStackTrace(); //promts error to screen
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
