package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadInputs {
	public static String readJsonData(String path) throws IOException, ParseException {
		FileReader fr=new FileReader (path);
		JSONParser jp=new JSONParser();
		String responsebody=jp.parse(fr).toString();
		return responsebody;
	}

}
