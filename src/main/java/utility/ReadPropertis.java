package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertis {
	public static String readProperties(String property) throws IOException {
		FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\APIAutomation\\src\\main\\java\\utility\\global.properties");
	    Properties prop=new Properties();
	    prop.load(fr);
	    String value= prop.getProperty(property);
	    return value;
	    	
	}

}
