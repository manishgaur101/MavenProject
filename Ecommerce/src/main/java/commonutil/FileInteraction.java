package commonutil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class FileInteraction {

	 /** Path to the File. */
    private String path;
	
	Logger log = Logger.getLogger(FileInteraction.class);
	
	public FileInteraction(final String path) {
		this.path = path;
		
	}
	
	
	/**
	 * Method to print text file content
	 * 
	 * @author Manish.Gaur
	 */
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
			e.printStackTrace();
		}
		
	}
	
	public void readCSV(){
		
			
		
	}
	
	/**
	 * Method to convert file Content into String
	 * 
	 * @param
	 * 		file is path of the file whose data needs to be converted into String
	 * @return
	 * 		String - conversion of the given File
	 */
	
	public static String readFileToString(String file ){
		String StringFile = "";
		try {
		return new String(Files.readAllBytes(Paths.get(file)));
	} catch (IOException e) {
		return StringFile;
	}
		
	}

	
	public static void main(String[] var){
		FileInteraction fObj = new FileInteraction("D://Sujoy//myworkspace//GitProject//Ecommerce//src//test//resources//TestData//TestText.txt");
		fObj.readTextFile();
		
		
	}

}
