package commonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtils {

	public String getDataFrmPropertyFile(String keyName) throws IOException
	{
		FileInputStream fis = new FileInputStream("src\\test\\resources\\data1.properties");
		Properties ps = new Properties();
		ps.load(fis);
		String value = ps.getProperty(keyName);
		return value;
		
	}
	
	public String getDataFrmPropertyFile1(String keyName) throws IOException
	{
		//prerared for Vtiger application 
		FileInputStream fis = new FileInputStream("src\\test\\resources\\vtigerpropert.properties");
		Properties ps = new Properties();
		ps.load(fis);
		String value = ps.getProperty(keyName);
		return value;
		
	}
	
}

