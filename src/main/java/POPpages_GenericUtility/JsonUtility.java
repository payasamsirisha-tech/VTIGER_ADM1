package POPpages_GenericUtility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * @author Lakshmi
 * 
 */
public class JsonUtility {
	/**
	 * This method is used to fetch data from json file using key
	 * 
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	
	public String fetchDatafromJsonFile(String key)throws FileNotFoundException,IOException, ParseException {
		
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/VtigerCommndata.json"));
		JSONObject js=(JSONObject)obj;
		String data=js.get(key).toString();
		return data;
	}

}
