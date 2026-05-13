package POPpages_GenericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author SuryaLakshmi
 * This class is declare to fetch data and writeback data from property file
 */
public class PropertyfileUtility {
	/**
	 * This method is declare to fetch data from property file
	 * @param Key
	 * @return
	 * @throws IOException
	 */
	
	public String fetchdatafromPropFile(String Key) throws IOException {
		
		FileInputStream fis=new FileInputStream("./src/test/resources/Org.properties");
		
		Properties prop=new Properties();
		
		prop.load(fis);
		
		String Value = prop.getProperty(Key);
		
		return Value;
	}
	/**
	 * This method is declare to writeback data to property file
	 * @param key
	 * @param Value
	 * @throws IOException
	 */
	public void writeBackDataToPropFile(String key,String Value) throws IOException {
		
      FileInputStream fis=new FileInputStream("./src/test/resources/Org.properties");
		
		Properties prop=new Properties();
		
		prop.load(fis);
		
		prop.put(key, Value);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/Org.properties");
		prop.store(fos, "update");
	}
	
	
}
