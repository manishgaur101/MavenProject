package commonutil;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import org.apache.log4j.Logger;

import testcases.ui.SanityTest;

public class FileInteraction {

	 /** Path to the File. */
    private String path;
	
	Logger log = Logger.getLogger(FileInteraction.class);
	
	public FileInteraction(final String path) {
		this.path = path;
		
	}
	
	public void readTextFile(){
		try {
			RandomAccessFile file = new RandomAccessFile(path,"r");
			//HashMap<String, String> map = new HashMap<String, String>();
			String str;
			while ((str = file.readLine()) != null) {
				System.out.println(str);
				
			}
			file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readCSV(){
		
			
		
	}

	
	public static void main(String[] var){
		FileInteraction fObj = new FileInteraction("D://Sujoy//myworkspace//GitProject//Ecommerce//src//test//resources//TestData//TestText.txt");
		fObj.readTextFile();
		
		
	}

}
