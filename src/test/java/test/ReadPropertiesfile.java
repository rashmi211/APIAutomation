package test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesfile {
	public static void main(String[] args) throws IOException {
	FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\APIAutomation\\src\\main\\java\\utility\\global.properties");
    Properties prop=new Properties();
    prop.load(fr);
    
    
    String url=prop.getProperty("URL");
    System.out.println(url);
    
    String username=prop.getProperty("Username");
    System.out.println(username);
    System.out.println(prop.getProperty("Password"));
   
}
}