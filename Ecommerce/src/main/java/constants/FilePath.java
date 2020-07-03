package constants;

import java.io.File;

public interface FilePath {

	String PROJECT_PATH = System.getProperty("user.dir");
	
	String TEST_RESOURCE_PACKAGE = PROJECT_PATH+File.separator+"src"+File.separator+"test"+File.separator+"resources";
	/** Path of env.properties file */
	String ENV_PROPERTIES = TEST_RESOURCE_PACKAGE+File.separator+"envsetup"+File.separator+"env.properties";
	
	String REPORT_PACKAGE = FilePath.PROJECT_PATH+File.separator+"Report";
	String SCREEN_SHOT = FilePath.PROJECT_PATH+File.separator+"Images";
	String LOG4J_XML = TEST_RESOURCE_PACKAGE+File.separator+"Log4j.xml";
	String Excel_File_Path = TEST_RESOURCE_PACKAGE+File.separator+"TestData"+File.separator+"MGTest.xlsx";
	String csv_File_Path = TEST_RESOURCE_PACKAGE+File.separator+"TestData"+File.separator+"userLogin.csv";
}
